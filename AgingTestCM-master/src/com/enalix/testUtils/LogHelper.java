package com.enalix.testUtils;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

public class LogHelper {
	
	static FileWriter fw;

	public static void Init(){
		try {
			fw = new FileWriter("/sdcard/uitest/LogInformation.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void LogHelp(String tag, String Content){

		System.out.println(tag + ":\t "+Content);
		
		try {
			fw = new FileWriter("/sdcard/uitest/LogInformation.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(tag + ":\t "+Content+"\n");
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void LogHelp(String Content){
		
	System.out.println(Content);
		
		try {
			fw = new FileWriter("/sdcard/uitest/LogInformation.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Content+"\n");
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void commit(){
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
