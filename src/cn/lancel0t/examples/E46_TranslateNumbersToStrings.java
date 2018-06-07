/**
 * 
 * 【剑指Offer】	面试题46 ：把数字翻译成字符串
 * 【题目描述】	给定一个数字，我们按照如下规则把它翻译为字符串：0翻译成"a"，1翻译成"b"，……，11翻译成"l"，……，25翻译成"z"。
 * 一个数字可能有多个翻译。例如12258有5种不同的翻译，它们分别是"bccfi"、"bwfi"、"bczi"、"mcfi"和"mzi"。
 * 请编程实现一个函数用来计算一个数字有多少种不同的翻译方法。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E46_TranslateNumbersToStrings {

	/*
	 * 把数字翻译成字符串
	 * 思路：如12258会计算1,2258和12,258，而2258只会计算2,258，
	 * 我们定义f(i)表示从第i位数字开始的不同翻译的数目，那么f(i) = f(i + 1) + g(i,i + 1) * f(i + 2)。
	 * 当第i位和第i + 1位两位数字拼接起来的数字在10 ~ 25的范围内时，函数g(i,i + 1)的值为1，否则为0.
	 */
	public int getTranslationCount(int number) {
		if (number < 0)
			return 0;
		if (number == 1)
			return 1;
		return getTranslationCount(Integer.toString(number));
	}

	// 动态规划，从右到左计算。
	private int getTranslationCount(String number) {

		int[] sum = new int[number.length()];
		int count = 0;
		// 从后往前递归查找
		for (int i = number.length() - 1; i >= 0; i--) {

			// 如果当前处理的不是最后一位
			count = 0;
			if (i < number.length() - 1) {
				count = sum[i + 1];
			} else {
				// 最后一个
				count = 1;
			}

			// 两位数可以合并
			if (i < number.length() - 1) {
				// 合并
				int combine = Integer.parseInt(number.charAt(i) + "" + number.charAt(i + 1));

				// 判断合并后的数字
				if (combine >= 10 && combine <= 25) {
					if (i < number.length() - 2) {
						count += sum[i + 2];
					} else {
						// 倒数第二位初始值
						count += 1;
					}
				}
			}
			sum[i] = count;
		}
		return sum[0];
	}

	// ====================测试代码====================
	private void test(String testName, int number, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("把数字翻译成字符串:\nResult:%d\nExpect:%d\n", getTranslationCount(number), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		test("test1", 0, 1);
	}

	private void test2() {
		test("test2", 10, 2);
	}

	private void test3() {
		test("test3", 125, 3);
	}

	private void test4() {
		test("test4", 126, 2);
	}

	private void test5() {
		test("test5", 426, 1);
	}

	private void test6() {
		test("test6", 100, 2);
	}

	private void test7() {
		test("test7", 101, 2);
	}

	private void test8() {
		test("test8", 12258, 5);
	}

	private void test9() {
		test("test9", -100, 0);
	}

	public static void main(String[] args) {

		E46_TranslateNumbersToStrings exam = new E46_TranslateNumbersToStrings();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
		exam.test8();
		exam.test9();
	}
}
