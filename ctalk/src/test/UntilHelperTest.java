package test;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.Line;
import com.helper.UntilHelper;

public class UntilHelperTest {

	private static byte[] b = null;
	
	@Before
	public void tearUp(){ 
		b = ("sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD" +
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
"sfasjf sdfasfi safdasfsf   dajf  kasfi jwlfja  sjflsadf jsadf ljsaldfA cx FAF FSAF  SAFD SAFSDFFD"+
				"FSAFSFD ASFSA DFSAFASFDfaskj foafasj foasjfasjf saffa siasfj asfasf isfjalssssssfasf" +
				"asfdasas fasfaiwarf asfwoej 12e4 sarewrw sarf3er f waer a fsa    saf f asf asaf " +
				" fsadf fasew a asfsare fdaste stesagas tew afdasfasft  sref  saf  sfdasfe     sdf ").getBytes();
	}
	
	@Test
	public void testCutBy() {
		List<ByteArrayOutputStream> list = UntilHelper.cutby(32, " -login   chao    chao ".getBytes());
		for (ByteArrayOutputStream byteArrayOutputStream : list) {
			System.out.println(byteArrayOutputStream.toString());
		}
	}
	
	@Test
	public void testRemoveBy() {

	}
	
	@Test
	public void testGetLineTime() {
		long star = System.currentTimeMillis();
		Line.getLine(b);
		long end = System.currentTimeMillis();
		System.out.println("use ms: " +(end - star));
		
	}
	
	@Test
	public void testCutByTime() {
		long star = System.currentTimeMillis();
		UntilHelper.cutby(32, b);
		long end = System.currentTimeMillis();
		System.out.println("use ms: " +(end - star));
	}

}
