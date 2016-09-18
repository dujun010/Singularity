package qid.type;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Double类型操作
 * 1.FormatDouble 		四舍五入
 * 2.subZeroAndDot		格式化最后一位的零
 * 3.add				两个Double数相加
 * 4.subtract			两个Double数相减
 * 5.multiply			两个Double数相乘
 * 6.divide				两个Double数相除/两个Double数相除，并保留scale位小数
 * @author djun
 *
 */
public class DoubleType {
	
	/**
	 * 四舍五入
	 * @param number double类型数字
	 * @param format  为null时，默认保留两位小数	格式 ： 例如保留两位小数    #.00
	 * @return
	 */
	public static String FormatDouble(double number, String format){
		format = format==null ? "#.00" : format;
		DecimalFormat df=new DecimalFormat(format); 
		return df.format(number);
	}
	
	/** 
	 * 取消小数点后面的零
	 * <p>
	 * 1.010 ---> 1.01
	 * 1.011 ---> 1.011
     * 使用java正则表达式去掉多余的.与0 
     * @param s 
     * @return  
     */  
    public static String subZeroAndDot(String s){  
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    }
    
    /**
     * 两个Double数相加
     * @author djun
     * @param v1
     * @param v2
     * @return
     */
    public static Double add(Double v1, Double v2) {  
    	BigDecimal b1 = new BigDecimal(v1.toString());  
    	BigDecimal b2 = new BigDecimal(v2.toString());  
    	return new Double(b1.add(b2).doubleValue());  
    } 

    /**
     * 两个Double数相减
     * @author djun
     * @param v1
     * @param v2
     * @return
     */
    public static Double subtract(Double v1, Double v2) {  
    	BigDecimal b1 = new BigDecimal(v1.toString());
    	BigDecimal b2 = new BigDecimal(v2.toString()); 
    	return new Double(b1.subtract(b2).doubleValue());  
    }
    
    /**
     *  两个Double数相乘
     * @author djun
     * @param v1
     * @param v2
     * @return
     */
    public static Double multiply(Double v1, Double v2) { 
    	BigDecimal b1 = new BigDecimal(v1.toString());
    	BigDecimal b2 = new BigDecimal(v2.toString()); 
    	return new Double(b1.multiply(b2).doubleValue()); 
    }
    
    public static Double divide(Double v1, Double v2) { 
    	BigDecimal b1 = new BigDecimal(v1.toString());
    	BigDecimal b2 = new BigDecimal(v2.toString()); 
    	return new Double(b1.divide(b2, BigDecimal.ROUND_HALF_UP).doubleValue()); 
    }
    
    /**
     * 两个Double数相除，并保留scale位小数
     * @author djun
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static Double divide(Double v1, Double v2, int scale) {  
    	if (scale < 0) throw new IllegalArgumentException("The scale must be a positive integer or zero");  
    	BigDecimal b1 = new BigDecimal(v1.toString());
    	BigDecimal b2 = new BigDecimal(v2.toString()); 
    	return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }
    
    public static void main(String[] args) {  
        Float f = 1f;  
        System.out.println(f.toString());//1.0  
        System.out.println(subZeroAndDot("1"));;  // 转换后为1  
        System.out.println(subZeroAndDot("10"));;  // 转换后为10  
        System.out.println(subZeroAndDot("1.0"));;  // 转换后为1  
        System.out.println(subZeroAndDot("1.010"));;  // 转换后为1.01   
        System.out.println(subZeroAndDot("1.01"));;  // 转换后为1.01  
    }  
}
