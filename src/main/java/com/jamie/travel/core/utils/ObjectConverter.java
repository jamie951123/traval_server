package com.jamie.travel.core.utils;

import org.apache.commons.codec.binary.Base64;

public class ObjectConverter {

	public static String byteArrayToBase64(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}

	/**
	 * Decodes the base64 string into byte array
	 *
	 * @param imageDataString - a {@link java.lang.String}
	 * @return byte array
	 */
	public static byte[] base64ToByteArray(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}
	

}