package Checks;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadEffortCheck  extends AbstractCheck{
	
	private double halsteadEffort;

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

		try { 
			log(0, "Halstead Effort: " + halsteadEffort);
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
		
		// Get Difficulty and Volume length 
		int halsteadDifficultyLength = halsteadDifficulty.getDefaultTokens().length;
		int halsteadVolumeLength = halsteadVolume.getDefaultTokens().length;
		
		// create new list to return 
		int[] newDefaultTokens = new int[halsteadDifficultyLength+ halsteadVolumeLength];
		
		// assign local variables to operand and operator arrays for readability purpose.
		int[] difficultyTokens = halsteadDifficulty.getDefaultTokens();
		int[] halsteadTokens = halsteadVolume.getDefaultTokens();
		
		// Merge the two arrays into new array.
		System.arraycopy(difficultyTokens, 0, newDefaultTokens , 0, halsteadDifficultyLength);
		System.arraycopy(halsteadTokens, 0, newDefaultTokens, 0, halsteadVolumeLength);
		
		return newDefaultTokens;
	}

	@Override
	public int[] getAcceptableTokens() {
		// Get Difficulty and Volume length 
		int halsteadDifficultyLength = halsteadDifficulty.getAcceptableTokens().length;
		int halsteadVolumeLength = halsteadVolume.getAcceptableTokens().length;
		
		// create new list to return 
		int[] newAcceptableTokens = new int[halsteadDifficultyLength+ halsteadVolumeLength];
		
		// assign local variables to operand and operator arrays for readability purpose.
		int[] difficultyTokens = halsteadDifficulty.getAcceptableTokens() ;
		int[] halsteadTokens = halsteadVolume.getAcceptableTokens() ;
		
		// Merge the two arrays into new array.
		System.arraycopy(difficultyTokens, 0, newAcceptableTokens , 0, halsteadDifficultyLength);
		System.arraycopy(halsteadTokens, 0, newAcceptableTokens, 0, halsteadVolumeLength);
		
		return newAcceptableTokens;
	}

	@Override
	public final int[] getRequiredTokens() {
		
		// Get Difficulty and Volume length 
		int halsteadDifficultyLength = halsteadDifficulty.getRequiredTokens().length;
		int halsteadVolumeLength = halsteadVolume.getRequiredTokens().length;
		
		// create new list to return 
		int[] newRequiredTokens = new int[halsteadDifficultyLength+ halsteadVolumeLength];
		
		// assign local variables to operand and operator arrays for readability purpose.
		int[] difficultyTokens = halsteadDifficulty.getRequiredTokens();
		int[] halsteadTokens = halsteadVolume.getRequiredTokens();
		
		// Merge the two arrays into new array.
		System.arraycopy(difficultyTokens, 0, newRequiredTokens , 0, halsteadDifficultyLength);
		System.arraycopy(halsteadTokens, 0, newRequiredTokens, 0, halsteadVolumeLength);
		
		return newRequiredTokens;
	}

	private ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i : array) {
			returnList.add(i);
		}
		return returnList;
	}

}
