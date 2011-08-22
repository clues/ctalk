package com;

import java.io.ByteArrayOutputStream;

public class Line {

	public static String HELP_CMD = "-help";//1
	public static String REG_CMD = "-reg";//2
	public static String LOGIN_CMD ="-login";//3
	public static String QUIT_CMD="-quit";//4
	public static String ONLINER_CMD="-onliner";//5
	public static String HISTORY_CMD ="-history";//6
	
	private String cmd = null;
	private String msg;
	private int state = 0;  // -1#wrong  0#ok



	public static String welcomeInfo = "\t*****************************************\r\n\r\n\r\n"
															+"\t                 welcome to cTalk                  \r\n\r\n\r\n"
			                                                +"\t*****************************************\r\n"
															+"\t      input -help get more command          \r\n";
	
	public static String helpInfo = "\t-reg username password  repassword  #register user\r\n"
													+"\t-login username password #login bbs\r\n"
													+"\t-onliner #get all current user\r\n"
													+"\t-quit  #quit cTalk  \r\n"
													+"\t-history  #view all talk record";
	
	
	public static String wrongFormatInfo = "your command have wrong format!!";
	
	
	
	public static Line getLine(byte[] b){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Line line = new Line();
		boolean isHead32 = true;
		for (int i=0;i < b.length;i++){
			if (line.getCmd() != null){
				baos.write(b[i]);
			}else{
				if (b[i] == 32){
					if (!isHead32){
						line.setCmd(baos.toString());
						baos.reset();
					}
				}else{
					baos.write(b[i]);
					isHead32 = false;
				}
			}
			
		}
		if (line.getCmd() == null){
			line.setCmd(baos.toString());
			line.setMsg("");
		}else{
			line.setMsg(baos.toString());
		}
		baos.reset();
		
		return filiterLine(line);
	}
	
	public static Line filiterLine(Line line){
		if (line.getCmd() != null){
			if (line.getCmd().equals(HELP_CMD) || line.getCmd().equals(QUIT_CMD) ||line.getCmd().equals(ONLINER_CMD) 
					||line.getCmd().equals(HISTORY_CMD)){
				if (line.getMsg() == null || line.getMsg().trim().equals("")){
					//
				}else{
					line.setState(-1);
				}
			}else if (line.getCmd().equals(LOGIN_CMD)){
				if (line.getMsg() == null || line.getMsg().split(" ").length != 2){
					line.setState(-1);
				}
			}else if (line.getCmd().equals(REG_CMD)){
				if (line.getMsg() == null || line.getMsg().split(" ").length != 3){
					line.setState(-1);
				}
			}else {
				line.setMsg(line.getCmd()+" "+line.getMsg());
				line.setCmd(null);
			}
		}
		if(line.getState() == -1){
			line.setMsg(wrongFormatInfo);
		}
		return line;
	}
	
	
//	public static String line2str(Line line){
//		String str = "";
//		if(line.getCmd() == 1){
//			str = helpInfo;
//		}else if(line.getCmd() == 0){
//			str = line.getMsg();
//		}
//		return str;
//	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public static void main(String[] args){

		System.out.println("aa.cc.pp".replace(".", "_"));
	}
}


