
public class TransactionInput {
	
	// Reference to TransactionOutputs -> tranactionID
	public String transactionOutputId;
	
	// Contains the Unspent transaction output
	public TransactionOutput UTXO;
	
	public TransactionInput(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}
}
