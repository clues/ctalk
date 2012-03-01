package com.helper;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @created: 2011-11-7
 * @author : jias chao<lino.chao@gmail.com>
 */
public class ByteHelper {

	public static long getLong(byte[] bytes, int offset) {
		long result = 0;
		for (int i = offset; i < offset + 8; i++) {
			result = (result << 8) | (0x00ff & bytes[i]);
		}
		return result;
	}
	
	public static int getInt(byte[] bytes, int begin) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = result | (0x00ff & bytes[i + begin]) << ((3 - i) * 8);
		}
		return result;
	}
	
	public static int getShortInt(byte[] bytes, int begin) {
		int result = 0;
		result = ((0x00ff & bytes[begin]) << 8) | (0x00ff & bytes[begin + 1]);
		return result;
	}

	public static String getString(byte[] bytes,int begin){
		return getString(bytes,begin,bytes.length,null);
	}
	
	public static String getString(byte[] bytes,int begin,int len){
		return getString(bytes,begin,len,null);
	}
	
	public static String getString(byte[] bytes,int begin,int len,String charset){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = begin;i <len;i++){
			if (bytes[i] != 0){
				out.write(bytes[i]);
			}else{
				break;
			}
		}
		try {
			if (charset == null)
				charset = "UTF-8";
			return new String(out.toByteArray(),charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static byte[] getBytes(short value) {
		int i = value;
		byte[] r = new byte[2];
		r[0] = ByteHelper.getByte(i, 3);
		r[1] = ByteHelper.getByte(i, 4);
		return r;
	}

	public static byte[] getBytes(int value, int len) {
		if (len > 4)
			throw new IllegalArgumentException(
					"the argument 'len' can not larger than 4");
		byte[] r = new byte[len];
		for (int i = 0; i < len; i++) {
			r[i] = ByteHelper.getByte(value, i + 1);
		}
		return r;
	}

	public static byte[] getBytes(int value) {
		return ByteHelper.getBytes(value, 4);
	}
	
	public static byte[] getBytes(long value, int len) {
		if (len > 8)
			throw new IllegalArgumentException(
					"the argument 'len' can not larger than 8");
		byte[] r = new byte[len];
		for (int i = 0; i < len; i++) {
			r[i] = ByteHelper.getByte(value, i + 1);
		}
		return r;
	}
	
	public static byte getByte(int value, int index) {
		return (byte) ((value >> ((4 - index) * 8)) & 0x00ff);
	}
	
	public static byte getByte(long value, int index) {
		return (byte) ((value >> ((8 - index) * 8)) & 0x00ff);
	}
	
	public static byte[] getBytes(String str){
		return ByteHelper.getBytes(str,"UTF-8");
	}
	
	
	public static byte[] getBytes(String str,String charset){
		
		try {
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
