package com.timebank.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
	String path = getPath();

	public String readApplicationFile(String key) {
		String value = "";
		try {
			Properties prop = new Properties();
			String dirPath = System.getProperty("user.dir");
			System.out.println(("thet get " + dirPath));
			File f = new File(dirPath
					+ "//src//main//resources//application.properties");
			System.out.println(("thet get " + f));
			if (f.exists()) {
				prop.load(new FileInputStream(f));
				value = prop.getProperty(key);
			}
		} catch (Exception e) {
			System.out
					.println("Failed to read from application.properties file.");
		}
		return value;
	}

	public String getPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return path;
	}

}