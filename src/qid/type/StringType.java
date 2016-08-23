package qid.type;

import java.math.BigDecimal;
/**
 * String类型操作
 * 
 * 1.StringTODouble			字符串转数字
 * 
 * @author djun
 *
 */
public class StringType {

	
	/**
	 * 字符串 转数字
	 * @author djun
	 * @param strNumber			需要转换的数字
	 * @param decimalDigit		保留小数位	若为null 则自动保留2位小数
	 * @return
	 */
	public static Double StringTODouble(String strNumber,Integer decimalDigit){
		decimalDigit = decimalDigit == null || decimalDigit.equals("") ? 4 : decimalDigit;
		double number = Double.parseDouble(strNumber);
		BigDecimal result = new BigDecimal(number).setScale(decimalDigit,BigDecimal.ROUND_DOWN);
		return result.doubleValue();
	}
	
	public static void main(String[] args) {
		System.err.println(StringTODouble("3.1415926",null));
	}
}
