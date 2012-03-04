package com.db.letdb;

import java.io.File;
import java.io.IOException;

/**
 * @created: Mar 2, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class Letdb {

	public void saveOrUpdate(Document doc) throws IOException{
		
		if (doc.getId() == null){
			doc.setId(System.currentTimeMillis());
		}
		byte[] bin = doc.getBytes();
		DocIndex index = DocIndex.getDocIndex(doc.getId());
		StorageFileIndex sfi = null;
		if (index == null){//add
			sfi = StorageFileIndex.getUseableStorageFileIndex();
		}else{//update
			sfi = StorageFileIndex.getStorageFileIndex(index.getFileName());
		}
		
		DocIndex tmp = new DocIndex();
		tmp.setId(doc.getId());
		tmp.setFileName(sfi.getFileName());
		tmp.setClazz(doc.getClass().getName());
		tmp.setLength(bin.length);
		tmp.setOffset(sfi.getSize());
		
		long size = InWrite.write(sfi.getFd(), bin, true);
		
		StorageFileIndex.updateIndex(tmp.getFileName(), size);
		DocIndex.updateIndex(tmp);
	}
	
	
	public Document get(Long id) throws IOException, ClassNotFoundException{
		Document doc = null;
		DocIndex index = DocIndex.getDocIndex(id);
		if (index != null){
			File fd = StorageFileIndex.getFD(index.getFileName());
			if (fd != null){
				byte[] b = InWrite.read(fd, index.getOffset(), index.getLength());
				doc = Document.getInstance(new String(b),index.getClazz());
				return doc;
			}
		}
		return null;
		
	}
	
	public void delete(DocIndex index){
		if (index != null){
			delete(index.getId());
		}
	}
	
	public void delete(Long id){
		DocIndex index = DocIndex.getDocIndex(id);
		if (index != null){
			DocIndex.deleteIndex(index.getId());
		}
	}
}
