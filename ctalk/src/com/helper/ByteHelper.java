package com.helper;
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
	

}
