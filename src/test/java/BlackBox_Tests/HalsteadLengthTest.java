package BlackBox_Tests;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import Checks.HalsteadLengthCheck;
import Test_Engine.TestEngine;

public class HalsteadLengthTest {
	
	String projectPath = "/src/test/java/BlackBox_Test_Files/HalsteadLength/";
	String filePath = System.getProperty("user.dir") + projectPath ;
	
	@Test //  Tests the operands count in halstead length.
	public void LengthTest1() throws IOException {
		HalsteadLengthCheck check = new HalsteadLengthCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(12, check.getOperandCount()); 
    }
	
	
	@Test // Tests the operators count in halstead length.
	public void LengthTest2() throws IOException {
		HalsteadLengthCheck check = new HalsteadLengthCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
		
			e.printStackTrace();
		}

		assertEquals(4, check.getOperatorCount()); 
    }
	
	
	@Test // Tests the Halstead length of a file.
	public void LengthTest3() throws IOException {
		HalsteadLengthCheck check = new HalsteadLengthCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(12, check.getHalsteadLength()); 
    }

}
