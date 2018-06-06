/**
 * 
 * 【剑指Offer】	面试题44 ：数字序列中某一位的数字
 * 【题目描述】	数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从0开始计数）是5，第13位是1，第19位是4，等等。请写一个函数求任意位对应的数字。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E44_DigitsInSequence {

	/*
	 * 数字序列中某一位的数字
	 * 思路：分析数字分布的规律，我们可以得出，比如要获得1001位
	 * 1.序列前10位是0-9，跳过，在从后面找991位；
	 * 2.后面90*3位是10-99，跳过，在后面找881位；
	 * 3.后面900*3位是100-999，因为2700>881，所以811位是某个三位数中的一位；
	 * 4.由于811=270*3+1，这就是说811位是从100开始的第270个数字即370的中间一位，即7。
	 */
	public int digitAtIndex(int index) {

		if (index < 0)
			return -1;

		int digits = 1;
		while (true) {
			int numbers = countOfIntegers(digits);
			if (index < numbers * digits)
				return digitAtIndex(index, digits);

			index -= digits * numbers;
			digits++;
		}
	}

	// digits表示位数，统计该位数时需要跳过多少数字
	private int countOfIntegers(int digits) {
		if (digits == 1)
			return 10;
		int count = (int) Math.pow(10, digits - 1);

		return 9 * count;
	}

	// digits表示位数，获得该位数下第index的数值
	private int digitAtIndex(int index, int digits) {

		int number = beginNumber(digits) + index / digits;
		int indexFromRight = digits - index % digits;
		for (int i = 1; i < indexFromRight; i++)
			number /= 10;

		return number % 10;
	}

	// digits表示位数，获得该位数的起始数值
	private int beginNumber(int digits) {
		if (digits == 1)
			return 0;

		return (int) Math.pow(10, digits - 1);
	}

	// ====================测试代码====================
	private void test(String testName, int index, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("数字序列中第%d位的数字:\nResult:%d\nExpect:%d\n", index, digitAtIndex(index), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		E44_DigitsInSequence exam = new E44_DigitsInSequence();

		exam.test("test1", 0, 0);
		exam.test("test2", 1, 1);
		exam.test("test3", 9, 9);
		exam.test("test4", 10, 1);
		exam.test("test5", 189, 9); // 数字99的最后一位，9
		exam.test("test6", 190, 1); // 数字100的第一位，1
		exam.test("test7", 1000, 3); // 数字370的第一位，3
		exam.test("test8", 1001, 7); // 数字370的第二位，7
		exam.test("test9", 1002, 0); // 数字370的第三位，0
	}
}
