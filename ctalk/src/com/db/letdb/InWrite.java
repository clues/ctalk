package com.db.letdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InWrite {
	
	public static long write(File file,byte[] bin,boolean append) throws IOException{
		OutputStream os = new FileOutputStream(file,append);
		os.write(bin);
		os.flush();
		if (os != null)
			os.close();
		return file.length();
	}
	
	
	public static byte[] read(File file,long offset,int len) throws IOException{
		InputStream in = new FileInputStream(file);
		in.skip(offset);
		byte[] bin = new byte[len];
		in.read(bin);
		if (in != null)
			in.close();
		return bin;
	}
}
