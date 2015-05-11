package com.zero.test.base.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class FileUtil {

	public static final String ROOT_EXTERNAL = "mbox";
	public static final String ROOT_EXTERNAL_CFG = "cfg";
	public static final String ROOT_EXTERNAL_ERR = "log";

	public static File createLogFile() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			String name = TimeUtil.currentLocalTimeString() + ".txt";
			File parent = new File(Environment.getExternalStorageDirectory() + File.separator + ROOT_EXTERNAL + File.separator + ROOT_EXTERNAL_ERR);
			if (!parent.exists()) {
				parent.mkdirs();
			}
			File log = new File(parent.getAbsolutePath(), name);
			try {
				log.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return log;
		} else {
			return null;
		}

	}

	public static void createAppCacheDir() {
		File parent = new File(getRootPath());
		if (!parent.exists()) {
			parent.mkdirs();
		}
	}

	public static String getRootPath() {
		return Environment.getExternalStorageDirectory() + File.separator + ROOT_EXTERNAL + File.separator + ROOT_EXTERNAL_CFG;
	}
}
