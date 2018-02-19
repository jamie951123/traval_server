package com.jamie.travel.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DirectoryUtils {

	public static boolean createFolder(String path) {
		File theDir = new File(path);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + path);
			boolean result = false;

			try {
				if(!theDir.getParentFile().exists()) {
					theDir.getParentFile().mkdirs();
				}
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
				System.out.println("SecurityException");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			if (result) {
				System.out.println("DIR created");
				return true;
			}
		} else {
			System.out.println("DIR is Existed");
		}
		return false;

	}
	
	public static byte[] pathToByte(String pathe) throws IOException {
		File file = new File(pathe);
		//init array with file length
		byte[] bytesArray = new byte[(int) file.length()];

		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray); //read file into bytes[]
		fis.close();

		return bytesArray;
	}
}
