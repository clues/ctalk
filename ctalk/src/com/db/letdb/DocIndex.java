package com.db.letdb;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;

import com.helper.ByteHelper;

/**
 * @created: Feb 29, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class DocIndex {

	public static int LENGTH_INDEX = 40;
	
	public static Hashtable<ByteArray,DocIndex> indexTable = new Hashtable<ByteArray,DocIndex>();
	
	//doc type,class type
	private String clazz;

	//doc length take 2 bytes
	private int length;
	
	//doc begin position,take 4 bytes
	private int offset;
	
	//doc store in fileName,take 16 bytes
	private String fileName;
	
	//md5 key,take 16 bytes
	private ByteArray md5key;
	
	
	public DocIndex(){
		
	}
	
	public DocIndex(byte[] bytes){
		this.md5key = new ByteArray(Arrays.copyOfRange(bytes, 0, 16));
		this.length = ByteHelper.getInt(bytes, 16);
		this.offset = ByteHelper.getInt(bytes, 20);
		this.fileName = ByteHelper.getString(bytes, 24);
	}
	
	public DocIndex(ByteArray md5key,int length,int offset,String fileName){
		this.md5key = md5key;
		this.length = length;
		this.offset = offset;
		this.fileName = fileName;
		
	}
	
	
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(this.getMd5key().getArray());
		out.write(ByteHelper.getBytes(this.getLength()));
		out.write(ByteHelper.getBytes(this.getOffset()));
		out.write(ByteHelper.getBytes(this.getFileName()));
		for (int i = out.size();i < LENGTH_INDEX;i++){
			out.write(0);
		}
		return out.toByteArray();
	}
	
	//flush hashtable to disk
	public static int sync() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (Iterator<ByteArray> it = indexTable.keySet().iterator();it.hasNext();){
			ByteArray md5key = it.next();
			out.write(((DocIndex)indexTable.get(md5key)).getBytes());
		}
		
		LetdbFile.docindexFile.delete();
		LetdbFile.docindexFile.createNewFile();
		OutputStream os = new FileOutputStream(LetdbFile.docindexFile);
		os.write(out.toByteArray());
		os.flush();
		if (os != null)
			os.close();
		return out.toByteArray().length;
	}
	
	public static DocIndex getDocIndex(ByteArray md5key){
		return (DocIndex)indexTable.get(md5key);
	}
	
	public static void updateIndex(){
		
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

	public ByteArray getMd5key() {
		return md5key;
	}

	public void setMd5key(ByteArray md5key) {
		this.md5key = md5key;
	}
	
}
