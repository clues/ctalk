package test;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.db.letdb.LetFile;

public class TestLetFile {

	private LetFile letFile = null;
	
	@Before
	public void setUp() throws Exception {
		letFile = new LetFile();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testFileExist() {
		try{
			letFile.init();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
}
