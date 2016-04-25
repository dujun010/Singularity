package qid.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 根据模板创建XML文件
 * @author djun
 *
 */
public class FileCreatXMLForTemplate {
	public final static String FILE_PATH = System.getProperty("user.dir")+"\\template\\";
	public final static String FILE_NAME = "WaterTemplate.xml";
	
	
	
	public static void creatXMLForTemplate(){
		StringBuffer sbf = readFileByLines(FILE_PATH+FILE_NAME);//一行一行的读取文件
	}
	
	/**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static StringBuffer readFileByLines(String fileName) {
    	StringBuffer sbf = new StringBuffer("");
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if(tempString.indexOf("${name}") > 0){
                	sbf.append(tempString.replace("${name}", "Login")+"\n");
                }else if(tempString.indexOf("${status}") > 0){
                	sbf.append(tempString.replace("${status}", "0")+"\n");
                }else if(tempString.indexOf("${msg}") > 0){
                	sbf.append(tempString.replace("${msg}", "登陆成功")+"\n");
                }else if(tempString.indexOf("${itemName}") > 0 && tempString.indexOf("${itmeValue}") > 0){
                	String item1 = tempString.replace("${itemName}", "");
                	String item2 = item1.replace("${itmeValue}", "")+"\n";
                	sbf.append(item2);
                }else{
                	sbf.append(tempString+"\n");
                }
            }
            reader.close();
            return sbf;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return sbf;
    }
    /**
	 * 写入文件
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
	private void isThereFile(String path){
		File file = new File(path);
		if(!file.exists()) file.mkdir();
	}
	
	
	
	
	public static void main(String[] args) {
		creatXMLForTemplate();
//		String a = "<name>${name}</name>";
//		System.out.println(a.replace("${name}", "sss"));
	}
}
