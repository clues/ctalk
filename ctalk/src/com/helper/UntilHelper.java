package com.helper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UntilHelper {

	
	/**
	 * 移除byte数组中多余空格 连续空格只保留一个  如果是一个连续值为32的数组将返回空长度
	 * et: "   aa   b  c   " -> "aa b c"
	 * @param 
	 * @return
	 */
	public static byte[] removeBy(int value,byte[] content){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		if (content.length > 0){
			boolean preisSame = true; //
			int tailIndex = content.length -1;
			for (int i=0 ;i < content.length; i++){
				if (content[i] != value){
					preisSame = false;
					out.write(content[i]);
				}else if(content[i] == value && preisSame){
					//ignore
				}else if(i == tailIndex){

				}else{
					out.write(value);
					preisSame = true;
				}
			}
			
		}
		return out.toByteArray();
	}
	
	/**
	 * 
	 * @param first
	 * @param second
	 * @return first full equal second return true
	 */
	public static boolean isSame(byte[] first,byte[] second){
		boolean same = true;
		if (first.length != second.length){
			return false;
		}else{
			for (int i=0 ;i < first.length; i++){
				if (first[i] != second[i]){
					same = false;
					break;
				}
			}
			return same;
		}
	}
	
	public static List<ByteArrayOutputStream> cutby(int value,byte[] content){
		List<ByteArrayOutputStream> bytes = new ArrayList<ByteArrayOutputStream>();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		boolean preisSame = false; //
		if(content.length > 0){
			for (int i=0; i< content.length; i++){
				if (content[i] != value){
					out.write(content[i]);
					preisSame = false;
				}else if(!preisSame){
					if (out.size() > 0){
						bytes.add(out);
						out = new ByteArrayOutputStream();
					}
					preisSame = true;
				}
			}
		}
		if (out.size() > 0){
			bytes.add(out);
		}
		return bytes;
	}
	
//	public static boolean isLike(byte[] first,byte[] second){
//		
//	}
	
}
