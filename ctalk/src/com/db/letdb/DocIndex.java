package com.db.letdb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.helper.ByteHelper;

/**
 * @created: Feb 29, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class DocIndex {

	public static int LENGTH_DOCINDEX = 24;
	
	//doc type,class type
	private String clazz;

	//doc length take 2 bytes
	private int length;
	
	//doc begin position,take 4 bytes
	private int offset;
	
	//doc store in fileName,take 16 bytes
	private String fileName;
	
	
	public DocIndex(){
		
	}
	
	public DocIndex(byte[] bytes){
		this.length = ByteHelper.getInt(bytes, 0);
		this.offset = ByteHelper.getInt(bytes, 4);
		this.fileName = ByteHelper.getString(bytes, 8);
	}
	
	public DocIndex(int length,int offset,String fileName){
		this.length = length;
		this.offset = offset;
		this.fileName = fileName;
		
	}
	
	
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(ByteHelper.getBytes(this.getLength()));
		out.write(ByteHelper.getBytes(this.getOffset()));
		out.write(ByteHelper.getBytes(this.getFileName()));
		for (int i = out.size();i < LENGTH_DOCINDEX;i++){
			out.write(0);
		}
		return out.toByteArray();
	}
	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
