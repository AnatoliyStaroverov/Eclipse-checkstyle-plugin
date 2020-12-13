package BlackBox_Test_Files.HalsteadVolume;  //operand 2

/*
 * Halstead Volume is the program length (N) times
 * the log2 of the program vocabulary (n) [1,2] : 
 *  Volume = N log2 n 
 */
public class test1 { // operand 3
	public void TestHalsteadVolume() {  //operand 4
		
		/* operand 6*/	int temp2 = 12; // operator 1
		/*operand 7*/	int temp3 = 13;  
		/*operand 9 */	float temp4 = 14;
		/*operand 11 */	double temp23 = 12.5;  
		/* operand 13*/	boolean temp = true;  
			
		/**/	 temp23 = temp23 / temp4; // operator 2
		/**/	 temp2 = temp2 + temp3; // operator 3
		/**/	 temp3 = temp2 * temp3; // operator 4
		/**/	 temp23 = temp2 % temp3; // operator 5
		/**/	 temp =  temp == !true; // operator 7 (two here)
		
		// UNique operands = 13
		// unique operators = 7
		// Halstead vocabulary = 20
		
	}
}
