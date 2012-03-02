package com.db.letdb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.helper.ByteHelper;

/**
 * @created: Mar 2, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class StorageFileIndex {

	public static int SIZE_DEFAULT = 1024*1024;
	
	public static String FILENAME_DEFAULT = "letdb";
	
	public static Map<Integer,StorageFileIndex> indexMap = new HashMap<Integer,StorageFileIndex>();
	
	public static int LENGTH_INDEX = 32;
	
	public static int STATUS_NEW = 0;
	
	public static int STATUS_OLD = 1;
	
	public static int STATUS_UNLOAD = -1;
	
	private int id;
	
	private String fileName;
	
	private long lastModifyTime;
	
	private int size;
	
	private int status;
	
	private File fd;
	

	public static StorageFileIndex getInstance(byte[] bytes){
		StorageFileIndex index = new StorageFileIndex();
		index.setId(ByteHelper.getInt(bytes, 0));
		index.setSize(ByteHelper.getInt(bytes, 4));
		index.setStatus(ByteHelper.getInt(bytes, 8));
		index.setLastModifyTime(ByteHelper.getInt(bytes, 12));
		index.setFileName(ByteHelper.getString(bytes, 20));
		index.setFd(null);
		
		File file = new File(LetdbFile.DbRoot+index.getFileName());
		if (file.exists()){
			if (index.getLastModifyTime() == file.lastModified()){
				index.setFd(file);
				return index;
			}
		}
		return null;
	}
	
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(ByteHelper.getBytes(this.getId()));
		out.write(ByteHelper.getBytes(this.getSize()));
		out.write(ByteHelper.getBytes(this.getStatus()));
		
		out.write(ByteHelper.getBytes(this.getLastModifyTime()));
		out.write(ByteHelper.getBytes(this.getFileName()));
		for (int i = out.size();i < LENGTH_INDEX;i++){
			out.write(0);
		}
		return out.toByteArray();
	}
	
	//flush map to disk
	public static int sync() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (Iterator<Integer> it = indexMap.keySet().iterator();it.hasNext();){
			out.write(((StorageFileIndex)indexMap.get(it.next())).getBytes());
		}
		
		LetdbFile.storeIndexFile.delete();
		LetdbFile.storeIndexFile.createNewFile();
		OutputStream os = new FileOutputStream(LetdbFile.storeIndexFile);
		os.write(out.toByteArray());
		os.flush();
		if (os != null)
			os.close();
		return out.toByteArray().length;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public File getFd() {
		return fd;
	}

	public void setFd(File fd) {
		this.fd = fd;
	}
}
