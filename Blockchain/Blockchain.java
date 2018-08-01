/*
  A blockchain is a chain of blocks. Each block has its own digital
  signature, and contains  the digital signature or the previous
  block and have some data.
*/
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Blockchain{
  public static ArrayList<Block> blockchain = new ArrayList<Block>();
  public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();
  
  public static int difficulty = 3;
  public static float minimumTransaction = 0.1f;
  public static Wallet walletA;
  public static Wallet walletB;
  public static Transaction firstTransaction;

  public static void main(String[] args){
	  
	  // add blocks to the blockchain ArrayList
	  // Setup Bouncey castle as Security Provider
	  Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	  
	  // Create the new wallets
	  walletA = new Wallet();
	  walletB = new Wallet();
	  Wallet coinbase = new Wallet();
	  
	  firstTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f, null);
	  // Manually sign the first transaction
	  firstTransaction.generateSignature(coinbase.privateKey);
	  // Manually set the transaction id
	  firstTransaction.transactionId = "0";
	  
	  // Manually add Transaction output
	  firstTransaction.outputs.add(new TransactionOutput(firstTransaction.reciepient, firstTransaction.value, firstTransaction.transactionId));
	  
	  // Important to store our first transaction in the UTXOs list
	  UTXOs.put(firstTransaction.outputs.get(0).id, firstTransaction.outputs.get(0));
	  
	  System.out.println("Creating and Mining First block...");
	  Block first = new Block("0");
	  first.addTransaction(firstTransaction);
	  addBlock(first);
	  
	  // Tesing
	  Block block1 = new Block(first.hash);
	  System.out.println("\nWalletA's balance is: " + walletA.getBalance());;
	  System.out.println("\nWalletA is attempting to send funds (40) to WalletB...");
	  block1.addTransaction(walletA.sendFunds(walletB.publicKey, 40f));
	  addBlock(block1);
	  System.out.println("\nWalletA's balance is: " + walletA.getBalance());
	  System.out.println("\nWalletB's balance is: " + walletB.getBalance());
	  
	  
	  Block block2 = new Block(block1.hash);
	  System.out.println("\nWalletA's balance is: " + walletA.getBalance());;
	  System.out.println("\nWalletA is attempting to send funds (1000) to WalletB...");
	  block2.addTransaction(walletA.sendFunds(walletB.publicKey, 1000f));
	  addBlock(block2);
	  System.out.println("\nWalletA's balance is: " + walletA.getBalance());
	  System.out.println("\nWalletB's balance is: " + walletB.getBalance());
	  
	  
	  
	  Block block3 = new Block(block2.hash);
	  System.out.println("\nWalletA's balance is: " + walletA.getBalance());;
	  System.out.println("\nWalletA is attempting to send funds (40) to WalletB...");
	  block3.addTransaction(walletA.sendFunds(walletB.publicKey, 40f));
	  addBlock(block3);
	  System.out.println("\nWalletA's balance is: " + walletA.getBalance());
	  System.out.println("\nWalletB's balance is: " + walletB.getBalance());
	  
	  isChainValid();
	  // Test public and private keys
  
  }
  
  public static Boolean isChainValid() {
	  Block currentBlock;
	  Block previousBlock;
	  String hashTarget = new String(new char[difficulty]).replace('\0', '0');
	  
	  // a temporary working list of unspent transactions at a given block state.
	  HashMap<String, TransactionOutput> tempUTXOs = new HashMap<String, TransactionOutput>();
	  tempUTXOs.put(firstTransaction.outputs.get(0).id, firstTransaction.outputs.get(0));
	  
	  // loop through blockchain to check hashes:
	  for(int i = 1; i < blockchain.size(); i++) {
		  
		  currentBlock = blockchain.get(i);
		  previousBlock = blockchain.get(i-1);
		  
		  // compare registered hash and calculated hash:
		  if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
			  System.out.println("Current Hashes not equal");
			  return false;
		  }
		  
		  // compare previous hash and registered previous hash
		  if(!previousBlock.hash.equals(currentBlock.previousHash)) {
			  System.out.println("Previous Hashes not equal");
			  return false;
		  }
		  
		  // check if hash is solved
		  if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
			  System.out.println("This block has been mined");
			  return false;
		  }
		  
		  // loop through blockchains transactions:
		  TransactionOutput tempOutput;
		  for(int t = 0; t < currentBlock.transactions.size(); t++) {
			  Transaction currentTransaction = currentBlock.transactions.get(t);
			  
			  if(!currentTransaction.verifySignature()) {
				  System.out.println("Signature on Transaction(" + t + ") is Invalid");
				  return false;
			  }
			  if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
				  System.out.print("Inputs are note equal to outputs on Transaction(" + t + ")");
				  return false;
			  }
			  
			  for(TransactionInput input: currentTransaction.inputs) {
				  tempOutput = tempUTXOs.get(input.transactionOutputId);
				  
				  if(tempOutput == null) {
					  System.out.println("Referenced input on Transaction("+ t + ") is Missing");
					  return false;
				  }
				  
				  tempUTXOs.remove(input.transactionOutputId);
			  }
			  
			  for(TransactionOutput output: currentTransaction.outputs) {
				  tempUTXOs.put(output.id, output);
			  }
			  
			  if(currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient) {
				  System.out.println("Transaction("+ t + ") output reciepient is not who it should be");
				  return false;
			  }
			  
			  if(currentTransaction.outputs.get(1).reciepient != currentTransaction.sender) {
				  System.out.println("Transaction(" + t + ") output 'change' is not sender.");
				  return false;
			  }
		  }
		  
		  
	  }
	  System.out.println("Blockchain is valid");
	  return true;
  }
  

  
  public static void addBlock(Block newBlock) {
	  newBlock.mineBlock(difficulty);
	  blockchain.add(newBlock);
  }



}
