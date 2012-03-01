package com.db.letdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.db.letdb.entity.Json;
import com.db.letdb.file.InWrite;
import com.helper.ByteHelper;
import com.helper.ReflexHelper;


/**
 * 
 * @author chao
 *
 */
public class LetFile {
	public static String filePath = "data";
	private List<String> tables = new ArrayList<String>() ;
	private Map<String,Map> map = new HashMap<String,Map>();
	//find all collection
	public void loadAllDocument(){
		List<String> names = new ArrayList<String>() ;
		File file =new File(filePath);
		if (file.isDirectory()){
			String[] fileNames = file.list();
			for (String name : fileNames) {
				names.add(name);
			} 
		}
		if (names.size() > 0){
			this.setTables(names);
		}
	}
	
	public void loadAllEntity(){
		if (this.tables.size() > 0){
			try{
				for (String name : this.tables) {
					InWrite iw = new InWrite(name);
					String line = null;
					Map docs = new HashMap();
					while ((line = iw.readNextLine()) != null){
						Json json = Json.instance(line);
						Object o = ReflexHelper.instance(json);
						docs.put(json.getKeyValue(), o);
					}
					map.put(name, docs);
				}
			}catch(IOException e){
				e.printStackTrace();
			}

		}
	}
	
	
	public void syncToDisk(Map docs){
		
	}
	
	public void syncToDisk(String context){
		
	}

	public List<String> getTables() {
		return tables;
	}

	public void setTables(List<String> tables) {
		this.tables = tables;
	}
}
