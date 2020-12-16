package Checks;
import com.puppycrawl.tools.checkstyle.api.*;
import java.util.ArrayList;

public class HalsteadVolumeCheck extends AbstractCheck{

	 int[] halsteadVolumeTokens = { 
				
				/* Unary Operator Type*/	
				TokenTypes.POST_INC,TokenTypes.POST_DEC,TokenTypes.DEC,TokenTypes.INC,
				TokenTypes.LNOT,TokenTypes.BNOT,TokenTypes.UNARY_MINUS,TokenTypes.UNARY_PLUS,
				
				/* Arithmetic Operator type */
				TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,TokenTypes.PLUS,TokenTypes.MINUS,
				TokenTypes.BSR,TokenTypes.SR,TokenTypes.SL,
				
				/* Relational Operator type */
				TokenTypes.LT,TokenTypes.GT,TokenTypes.LE,TokenTypes.GE,
				TokenTypes.LITERAL_INSTANCEOF,TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,
				
				/* Bitwise */
				TokenTypes.BAND,TokenTypes.BXOR,TokenTypes.BOR,
				
				/* Logical Operator type */
				TokenTypes.LAND,TokenTypes.LOR,
				
				/* Ternary  Operator type */
				TokenTypes.QUESTION,TokenTypes.EOF,
				
				/* Assignment  Operator type  */
				TokenTypes.ASSIGN,TokenTypes.BAND_ASSIGN,TokenTypes.BOR_ASSIGN,
				TokenTypes.BSR_ASSIGN,TokenTypes.BXOR_ASSIGN,TokenTypes.DIV_ASSIGN,
				TokenTypes.MINUS_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.PLUS_ASSIGN,
				TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.STAR_ASSIGN,
				
				// operands 
				TokenTypes.IDENT, 
				TokenTypes.NUM_DOUBLE,
				TokenTypes.NUM_FLOAT,
				TokenTypes.NUM_INT,
				TokenTypes.NUM_LONG 
				
		};
		
	 
	private double halsteadVolume;
	private int halLength;
	private int halVocabulary;

	private HalsteadLengthCheck halsteadLength = new HalsteadLengthCheck();
	private HalsteadVocabularyCheck halsteadVocabulary = new HalsteadVocabularyCheck();

	private ArrayList<Integer> halsteadLengthTokens = arrayToList(halsteadLength.getDefaultTokens());
	private ArrayList<Integer> halsteadVocabularyTokens = arrayToList(halsteadVocabulary.getDefaultTokens());

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
		if ( halsteadLengthTokens.contains(ast.getType())) {
			halsteadLength.visitToken(ast);
		}
		
		// Condition for operator type
		if (halsteadVocabularyTokens.contains(ast.getType())) {
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
		
		CalcHalsteadVolume();

		try {
			log(0, "Halstead Volume: " + halsteadVolume);
		} catch (NullPointerException e) {
			System.out.println("Error from treewalker!");
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
		return  halsteadVolumeTokens;
	}

	@Override
	public int[] getAcceptableTokens() {
		return  halsteadVolumeTokens;
	}

	@Override
	public final int[] getRequiredTokens() {
		return  halsteadVolumeTokens;
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
