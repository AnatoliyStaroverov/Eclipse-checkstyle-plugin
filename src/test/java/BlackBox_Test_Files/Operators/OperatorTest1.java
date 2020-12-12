package BlackBox_Test_Files.Operators;

// This tests unique operators 
public class OperatorTest1 {

	int temp2 = 12;  
	int temp3 = 13; 
	int temp4 = 14; 
	int temp41 = 14; 
	
	int temp5 = ~1;
	int temp6 = 25 | 25;
	int yemo = temp5 >>> 5;
	
	int[] myIntArray = new int[3]; 
	
	/* Arithmetic Operator type */
	int temp22 = 25 / 25;
	int temp33 = 25*25;
	int temp44 = 25-25;
	int temp55 = 25+25;
	int temp56 = 25 % 2;
	
	/* Relational Operator type */
	boolean temp66 = 25<20;
	boolean temp77 = 25>20;
	boolean temp88 = 25<=20;
	boolean temp99 = 25>=20;
	
	// unary 
	int temp23 = temp22++;
	int temp240  = temp22++;
	int temp001 = ++temp2;
	int temp0012 = +temp22;
	int temp0013 = -temp22;
	
	//logical 
	boolean temp100 = temp66  && temp77;
	boolean temp101 = temp66  || temp77;

	
	// TERARY : ? 
	int temp222 = temp100 ? 5 : 4;
	
	//assigment
	int temp200 = temp23+=5;
	int temp2001 = temp23*=5;
	int temp2002 = temp23-=5;
	 int temp300 = 25 & 25;
	 int temp3001 = temp300 << 5; 
     int temp3005 = temp300<<= 5;
     int temp30056 = temp300 >>= 5; 
     int temp3009 = temp300 >> 5; 
	
	 
	 // should return 61 operators and 25 unique ones.
	
}
