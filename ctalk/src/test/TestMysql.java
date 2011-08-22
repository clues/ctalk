package test;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class TestMysql {
    //condition 100000 records insert to mysql
	//use time ms: 1817487  insert one per time
	//use time ms: 2358  insert all one time
	public static void main(String[] args){
	     Connection conn=null;
         Statement  stmt=null;
         ResultSet  rs=null;
         int   rowcount=0;
         try{
        	 Class.forName("org.gjt.mm.mysql.Driver").newInstance();
         }catch(Exception e){
        	 e.printStackTrace();
         }
         try{
        	 String url = "jdbc:mysql://192.168.203.18:3306/test";
        	 String user = "root";
        	 String pwd = "root";
        	 conn = DriverManager.getConnection(url, user, pwd);
        	 stmt = (Statement)conn.createStatement();
         	StringBuffer sb = new StringBuffer("insert into message(name,time,msg) values('root','2011-10-11 02:13:21',' test insert db ....')");
        	 for(int i=0;i<100000;i++){
        		 sb.append(",('root','2011-10-11 02:13:21',' test insert db ....')");
    	    }
        	 
        	 long start  = System.currentTimeMillis();
        	// for(int i=0;i<100000;i++){
        		 stmt.executeUpdate(sb.toString());
        	// }
        	 long end = System.currentTimeMillis();
        	 System.out.print("use time ms: "+(end-start));
         }catch(Exception e){
        	 e.printStackTrace();
         }
         
	}
}
