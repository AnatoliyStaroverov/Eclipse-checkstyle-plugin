package BlackBox_Tests;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import Checks.HalsteadEffortCheck;
import Test_Engine.TestEngine;

public class HalsteadEffortTest {

	String projectPath = "/src/test/java/BlackBox_Test_Files/HalsteadEffort/";
	String filePath = System.getProperty("user.dir") + projectPath ;
	
	@Test  // Tests Halstead volume
	public void EffortTest1() throws IOException {
		HalsteadEffortCheck check = new HalsteadEffortCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try { 
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(182.66, check.getHalsteadVolume(),0.1); 
    }
	
	
	@Test // Test Halstead difficulty. 
	public void LengthTest2() throws IOException {
		HalsteadEffortCheck check = new HalsteadEffortCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
		
			e.printStackTrace();
		}

		assertEquals( 7.87, check.getHalsteadDifficulty(),0.1); 
    }
	
	
	@Test  // Tests the overall effort
	public void LengthTest3() throws IOException {
		HalsteadEffortCheck check = new HalsteadEffortCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(1438.45, check.getHalsteadEffort(),0.1); 
    }
}
