package com.db.letdb.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @created: 2011-11-16
 * @author : jias chao<lino.chao@gmail.com>
 */
public class InWrite {

	private String path = "";
	private String fileName="";
	private BufferedReader read;
	private BufferedWriter write;
	
	public static int indexOfLine = 0;
	
	public InWrite(String _fileName) throws IOException{
		this.fileName = _fileName;
		openFile();
	}
	
	public void openFile() throws IOException{
		File file = new File(this.fileName);
		if (!file.exists()){
			file.createNewFile();
		}
		FileReader fr = new FileReader(file);
		FileWriter fw = new FileWriter(file,true);
		read = new BufferedReader(fr);
		write = new BufferedWriter(fw);
	}
	
	public void write(String context) throws IOException{
		write.write(context);
		write.newLine();
		write.flush();
	}
	
	public String readNextLine() throws IOException{
		read.skip(indexOfLine);
		indexOfLine = indexOfLine + 5;
		return read.readLine();
	}
}
