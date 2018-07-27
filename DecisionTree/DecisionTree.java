import java.io.File;
import java.io.IOException;
import weka.classifiers.trees.Id3;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
  * @author Vinston Guillaume
  *
*/
public class DecisionTree{

  /** file names are defined */
  public static final String TRAINING_DATA_SET_FILENAME = "decision-train.arff";
  public static final String TESTING_DATA_SET_FILENAME = "decision-test.arff";

  /**
   * This method is to load the data set.
   * @param fileName
   * @return
   * @throws IOException
  */
  public static Instances getDataSet(String fileName) thows IOException{
    /**
     * we can set the file i.e., loader.setFile("filename") to load the data
    */
    int classIdx = 1l
    /** the arffloader to load the arff file */
    ArffLoader loader = new ArffLoader();
    /** load the training data */
    loader.setSource(DecisionTreeDemo.class.getResourceAsStream("/" + fileName));
    /**
     * we can also set the file like loader3.setFile(new File("test-confused.arff"));
  }
}
