package com;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.db.MongoDB;
import com.db.MsgService;

public class CTalk {
	private static int DEFAULT_PORT = 4017;
	private static final Log log = LogFactory.getLog(CTalk.class);
	private int port;
	public CTalk(){
		this.port = DEFAULT_PORT;
	}

	public CTalk(int _port){
		this.port = _port;
	}
	
	public void doInt(){
		log.debug("try listen on port: "+ this.port);
		ServerSocket server = null;
		try{
			new MongoDB().connect();
			server = new ServerSocket(this.port);
			log.info("listen on port: "+this.port);
			while(true){
				Socket sock = server.accept();
				log.info("receive a new connect from addrss: "+sock.getInetAddress()+"["+sock.getPort()+"]");
				Thread thread = new Thread(new UserThread(sock));
				thread.start();
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new CTalk().doInt();
	}
}

class UserThread implements Runnable{

	private static final Log log = LogFactory.getLog(UserThread.class);
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private BufferedReader in = null;
	
	private BufferedOutputStream out = null;
	
	private Socket sock;
	
	public UserThread(Socket socket){
		try{
			sock = socket;
			this.in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			this.out =  new BufferedOutputStream(socket.getOutputStream());
			this.out.write(Line.welcomeInfo.getBytes());
			this.out.flush();
		}catch(IOException e){
			e.printStackTrace();
			log.error("ioexcpetion when link address: "+sock.getInetAddress()+"["+sock.getPort()+"]");
		}
	}
	public void run(){
		try{
			String strline = null;
			while((strline = this.in.readLine()) != null){
				Line line = Line.getLine(strline.getBytes());
				hander(line,out);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void hander(Line line,BufferedOutputStream out)
		throws IOException{
		if (line.getState() == -1){
			// wrong format command,will ingore lest hander
		}else if (line.getCmd() == null){
			User user = User.getUserBySocket(sock);
			if (user != null){
				String time = format.format(new Date());
				//save normal talk record to db
				Msg msg = new Msg();
				msg.setTime(time);
				msg.setUsername(user.getName());
				msg.setMsg(line.getMsg());
				MongoDB.getInstance().save(msg);

				if (user.getReceiver() != null){
					boolean ok = GroupHander.flush2One(user.getReceiver(),"\r\n["+user.getName()+"] "+time+":  " +line.getMsg());
					if (!ok){
						line.setMsg("send failed,may be" +user.getReceiver() +" off or socket exception!!!");
					}else{
						return;
					}
				}else{
					GroupHander.flush2group("\r\n["+user.getName()+"] "+time+":  " +line.getMsg());
					return;
				}
				
			}
		}else if (line.getCmd().equals(line.HELP_CMD)){
			line.setMsg(line.helpInfo);
		}else if (line.getCmd().equals(line.TALK_CMD)){
			User user = User.getUserBySocket(sock);
			if (user != null){
				if (line.getMsg() == null){
					user.setReceiver(null);
				}else {
					if (User.onlinerMap.get(line.getMsg()) != null){
						user.setReceiver(line.getMsg());
					}else{
						line.setMsg(line.getMsg() + " not exist or not login,switch faild...");
						user.setReceiver(null);
					}
				}
				User.updateUserInOnlinerMap(user);
				line.setMsg("switch to " +(user.getReceiver() == null?"ALL":user.getReceiver()));
			}else{
				line.setMsg(" please login first!!");
			}
			
		}else if (line.getCmd().equals(line.HISTORY_CMD)){
			User user = User.getUserBySocket(sock);
			if (user != null){
				line.setMsg(MsgService.getAllMsgByUser(user));
			}else{
				line.setMsg(" please login first!!");
			}
		}else if (line.getCmd().equals(line.CVIEW_CMD)){
			User user = User.getUserBySocket(sock);
			if (user != null){
				if (user.getState() == 0){
					user.setState(-1);
					
				}else{
					user.setState(0);
				}
				User.updateUserInOnlinerMap(user);
				line.setMsg("View "+ (user.getState() ==0?"ON":"OFF"));
			}else{
				line.setMsg(" please login first!!");
			}
		}else if (line.getCmd().equals(line.STATE_CMD)){
			User user = User.getUserBySocket(sock);
			if (user != null){
				StringBuffer sb = new StringBuffer("Current State:");
				sb.append("\r\nlogin name: ").append(user.getName());
				sb.append("\r\nreceiver:").append(user.getReceiver()==null?"ALL":user.getReceiver());
				sb.append("\r\nview:").append(user.getState() == 0?"ON":"OFF");
				line.setMsg(sb.toString());
			}else{
				line.setMsg(" please login first!!");
			}
		}else if(line.getCmd().equals(line.QUIT_CMD)){
			User user = User.removeUserBySocket(sock);
			if (user != null){ //this user have login
				GroupHander.flush2group("\r\n- - - - -[user: "+user.getName()+"] from "+user.getIp()+" ["+user.getPort()+"]");
			}
			log.info(user.getName() + " quit ctalk,socket will closed");
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (sock != null)
				sock.close();
			return;
		}else if(line.getCmd().equals(line.LOGIN_CMD)){
			String[] loginfo = line.getMsg().split(" ");
			User user = new User();
			user.setName(loginfo[0]);
			user.setPassword(loginfo[1]);
			if (MongoDB.getInstance().findOne(user) == null){
				line.setMsg("user [" +loginfo[0]+"] not exist!!");
			}else{
				if (User.get(user.getName()) != null){
					line.setMsg(user.getName()+" already login!!");
				}else{
					user.setSock(sock);
					user.setIp(sock.getRemoteSocketAddress().toString());
					user.setPort(sock.getPort());
					GroupHander.flush2group("\r\n+++++[user: "+user.getName()+"] from "+user.getIp()+" ["+user.getPort()+"]");
					User.put(user);
					line.setMsg(User.getOnlinerStr());
					log.info(user.getName()+" login ctalk");
				}
			}
			
		}else if(line.getCmd().equals(line.ONLINER_CMD)){
			line.setMsg(User.getOnlinerStr());
		}else if(line.getCmd().equals(line.REG_CMD)){
			String[] reginfo = line.getMsg().split(" ");
			User user = new User();
			user.setName(reginfo[0]);
			if (MongoDB.getInstance().findOne(user) != null){
				line.setMsg("user already exist!!");
			}else{
				 if (!reginfo[1].equals(reginfo[2])){
					 line.setMsg("second password not equal first!!");
				 }else{
					 user.setPassword(reginfo[1]);
					 MongoDB.getInstance().save(user);
					 line.setMsg("register success..");
					 log.info(user.getName()+" register ctalk");
				 }
				
			}
		}
		out.write((line.getMsg()+"\r\ncTalk>").getBytes());
		out.flush();
	}
	
}
