package com.tlscloud.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Transform {

	public static String getJSON(String fileName,String separator){
		
		byte[] bytes=null;
		try {
			bytes = org.apache.commons.io.FileUtils
					.readFileToByteArray(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String csv = new String(bytes);
		String csvValues[] = csv.split("\n");
		//ArrayList<String> list=new ArrayList<String>(csvValues.length);
		String header[]=csvValues[0].split(separator);
		
		StringBuilder sb=new StringBuilder("[\n");
		
		for(int i=1;i<csvValues.length;i++){
			sb.append("\t").append("{").append("\n");
			String tmp[]=csvValues[i].split(separator);
			for(int j=0;j<tmp.length;j++){
				sb.append("\t").append("\t\"").append(header[j]).append("\":\"").append(tmp[j]).append("\"");
				if(j<tmp.length-1){
					sb.append(",\n");
				}else{
					sb.append("\n");
				}
			}
			if(i<csvValues.length-1){
				sb.append("\t},\n");
			}
			else{
				sb.append("\t}\n");
			}
		}
		
		sb.append("]");
		return sb.toString();
	}
	public static void main(String[] args) throws Exception{
		System.out.println(getJSON("test/students.csv", "\\,"));
	}
}
