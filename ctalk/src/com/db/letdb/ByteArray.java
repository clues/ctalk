package com.db.letdb;

import java.util.Arrays;

/**
 * @created: Mar 1, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class ByteArray {

	private byte[] array;
		
	private int len;
	
	public ByteArray(byte[] array){
		this(array,array.length);
	}
	
	public ByteArray(byte[] array,int len){
		this.array = array;
		this.len = len;
	}
	
	public byte[] getArray(){
		return array;
	}
	
	@Override
	public boolean equals(Object other){
		if (other instanceof ByteArray){
			ByteArray oa = (ByteArray)other;
			if (oa.getLen() == this.len){
				for (int i=0;i<len;i++){
					if (oa.getArray()[i]  != array[i]){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		int hash = Arrays.hashCode(array);
		return hash;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}
}
