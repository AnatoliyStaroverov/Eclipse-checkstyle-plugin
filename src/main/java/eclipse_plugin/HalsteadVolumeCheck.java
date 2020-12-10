package eclipse_plugin;
import com.puppycrawl.tools.checkstyle.api.*;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

public class HalsteadVolumeCheck extends AbstractCheck{

	private double halsteadVolume;
	private int halLength;
	private int halVocabulary;

	private HalsteadLengthCheck halsteadLength = new HalsteadLengthCheck();
	private HalsteadVocabularyCheck halsteadVocabulary = new HalsteadVocabularyCheck();

	private ArrayList<Integer> operandTokens = arrayToList(halsteadLength.getDefaultTokens());
	private ArrayList<Integer> operatorTokens = arrayToList(halsteadVocabulary.getDefaultTokens());

	@Override // initialized and begin tree for length and vocab.
	public void beginTree(DetailAST rootAST) {
		
		// initialize other checks we need for volume calculation.
		halsteadLength.beginTree(rootAST);
		halsteadVocabulary.beginTree(rootAST);
		
		// initialize the two parameter that are need for predefined volume.
		halLength = 0;
		halVocabulary = 0;
	}

	@Override // based on type, visit the token.
	public void visitToken(DetailAST ast) {
		
		// Condition for operand type
		if (operandTokens.contains(ast.getType())) {
			halsteadLength.visitToken(ast);
		}
		
		// Condition for operator type
		if (operatorTokens.contains(ast.getType())) {
			halsteadVocabulary.visitToken(ast);
		}
	}

	// function to calculate defined volume.
	public void CalcHalsteadVolume() {
		
		// Get length and volume.
		halLength = getHalsteadLength();
	    halVocabulary = getHalsteadVocabulary();
	    
	    // calculate volume.
	    double vocabLog = Math.log(halVocabulary) / Math.log(2);
	    halsteadVolume = halLength * vocabLog;
	}
	
	@Override
	public void finishTree(DetailAST rootAST) {
		
		// finish other checks we depend on.
		halsteadLength.finishTree(rootAST);
		halsteadVocabulary.finishTree(rootAST);

		try {
			log(0, "Halstead Volume: " + halsteadVolume);
		} catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	// Public getter for the halstead volume.
	public double getHalsteadVolume() {
		return halsteadVolume;
	}

	// getters for   Halstead length
	public int getHalsteadLength() {
		return halsteadLength.getHalsteadLength();
	}

	// getter for Halstead vocabulary.
	public int getHalsteadVocabulary() {
		return halsteadVocabulary.getHalsteadVocabulary();
	}

	@Override
	public int[] getDefaultTokens() {
		
		// Get halstead vocab and  length 
		int halsteadLengths = halsteadLength.getDefaultTokens().length;
		int halsteadVocabularyLength = halsteadVocabulary.getDefaultTokens().length;
		
		// create new list to return 
		int[] newDefaultTokens = new int[halsteadLengths +halsteadVocabularyLength];
		
		// assign local variables to length and vocab arrays for readability purpose.
		int[] lengthTokens = halsteadLength.getDefaultTokens();
		int[] vocabTokens = halsteadVocabulary.getDefaultTokens();
		
		// Merge the two arrays into new array.
		System.arraycopy(lengthTokens, 0, newDefaultTokens , 0, halsteadLengths);
		System.arraycopy(vocabTokens, 0, newDefaultTokens, 0, halsteadVocabularyLength);
		
		return newDefaultTokens;
	}

	@Override
	public int[] getAcceptableTokens() {
		// Get halstead vocab and  length 
		int halsteadLengths = halsteadLength.getAcceptableTokens().length;
		int halsteadVocabularyLength = halsteadVocabulary.getAcceptableTokens().length;
		
		// create new list to return 
		int[] newAcceptableTokens = new int[halsteadLengths +halsteadVocabularyLength];
		
		// assign local variables to length and vocab arrays for readability purpose.
		int[] lengthTokens = halsteadLength.getAcceptableTokens();
		int[] vocabTokens = halsteadVocabulary.getAcceptableTokens();
		
		// Merge the two arrays into new array.
		System.arraycopy(lengthTokens, 0, newAcceptableTokens , 0, halsteadLengths);
		System.arraycopy(vocabTokens, 0, newAcceptableTokens, 0, halsteadVocabularyLength);
		
		return newAcceptableTokens;
	}

	@Override
	public final int[] getRequiredTokens() {
		
		// Get halstead vocab and  length 
		int halsteadLengths = halsteadLength.getRequiredTokens().length;
		int halsteadVocabularyLength = halsteadVocabulary.getRequiredTokens().length;
		
		// create new list to return 
		int[] newRequiredTokens = new int[halsteadLengths +halsteadVocabularyLength];
		
		// assign local variables to length and vocab arrays for readability purpose.
		int[] lengthTokens = halsteadLength.getRequiredTokens();
		int[] vocabTokens = halsteadVocabulary.getRequiredTokens();
		
		// Merge the two arrays into new array.
		System.arraycopy(lengthTokens, 0, newRequiredTokens , 0, halsteadLengths);
		System.arraycopy(vocabTokens, 0, newRequiredTokens, 0, halsteadVocabularyLength);
		
		return newRequiredTokens;
	}

	
	// function to create an ArrayList from an integer array
	private ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i : array) {
			returnList.add(i);
		}
		return returnList;
	}
}
