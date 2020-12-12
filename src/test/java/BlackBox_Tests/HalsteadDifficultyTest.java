package BlackBox_Tests;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import Checks.HalsteadDifficultyCheck;
import Test_Engine.TestEngine;

public class HalsteadDifficultyTest {

	String projectPath = "/src/test/java/BlackBox_Test_Files/HalsteadDifficulty/";
	String filePath = System.getProperty("user.dir") + projectPath ;
	
	@Test //  Tests the unique operands count in halstead Difficulity.
	
	public void DiffTest1() throws IOException {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(12, check.getUniqueOperands(),0.1); 
    }
	
@Test //  Tests the  unique operators count in halstead Difficulity.
	
	public void DiffTest2() throws IOException {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(12, check.getOperands(),0.1); 
    }

@Test //  Tests  halstead Difficulity.

public void DiffTest3() throws IOException {
	HalsteadDifficultyCheck check = new HalsteadDifficultyCheck(); 
	TestEngine test = new TestEngine(filePath , "test1.java", check); 
	
	try {
		test.RunTestCase(); 
	} catch (CheckstyleException e) {
		
		e.printStackTrace();
	}

	assertEquals(12, check.getHalsteadDifficulty(),0.1); 
}
}
