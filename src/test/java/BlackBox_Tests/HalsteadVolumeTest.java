package BlackBox_Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import Checks.HalsteadVolumeCheck;
import Test_Engine.TestEngine;

public class HalsteadVolumeTest {

			String projectPath = "/src/test/java/BlackBox_Test_Files/HalsteadVolume/";
			String filePath = System.getProperty("user.dir") + projectPath ;
			
			@Test // test single and block comments in one function.
			public void VolumeTest1() throws IOException {
				HalsteadVolumeCheck check = new HalsteadVolumeCheck(); 
				TestEngine test = new TestEngine(filePath , "test1.java", check); 
				
				try {
					test.RunTestCase(); 
				} catch (CheckstyleException e) {
					
					e.printStackTrace();
				}

				assertEquals(11.023, check.getHalsteadVolume(),0.1); 
		    }
}
