/**
 * 
 * 【剑指Offer】面试题20 ：表示数值的字符串
 * 【  题目描述 】请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E20_NumericStrings {

	int index = 0;

	// 数字的格式可以用A[.[B]][e|EC]或者.B[e|EC]表示，其中A和C都是
	// 整数（可以有正负号，也可以没有），而B是一个无符号整数
	public boolean isNumeric(char[] str) {
		if (str == null || str.length < 1) {
			return false;
		}
		index = 0;

		boolean numeric = scanInteger(str);

		if (index < str.length && str[index] == '.') {
			index++;

			// 下面一行代码用||的原因：
			// 1. 小数可以没有整数部分，例如.123等于0.123；
			// 2. 小数点后面可以没有数字，例如233.等于233.0；
			// 3. 当然小数点前面和后面可以有数字，例如233.666
			numeric = scanUnsignedInteger(str) || numeric;
		}

		// 如果出现'e'或者'E'，接下来跟着的是数字的指数部分
		if (index < str.length && (str[index] == 'e' || str[index] == 'E')) {
			index++;

			// 下面一行代码用&&的原因：
			// 1. 当e或E前面没有数字时，整个字符串不能表示数字，例如.e1、e1；
			// 2. 当e或E后面没有整数时，整个字符串不能表示数字，例如12e、12e+5.4
			numeric = scanInteger(str) && numeric;
		}

		return numeric && index == str.length;
	}

	// 扫描字符串部分的数字部分
	private boolean scanInteger(char[] str) {
		if (index >= str.length)
			return false;

		if ((str[index] == '+' || str[index] == '-'))
			index++;

		return scanUnsignedInteger(str);
	}

	// 扫描字符串部分的无符号数字
	private boolean scanUnsignedInteger(char[] str) {
		int before = index;
		while (index < str.length && str[index] >= '0' && str[index] <= '9') {
			index++;
		}
		return index > before;
	}

	private void test(String str, boolean expect) {
		System.out.printf("验证结果：%b [%b]\n", isNumeric(str == null ? null : str.toCharArray()), expect);
	}

	public static void main(String[] args) {

		E20_NumericStrings exam = new E20_NumericStrings();

		exam.test("100", true);
		exam.test("123.45e+6", true);
		exam.test("+500", true);
		exam.test("5e2", true);
		exam.test("3.1416", true);
		exam.test("600.", true);
		exam.test("-.123", true);
		exam.test("-1E-16", true);
		exam.test("1.79769313486232E+308", true);

		exam.test("12e", false);
		exam.test("1a3.14", false);
		exam.test("1+23", false);
		exam.test("1.2.3", false);
		exam.test("+-5", false);
		exam.test("12e+5.4", false);

		exam.test(".", false);
		exam.test(".e1", false);
		exam.test("e1", false);
		exam.test("+.", false);

		exam.test("", false);
		exam.test(null, false);
	}
}
