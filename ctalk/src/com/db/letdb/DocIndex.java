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

	public static int LENGTH_INDEX = 128;
	
	public static Hashtable<Long,DocIndex> indexTable = new Hashtable<Long,DocIndex>();
	
	//doc type,class type
	private String clazz;

	//doc length take 2 bytes
	private int length;
	
	//doc begin position,take 4 bytes
	private long offset;
	
	//doc store in fileName,take 16 bytes
	private String fileName;
	
	private Long id;
	
	
	public DocIndex(){
		
	}
	
	public DocIndex(byte[] bytes){
		this.id = ByteHelper.getLong(bytes, 0);
		this.offset = ByteHelper.getLong(bytes, 8);
		this.length = ByteHelper.getInt(bytes, 16);
		this.fileName = ByteHelper.getString(bytes, 20,18);
		this.clazz = ByteHelper.getString(bytes,38);
	}
	
	public DocIndex(Long id,long offset,int length,String fileName,String clazz){
		this.id = id;
		this.length = length;
		this.offset = offset;
		this.fileName = fileName;
		this.clazz = clazz;
		
	}
	
	
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(ByteHelper.getBytes(this.getId()));
		out.write(ByteHelper.getBytes(this.getOffset()));
		out.write(ByteHelper.getBytes(this.getLength()));
		out.write(ByteHelper.getBytes(this.getFileName()));
		out.write(ByteHelper.getBytes(this.getClazz()));
		for (int i = out.size();i < LENGTH_INDEX;i++){
			out.write(0);
		}
		return out.toByteArray();
	}
	
	//flush hashtable to disk
	public static int sync() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (Iterator<Long> it = indexTable.keySet().iterator();it.hasNext();){
			Long id = it.next();
			out.write(((DocIndex)indexTable.get(id)).getBytes());
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
	
	public static DocIndex getDocIndex(Long id){
		return indexTable.get(id);
	}
	
	public static int deleteIndex(Long id){
		int code = 0;
		DocIndex index = indexTable.get(id);
		if (index != null){
			indexTable.remove(id);
			code = 1;
		}
		return code;
	}
	
	public static void updateIndex(DocIndex index){
		DocIndex tmp = getDocIndex(index.getId());
		if (tmp != null){
			indexTable.remove(tmp.getId());
		}
		indexTable.put(index.getId(), index);
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}
	
}
