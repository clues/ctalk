package com.db.letdb;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.helper.ByteHelper;

/**
 * @created: Feb 29, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class LetdbFile {

	private static final Log log = LogFactory.getLog(LetdbFile.class);
	
	public static String DbRoot = "letdb/";
	
	public static String DocIndexName = "docIndex";
	
	public static String StoreFileIndexName = "sotreIndex";
	
	public static File root;
	
	public static File docindexFile;
	
	public static File storeIndexFile;
	
	public boolean checkFileisExist(){
		root = new File(DbRoot);
		if (!root.exists()){
			log.info("checked /letdb not exist,will mkdir it..");
			root.mkdir();
		}else if (root.isFile()){
			log.info("checked /letdb is file,will delete it and mkdir /letdb..");
			root.delete();
			root.mkdir();
		}
		try{
			checkIndex();
		}catch(IOException e){
			e.printStackTrace();
			log.error("init db error: "+e.getMessage());
			return false;
		}
		return true;
	}
	
	private void checkIndex() throws IOException{
		docindexFile = new File(DbRoot+DocIndexName);
		if (!docindexFile.exists() || docindexFile.isDirectory()){
			log.info("checked index not exist or is directory,\n" +
					"will clear current directory and create new index file");
			clearAllChildren(root);
			docindexFile.createNewFile();
		}
	}
	
	private void clearAllChildren(File folder){
		File[] files = folder.listFiles();
		for (File file:files){
			if (file.isFile()){
				file.delete();
			}else{
				clearAllChildren(file);
				file.delete();
			}
		}
	}
	
	//load all index form disk in to hashtab
	public int loadDocIndex() throws IOException{
		int count = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(docindexFile));
		byte[] indexBytes = new byte[DocIndex.LENGTH_INDEX];
		while (in.read(indexBytes) != -1){
			byte[] md5keybytes = Arrays.copyOfRange(indexBytes, 0, DocIndex.LENGTH_INDEX);
			DocIndex.indexTable.put(new ByteArray(md5keybytes),
					new DocIndex(Arrays.copyOfRange(indexBytes,16,DocIndex.LENGTH_INDEX)));
			count++;
		}
		return count;
	}
	
	
	public int loadStoreFileIndex() throws IOException{
		int count = StorageFileIndex.loadIndex();
		return count;
	}
	
	public int initStoreFileIndex() throws IOException{
		int count = 0;
		storeIndexFile = new File(DbRoot+StoreFileIndexName);
		preCheckFileExist(storeIndexFile);
		return count;
	}
	
	
	private void preCheckFileExist(File file) throws IOException{
		if (!file.exists())
			file.createNewFile();
		
	}
	
	
}
