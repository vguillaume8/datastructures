
import java.util.ArrayList;
import java.util.Date;

public class Block{

  public String hash;
  public String previousHash;
  public String merkleRoot;
  
  // Data will be a simple message
  public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
  private long timeStamp;
  private int nonce;
  
  
  // Block Constructor
  public Block(String previousHash){
    this.previousHash = previousHash;
    this.timeStamp = new Date().getTime();
    this.hash = calculateHash();
  }
  
  // Calculate new hash based on block contents
  public String calculateHash(){
    String calculatedhash = StringUtil.applySha256(previousHash+Long.toString(timeStamp)+Integer.toString(nonce)+merkleRoot);
    return calculatedhash;
  }
  
  // Increases nonce value until has target is reached
  public void mineBlock(int difficulty) {
	  merkleRoot = StringUtil.getMerkleRoot(transactions);
	  // Create a string with a difficulty of * "0"
	  String target = StringUtil.getDifficultyString(difficulty);
	  while(!hash.substring(0, difficulty).equals(target)) {
		  nonce ++;
		  hash = calculateHash();
	  }
	  System.out.println("Block Mined!!!: "+ hash);
  }

  // Add transactions to this block
  public boolean addTransaction(Transaction transaction) {
	  // process transaction and check if valid, unless block is first block then ignore.
	  if(transaction == null) {
		  return false;
	  }
	  if(!"0".equals(previousHash)) {
		  if((transaction.processTransaction() != true)) {
			  System.out.println("Transaction failed to process. Discared.");
			  return false;
		  }
	  }
	  
	  transactions.add(transaction);
	  System.out.print("Transaction successfully added to Block");
	  return true;
  }
  
}
