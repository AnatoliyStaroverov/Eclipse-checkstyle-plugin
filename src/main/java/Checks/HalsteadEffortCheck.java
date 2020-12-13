package Checks;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadEffortCheck  extends AbstractCheck{
	
	private double halsteadEffort;
	
     int[] HalsteadEfforttokens = { 
			
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

	


	private HalsteadDifficultyCheck halsteadDifficulty = new HalsteadDifficultyCheck();
	private HalsteadVolumeCheck halsteadVolume = new HalsteadVolumeCheck();

	private ArrayList<Integer> difficultyTokens = arrayToList(halsteadDifficulty.getDefaultTokens());
	private ArrayList<Integer> volumeTokens = arrayToList(halsteadVolume.getDefaultTokens());

	@Override // initialized other checks.
	public void beginTree(DetailAST rootAST) {
		halsteadDifficulty.beginTree(rootAST);
		halsteadVolume.beginTree(rootAST);
	}

	@Override // call  halsteadDifficulty and halsteadVolume tokens
	public void visitToken(DetailAST ast) {
		
		// halsteadDifficulty token call
		if (difficultyTokens.contains(ast.getType())) { 
			halsteadDifficulty.visitToken(ast);
		}
		
		// halsteadVolume token call
		if (volumeTokens.contains(ast.getType())) { 
			halsteadVolume.visitToken(ast);
		}
	}

	
	// Function to calculate Halstead effort.
	public double CalcHalsteadEffort() {
		
		double difficulty = getHalsteadDifficulty(); 
		double volume = getHalsteadVolume();

		halsteadEffort = difficulty * volume; 
		
		return halsteadEffort;
	}
	@Override  // Display results here 
	public void finishTree(DetailAST rootAST) {
		
		// Conclude other checks to calculate effort.
		halsteadDifficulty.finishTree(rootAST);
		halsteadVolume.finishTree(rootAST);
		
		double answer = CalcHalsteadEffort();

		try { 
			log(0, "Halstead Effort: " + answer );
		} catch (NullPointerException e) {
			System.out.println("Error from treewalker!");
		}
	}

	// Public getter for the halstead Effort.
	public double getHalsteadEffort() {
		return halsteadEffort;
	}

	// getters for getHalsteadDifficulty()
	public double getHalsteadDifficulty() {
		return halsteadDifficulty.getHalsteadDifficulty();
	}

	// getters for getHalsteadVolume()
	public double getHalsteadVolume() {
		return halsteadVolume.getHalsteadVolume();
	}

	//token types from checks that are depending on
	@Override
	public int[] getDefaultTokens() {
		return HalsteadEfforttokens;
	}

	@Override
	public int[] getAcceptableTokens() {
		return HalsteadEfforttokens;
	}

	@Override
	public final int[] getRequiredTokens() {
		return HalsteadEfforttokens;
	}

	private ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i : array) {
			returnList.add(i);
		}
		return returnList;
	}

}
