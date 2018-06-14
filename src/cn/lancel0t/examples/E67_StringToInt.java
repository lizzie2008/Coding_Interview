/**
 * 
 * 【剑指Offer】	面试题67 ：把字符串转换成整数
 * 【题目描述】	将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 
 *  数值为0或者字符串不是一个合法的数值则返回0
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E67_StringToInt {

	/*
	 * 把字符串转换成整数
	 * 思路：注意事项
	 * （1）字符串中包含有非数字字符；
	 * （2）字符串中包含正负符号；
	 * （3）考虑最大的正整数；
	 * （4）考虑最小的负整数；
	 * （4）溢出。
	 */
	public int StrToInt(String str) {

		if (str == null || str.length() == 0)
			return 0;

		int len = str.length();
		int index = 0;
		boolean minus = false;
		if (str.charAt(0) == '+') {
			index++;
		} else if (str.charAt(0) == '-') {
			minus = true;
			index++;
		}
		// 只有一个正负号符号，而没有数字的情况
		if (index != 0 && len == 1) {
			return 0;
		}
		int num = StrToInt(str, index, minus);
		if (num != 0)
			num = minus ? num * (-1) : num;
		return num;
	}

	/*
	 * 计算并返回数值，如果无效返回0
	 */
	private int StrToInt(String str, int index, boolean minus) {
		int result = 0;
		int len = str.length();
		for (int i = index; i < len; i++) {
			// 各个位置上的字符是否有效
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				// 计算数值
				result = result * 10;
				result += str.charAt(i) - '0';
				// 判断是否溢出
				if ((minus && (result * -1) > 0) || (!minus && (result < 0))) {
					result = 0;
					break;
				}
			} else {
				result = 0;
				break;
			}
		}
		return result;
	}

	// ====================测试代码====================
	private void test(String testName, String str) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("把字符串转换成整数:\n\"%s\" => %d\n", str, StrToInt(str));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		E67_StringToInt exam = new E67_StringToInt();

		exam.test("test1", null);
		exam.test("test2", "");
		exam.test("test3", "123");
		exam.test("test4", "+123");
		exam.test("test5", "-123");
		exam.test("test6", "1a33");
		exam.test("test7", "+0");
		exam.test("test8", "-0");
		
		// 有效的最大正整数, 0x7FFFFFFF
		exam.test("test9", "+2147483647");
		exam.test("test10", "-2147483647");
		exam.test("test11", "+2147483648");
		//有效的最小负整数, 0x80000000
		exam.test("test12", "-2147483648");
		exam.test("test13", "+2147483649");
		exam.test("test14", "-2147483649");
		
		exam.test("test15", "+");
		exam.test("test16", "-");
	}
}
