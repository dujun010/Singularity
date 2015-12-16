package qid.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 操作文件
 * 1.moveFile	移动文件
 * 2.copyFile	复制文件
 * @author djun
 *
 */
public class OperationFile {
	
	/**
	 * 移动文件
	 * @param filePath1		需要移动的目标文件       D:\file\test.txt
	 * @param filePath2		移动的目的地文件	   D:\file_new\
	 */
	public static void moveFile(String filePath1,String filePath2){
		filePath1 = filePath1.replace("\\", "/");
		filePath2 = filePath2.replace("\\", "/");
		
		//判断文件夹是否存在
		File file = new File(filePath2.trim()); 
		if(!file.exists())  file.mkdirs(); 
		
		//移动文件
		File Operation = new File(filePath1.trim());//移动的目标文件       D:\file\test.txt
		File Targetfile = new File(filePath2.trim());//		 D:\file_new\
		Operation.renameTo(new File(Targetfile, Operation.getName())); 
	}
	
	/**
	 * 复制文件
	 * @param file1	        复制的文件路径	F:\\test\\new\\1525021005_20150715001052.MIS
	 * @param file2           目的地路径		F:\\test\\old\\
	 * @param isName   是否使用原始名称      true:复制不改变		false:不使用原名称
	 */
	@SuppressWarnings("resource")
	public static void copyFile(String file1,String file2,boolean isName){
		file1 = file1.replace("\\", "/");
		file2 = file2.replace("\\", "/");
		String name = "";
		if(isName){
			name = file1.substring(file1.lastIndexOf("/")+1, file1.length());
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			name = sdf.format(new Date())+file1.substring(file1.lastIndexOf("."), file1.length());
		}
		try {
			FileInputStream input = new FileInputStream(file1);// 可替换为任何路径何和文件名
			FileOutputStream output = new FileOutputStream(file2+"/"+name);// 可替换为任何路径何和文件名
			int in = input.read();
			while (in != -1) {
				output.write(in);
				in = input.read();
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		String file1 = "F:/test/new/1525021005_20150715001052.MIS";
		String name = file1.substring(file1.lastIndexOf("."), file1.length());
		System.out.println(name);
	}
}
