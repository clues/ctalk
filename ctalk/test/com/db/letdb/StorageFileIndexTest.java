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
	public void testLoadFileIndex() throws IOException {
		File f0 =  new File(LetdbFile.DbRoot);
		if (f0.exists())
			clearAllChildren(f0);
		f0.mkdir();
		File f1 = new File(LetdbFile.DbRoot+StorageFileIndex.FILENAME_DEFAULT+1);
		File f2 = new File(LetdbFile.DbRoot+StorageFileIndex.FILENAME_DEFAULT+2);
		File f3 = new File(LetdbFile.DbRoot+StorageFileIndex.FILENAME_DEFAULT+3);
		f1.createNewFile();
		f2.createNewFile();
		f3.createNewFile();
		
		assertEquals(3,StorageFileIndex.loadIndex());
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

}
