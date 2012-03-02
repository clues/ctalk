package com.db.letdb;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * @created: Mar 2, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class StorageFileIndexTest {

	@Test
	public void testSync() throws IOException {
		
		LetdbFile.storeIndexFile = new File("letdb/"+LetdbFile.StoreFileIndexName);
		StorageFileIndex.indexMap.clear();
		
		StorageFileIndex index = new StorageFileIndex();
		index.setId(1);
		index.setSize(123456);
		index.setStatus(StorageFileIndex.STATUS_NEW);
		index.setFileName("abcdef");
		index.setLastModifyTime(9854685);
		
		StorageFileIndex.indexMap.put(index.getId(), index);
		int count = StorageFileIndex.sync();
		
		assertEquals(StorageFileIndex.LENGTH_INDEX,count);
	}

}
