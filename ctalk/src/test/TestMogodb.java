package test;

import com.Msg;
import com.db.MongoDB;

public class TestMogodb {

   //use time ms: 5489
	public static void main(String[] args){
//		long start = System.currentTimeMillis();
//		Msg msg = new Msg();
//		msg.setUsername("root");
//		msg.setTime("2011-10-11 02:31:21");
//		msg.setMsg("db insert operate test.......");
//		for(int i=0;i<100000;i++){
//			MongoDB.getInstance().save(msg);
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("use time ms: "+ (end-start));
		
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.arch"));
		System.out.println(System.getProperty("os.version"));
	}
}
