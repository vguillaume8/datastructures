import java.security.*;
import java.util.ArrayList;


public class Transaction {
	// The hash of the transaction
	public String transactionId;
	
	// The senders address/public key
	public PublicKey sender;
	
	// Recipients address/public key
	public PublicKey reciepient;
	
	public float value;
	
	// To prevent anybody else from spending funds in out waller
	public byte[] signature;
	
	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	// How many transactions have been generated
	private static int sequence = 0;
	
	// Constructor
	public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
	}
	
	public boolean processTransaction() {
		
		if(verifySignature() == false) {
			System.out.println("Transaction signature failed to verify");
			return false;
		}
		
		// Gathers transaction inputs (Making sure they are unspent)
		for(TransactionInput i : inputs) {
			i.UTXO = Blockchain.UTXOs.get(i.transactionOutputId);
		}
		
		// Check if transaction is valid
		if(getInputsValue() < Blockchain.minimumTransaction) {
			System.out.print("Transaction Inputs too small: " + getInputsValue());
			System.out.println("Please enter the amount greater than " + Blockchain.minimumTransaction);
			return false;
		}
		
		// Generate transaction outputs
		
		// gets value of inputs then the left over change
		float leftOver = getInputsValue() - value;
		transactionId = calculateHash();
		// send value to recipient
		outputs.add(new TransactionOutput(this.reciepient, value, transactionId));
		// send the left over 'change'
		outputs.add(new TransactionOutput(this.sender, leftOver, transactionId));
		
		// Add outputs to Unspent list
		for(TransactionOutput o : outputs) {
			Blockchain.UTXOs.put(o.id, o);
		}
		
		// Remove transaction inputs from UTXO lists as spent
		for(TransactionInput i : inputs) {
			if(i.UTXO == null) {
				// If transaction can't be found skip it
				continue;
			}
			Blockchain.UTXOs.remove(i.UTXO.id);
		}
		return true;
		
		
	}
	
	public float getInputsValue() {
		float total = 0;
	    
		for(TransactionInput i : inputs) {
			if(i.UTXO == null) {
				// If transaction can't be found skip it.
				continue;
			}
			total += i.UTXO.value;
		}
		return total;
	}
	// Calculates the transaction hash (which will be used as its Id)
	private String calculateHash() {
		// Increase the sequence to avoid 2 identical transactions having the same hash
		sequence++;
		
		return StringUtil.applySha256(StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value) + sequence);
		
	
	}
	
	// Signs all the data we don't want to be tampered with
	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value);
		signature = StringUtil.applyECDSASig(privateKey, data);
	}
	
	// Verifies the data we signed has not been tampered with
	public boolean verifySignature() {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value);
		return StringUtil.verifyECDSASig(sender, data, signature);
	}
	
	public float getOutputsValue() {
		float total = 0;
		for(TransactionOutput o : outputs) {
			total += o.value;
		}
		return total;
	}
	
	

}
