package BlackBox_Test_Files.HalsteadVolume;

public class test2 {

	// (Halstead Vocabulary) is the sum of 
	// the number of unique operators and unique operands [1,2]
	
	
	// (Halstead Volume) is the program length (N) 
	// times the log2 of the program vocabulary (n) [1,2] :
	// Volume = N log2 n 
	// Volume = (length) log(vocabulary)
	
	
	public static void main() {
		
	float oper1 = 23;
	float oper2 = 25;
	float oper32 = 56;
	float oper12 = 23;
	float oper24 = 25;
	float oper34 = 56;
	float oper15 = 23;
	float oper26 = 25;
	float oper36 = 56;
	
	oper1 = oper2 / oper1;
	oper2 = oper1 + oper12;
	oper32 = oper15 + oper36;
	oper26 = oper34 + oper24;
	oper26 = oper32 +oper26;
	
	
	
	
	}
	
}
