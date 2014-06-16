import com.associationrl.AssociationRuleLearning;
import com.associationrl.AssociationRuleLearningImpl;


/**
 * This is the main class for this program. It internally delegates to the processor classes.
 * This class can (in the future) add more housekeeping tasks as well. For the purpose of this
 * assignment, it is a thin wrapper around the processor class.
 * @author basanth
 * 
 */
public class AssociationRuleLearningEngine {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		AssociationRuleLearning associationRuleLearning = new AssociationRuleLearningImpl();

		associationRuleLearning.findCommonlyOccurringPairs();

	}

}
