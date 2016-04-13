package com.web.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Tom
 */
public class MyFileUtils {
	public static String WORKDIR ="D:\\images";
	
	/**
	 * 图片存放路径
	 * @return
	 */
	public static File ImageFilePath() {
		String date = getTime();
		String filePath = String.format("data/%s", date);
		
		File file = new File(WORKDIR + "/" + filePath);
		if(!file.exists()) file.mkdirs();
		return file;
	}
	
	public static String getTime() {
    	long mts = System.currentTimeMillis();
    	
    	//long time = mts/1000;
    	
    	Calendar cal = new GregorianCalendar();
    	cal.setTimeInMillis(mts);
    	
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH)+1;
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	int hour = cal.get(Calendar.HOUR_OF_DAY);
    	int date = year*10000 + month*100 + day;
    	return date+""+hour;
    }

	public static List<File> getFiles(File folder) {
		List<File> files = new ArrayList<File>();
		iterateFolder(folder, files);
		return files;
	}

	private static void iterateFolder(File folder, List<File> files) {
		File flist[] = folder.listFiles();
		files.add(folder);
		if (flist == null || flist.length == 0) {
			files.add(folder);
		} else {
			for (File f : flist) {
				if (f.isDirectory()) {
					iterateFolder(f, files);
				} else {
					files.add(f);
				}
			}
		}
	}
}
