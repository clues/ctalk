package com.db.letdb;

import java.util.Hashtable;

/**
 * @created: Mar 1, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class Index {

	public static int LENGTH_INDEX = 40;
	
	public static Hashtable<ByteArray,DocIndex> indexTable = new Hashtable<ByteArray,DocIndex>();
	
	
	private ByteArray md5key;
	
	private DocIndex docIndex;
	
	public Index(){
		
	}
	
	public Index(ByteArray md5key,DocIndex docIndex){
		this.md5key = md5key;
		//this.docIndex = docIndex;
	}
	
	public static DocIndex getDocIndex(ByteArray md5key){
		return (DocIndex)indexTable.get(md5key);
	}
	
}
