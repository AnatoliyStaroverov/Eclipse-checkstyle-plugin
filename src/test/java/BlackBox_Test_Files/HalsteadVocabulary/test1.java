package BlackBox_Test_Files.HalsteadVocabulary;   //operand 1

/* Halstead Vocabulary is the sum of the number
 *  of unique operators and unique operands [1,2] */
 
public class test1 { // operand 2
 public void HalsteadVocabulary() { // operand 3 - duplicate function name )
			
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
	
	// UNique operands = 12
	// unique operators = 7
	// Halstead vocabulary = 19
			 
	}

}