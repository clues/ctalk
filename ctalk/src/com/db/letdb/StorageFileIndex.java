package com.db.letdb;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @created: Mar 2, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class StorageFileIndex {

	public static long SIZE_DEFAULT_MAX = 1024*1024;
	
	public static String FILENAME_DEFAULT = "letdb";
	
	public static Map<String,StorageFileIndex> indexMap = new HashMap<String,StorageFileIndex>();

	public static int STATUS_NEW = 0;

	public static int STATUS_OLD = 1;

	public static int STATUS_UNLOAD = -1;
	
	private String fileName;
	
	private long lastModifyTime;
	
	private long size;
	
	private int status;
	
	private File fd;
	
	public StorageFileIndex(){
		
	}
	
	public StorageFileIndex(File file){
		this.fileName = file.getName();
		this.lastModifyTime = file.lastModified();
		this.size = file.getTotalSpace();
		this.fd = file;
		if (this.getSize() < SIZE_DEFAULT_MAX){
			this.status = STATUS_NEW;
		}else{
			this.status = STATUS_OLD;
		}
	}
	
	public StorageFileIndex getUseableFile() throws IOException{
		for (Iterator<StorageFileIndex> it = indexMap.values().iterator();it.hasNext();){
			StorageFileIndex index = it.next();
			if (index.getStatus() == STATUS_NEW){
				return index;
			}
		}
		return createNewStoreFile();
	}
	
	public static int loadIndex(){
		indexMap.clear();
		int count = 0;
		File root = new File(LetdbFile.DbRoot);
		File[] list = root.listFiles();
		for (File file : list){
			if (file.isFile() && file.getName().startsWith("letdb")){
				StorageFileIndex sindex = new StorageFileIndex(file);
				indexMap.put(file.getName(), sindex);
				count++;
			}
		}
		return count;
	}
	
	public void updateIndex(File file){
		StorageFileIndex sindex = new StorageFileIndex(file);
		indexMap.put(file.getName(), sindex);
	}
	
	public static File  getFD(String fileName){
		StorageFileIndex index = indexMap.get(fileName);
		return index.getFd();
	}
	
	
	private StorageFileIndex createNewStoreFile() throws IOException{
		File file = new File(LetdbFile.DbRoot+FILENAME_DEFAULT+System.currentTimeMillis());
		if ( file.exists()){
			file.delete();
		}
		file.createNewFile();
		StorageFileIndex sindex = new StorageFileIndex(file);
		indexMap.put(file.getName(), sindex);
		return sindex;
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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
