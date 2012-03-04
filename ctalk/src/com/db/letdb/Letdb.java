package com.db.letdb;

import java.io.File;
import java.io.IOException;

import com.helper.ByteHelper;

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
	
	public Document get(Long id) throws IOException{
		Document doc = null;
		DocIndex index = DocIndex.getDocIndex(new ByteArray(ByteHelper.getBytes(id)));
		if (index != null){
			File fd = StorageFileIndex.getFD(index.getFileName());
			if (fd != null){
				byte[] b = InWrite.read(fd, index.getOffset(), index.getLength());
				doc = Document.getInstance(new String(b));
			}
		}
		return doc;
	}
}
