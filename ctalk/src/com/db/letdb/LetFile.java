package com.db.letdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.helper.ByteHelper;


/**
 * 
 * @author chao
 *
 */
public class LetFile {
	private static final Log log = LogFactory.getLog(LetFile.class);
	private static final String DB_DIR = "letdb";
	private static final String LOCK_FILE = "letdb/.lock";
	private static final String INDEX_FILE = "letdb/.index";
	private static Map<Long,Index> indexMap = new Hashtable<Long,Index>();
	
	public void init() throws IOException{
		File file = new File(DB_DIR);
		if (!file.exists() || !file.isDirectory()){
			log.info("crate dir letdb/ ");
			file.mkdir();
		}else{
			File indexFile = new File(INDEX_FILE);
			if (!indexFile.exists()){
				log.info("crate file .index ");
				indexFile.createNewFile();
			}
			loadIndex();
		}
	}
	
	/**
	 * load index from .index file to indexMap in memory
	 * @throws IOException
	 */
	private void loadIndex() throws IOException{
		InputStream in = new FileInputStream(new File(INDEX_FILE));
		log.debug(".index found");
		int off = 0;
		byte[] b = new byte[24];
		while (in.read(b, off, 24) != -1){
			Index index = Index.getInstance(b);
			if (index != null){
				indexMap.put(index.getId(),index);
				off += 24;
			}
		}
		log.debug("load index in map account: "+indexMap.size());
	}
	
	
}

/**
 * doc map index
 * @author chao
 * 
 */
class Index{
	private long id = -1;       //doc id
	private int state = 0;      //-1:this doc has been delete; 0:normal
	private long pos = 0;       //doc start postion
	private int len = 0;        //doc take size  
	public static int INDEX_LEN = 24;
	
	public static Index getInstance(byte[] content){
		if (content.length == INDEX_LEN){
			Index index = new Index();
			
			index.setId(ByteHelper.getLong(content, 0));
			index.setState(ByteHelper.getInt(content, 4));
			index.setPos(ByteHelper.getLong(content, 12));
			index.setLen(ByteHelper.getInt(content, 20));
			
			return index;
		}else{
			return null;
		}
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getPos() {
		return pos;
	}
	public void setPos(long pos) {
		this.pos = pos;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
