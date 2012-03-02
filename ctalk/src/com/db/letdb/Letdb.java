package com.db.letdb;
/**
 * @created: Mar 2, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class Letdb {

	public void saveOrUpdate(Document doc){
		
		if (doc.getId() != null){
			doc.setId(System.currentTimeMillis());
		}
		byte[] bin = doc.getBytes();
		
		;
	}
}
