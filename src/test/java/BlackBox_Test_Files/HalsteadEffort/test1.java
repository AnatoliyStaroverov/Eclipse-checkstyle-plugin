package BlackBox_Test_Files.HalsteadEffort;

//	Halstead Effort is the difficulty multiplied by the volume. 
// Effort = DV. Effort was intended 
// as a suggestion for how long code review might take [1,2]
public class test1 {

	public void test() {
		
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
