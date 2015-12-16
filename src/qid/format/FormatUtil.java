package qid.format;

/**
 * 格式化工具类型
 * 1.subZeroAndDot   去掉小数点后面多余的0
 * 
 * 
 * @author djun
 *
 */
public class FormatUtil {
	/** 
	 * 格式化小数
     * 使用java正则表达式去掉多余的.与0 
     * @param s 数字字符串     3.140
     * @return  3.14
     */  
    public static String subZeroAndDot(String s){  
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
