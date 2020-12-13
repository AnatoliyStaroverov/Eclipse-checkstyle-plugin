package BlackBox_Test_Files.HalsteadDifficulty;  // operand 2

public class test1 {  // operand 3
	
public void HalsteadDifficulty() {  //operand 4
		
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
		
		
//Halstead Difficulty = ((.5) * unique operators * total operands ) / unique operands 
		
		// Unique operands = 12
		//// unique operators = 7
		// (.5) Unique operators = 3.5
		// total operands =  27

		// HAlDif = (3.5)(27) / (12) = 7.87
		
	}

}
