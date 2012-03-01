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

/**
 * @created: Feb 29, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class LetdbFile {

	private static final Log log = LogFactory.getLog(LetdbFile.class);
	
	public static String dbRoot = "letdb/";
	
	public static File root;
	
	public static File indexFile;
	
	public boolean checkFileisExist(){
		root = new File(dbRoot);
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
		indexFile = new File(dbRoot+"index");
		if (!indexFile.exists() || indexFile.isDirectory()){
			log.info("checked index not exist or is directory,\n" +
					"will clear current directory and create new index file");
			clearAllChildren(root);
			indexFile.createNewFile();
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
	public int loadIndex() throws IOException{
		int count = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(indexFile));
		byte[] indexBytes = new byte[Index.LENGTH_INDEX];
		while (in.read(indexBytes) != -1){
			byte[] md5keybytes = Arrays.copyOfRange(indexBytes, 0, Index.LENGTH_INDEX);
			Index.indexTable.put(new ByteArray(md5keybytes),
					new DocIndex(Arrays.copyOfRange(indexBytes,16,DocIndex.LENGTH_DOCINDEX)));
			count++;
		}
		return count;
	}
	
	//flush hashtable to disk
	public int sync() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (Iterator<ByteArray> it = Index.indexTable.keySet().iterator();it.hasNext();){
			ByteArray md5key = it.next();
			out.write(md5key.getArray());
			out.write(((DocIndex)Index.indexTable.get(md5key)).getBytes());
		}
		
		indexFile.delete();
		indexFile.createNewFile();
		OutputStream os = new FileOutputStream(indexFile);
		os.write(out.toByteArray());
		os.flush();
		if (os != null)
			os.close();
		return out.toByteArray().length;
	}
	
	
	
}
