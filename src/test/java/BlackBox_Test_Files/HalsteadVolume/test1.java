package BlackBox_Test_Files.HalsteadVolume;  //operand 2

/* Halstead Volume is the program length (N) times
 * the log2 of the program vocabulary (n) [1,2] : 
 *  Volume = N log2 n   */

public class test1 { // operand 3
	public void HalsteadVolume() {  //operand 4
		
		/* operand 5*/	int temp2 = 12;  //operator 1
		/*operand 6*/	int temp3 = 13;  
		/*operand 8 */	float temp4 = 14; 
		/*operand 10 */	double temp23 = 12.5;  
		/* operand 12*/	boolean temp = true;  
			 
		/**/	 temp23 = temp23 / temp4; // operator 2
		/**/	 temp2 = temp2 + temp3; // operator 3
		/**/	 temp3 = temp2 * temp3; // operator 4
		/**/	 temp23 = temp2 % temp3; // operator 5
		/**/	 temp =  temp == !true; // operator 7 (two here)
		
		// Unique operands = 12
		// unique operators = 7
		// Halstead vocabulary = 19
		
		// total operands =  24
		// total operators = 16
		// Halstead length = 24 + 16 = 43
		
		//   Volume =  Halstead-Length  log2 (Halstead-vocab)
		//   Volume  = 43log2(19) = 182.66
		
	}
}
