package qid.file.shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

/**
 * 执行指定shell脚本
 * 
 * @author djun
 * 2017年4月16日
 */
public class ReadShell {
	
	/**
	 * 读取并执行shell脚本
	 *
	 * @author 杜小军
	 * @param shellPath		.sh文件路径
	 * @param shellName		.sh文件名称
	 * @param parameter		参数   多个参数     如传递三个参数  123 abc 1a2b3c(三个参数值以空格分开)
	 * <p>linux 运行命令   ./djun.sh 123 abc 1a2b3c
	 * @return
	 */
	public static String runSell(String shellPath , String shellName, String parameter){  
	    JSONObject result = new JSONObject();  
	    String osname = System.getProperty("os.name");  
	    if ((osname != null) && (osname.toLowerCase().startsWith("win"))){  
	        System.out.println("当前操作系统是:"+osname);  
	        result.put("code", "0");  
	        result.put("msg", "当前服务器操作系统不是linux");  
	        return result.toString();  
	    }  
	    System.out.println("接收到参数:targetPath=" + parameter);  
	    if(parameter == null || parameter.equals("")){  
	        result.put("code", "0");  
	        result.put("msg", "dbCode/targetPath不能为空");  
	        return result.toString();  
	    }  
	      
	    //脚本路径  
//	    String shellPath = request.getServletContext().getRealPath("/")+"WEB-INF/classes";  
//	    String shellPath = "/usr/project/shell";
//	    String cmd = shellPath + "/djun.sh " + parameter;  
	    String cmd = shellPath.trim() +"/"+ shellName.trim()+ " " + parameter.trim();  
	    ProcessBuilder builder = new ProcessBuilder("/bin/sh","-c",cmd);  
	    builder.directory(new File(shellPath));  
	      
	    int runningStatus = 0;  
	    String s = null;  
	    StringBuffer sb = new StringBuffer();  
	    try {  
	        Process p = builder.start();  
	          
	        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));  
	           BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));  
	           while ((s = stdInput.readLine()) != null) {  
	               System.out.println("shell log info ...." + s);  
	               sb.append(s);  
	           }  
	           while ((s = stdError.readLine()) != null) {  
	               System.out.println("shell log error...." + s);  
	               sb.append(s);  
	           }  
	           try {  
	               runningStatus = p.waitFor();  
	           } catch (InterruptedException e) {  
	            runningStatus = 1;  
	            System.out.println("等待shell脚本执行状态时，报错...");  
	            sb.append(e.getMessage());  
	           }  
	             
	           closeStream(stdInput);  
	           closeStream(stdError);  
	             
	    } catch (Exception e) {  
	        System.out.println("执行shell脚本出错...");  
	        sb.append(e.getMessage());  
	        runningStatus =1;  
	    }  
	    System.out.println("runningStatus = " + runningStatus);  
	    if(runningStatus == 0){  
	        //成功  
	        result.put("code", "1");  
	        result.put("msg", "成功");  
	        return result.toString();  
	    }else{  
	        result.put("code", "0");  
	        result.put("msg", "调用shell脚本复制数据库时失败..." + sb.toString());  
	        return result.toString();  
	    }  
	}  
	  
	private static void closeStream(BufferedReader reader){  
	    try {  
	        if(reader != null){  
	            reader.close();  
	        }  
	    } catch (Exception e) {  
	        reader = null;  
	    }  
	} 
	
	
	
	
	public static void main( String[] args )
    {
        ReadShell.runSell("/usr/project/shell","djun.sh","oneP tow123");
    }
}

