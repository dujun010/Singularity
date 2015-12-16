package qid.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 写文件
 * 1.FileWriterFW	写入文件	通过FileWriter写入文件
 * 2.FileWriterBOS	写人文件	通过BufferedOutputStream写入文件
 * 3.FileWriterFOS	写入文件	通过FileOutputStream写入文件
 * 备注:	操作时间-->FileWriterFW 最短	FileWriterBOS其次		FileWriterFOS最长
 * @author djun
 *
 */
public class FileWrite {
	
	/**
	 * 1.写入文件
	 * 通过FileWriter写入文件
	 * @param Content	文件内容
	 * @param filePath	文件路径
	 * @param fileName	文件名称
	 * @return	写入成功:文件路径+文件名称	写入失败:""(空字符串)
	 */
	public String FileWriterFW(String Content,String filePath,String fileName){
		isThereFile(filePath);//判断文件夹是否存在,不存在怎创建
		FileWriter fw = null;
		try {
			fw = new FileWriter(filePath+fileName);
			fw.write(Content); 
			fw.close();
			return filePath+fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			try {   
                fw.close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }  
		}
		
	}
	
	/**
	 * 2.写入文件
	 * 通过BufferedOutputStream写入文件
	 * @param Content	文件内容
	 * @param filePath	文件路径
	 * @param fileName	文件名称
	 * @return	写入成功:文件路径+文件名称	写入失败:""(空字符串)
	 */
	public String FileWriterBOS(String Content,String filePath,String fileName){
		isThereFile(filePath);//判断文件夹是否存在,不存在怎创建
		FileOutputStream outSTr = null; 
		BufferedOutputStream Buff=null;
		try {
			outSTr = new FileOutputStream(new File(filePath+fileName));
			Buff=new BufferedOutputStream(outSTr);
			Buff.write(Content.getBytes()); 
			Buff.flush();   
            Buff.close();
            return filePath+fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			 try {
				Buff.close();   
				outSTr.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * 3.写入文件
	 * 通过FileOutputStream写入文件
	 * @param Content	文件内容
	 * @param filePath	文件路径
	 * @param fileName	文件名称
	 * @return	写入成功:文件路径+文件名称	写入失败:""(空字符串)
	 */
	public String FileWriterFOS(String Content,String filePath,String fileName){
		isThereFile(filePath);//判断文件夹是否存在,不存在怎创建
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(filePath+fileName));
			out.write(Content.getBytes());
			out.close();
			return filePath+fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void isThereFile(String path){
		File file = new File(path);
		if(!file.exists()) file.mkdir();
	}
	
	
	
	
	
	
}
