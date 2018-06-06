/**
 * 
 * 【剑指Offer】	面试题45 ：把数组排成最小的数
 * 【题目描述】	输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3, 32, 321}，则打印出这3个数字能排成的最小数字321323。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E45_SortArrayForMinNumber {

	/*
	 * 把数组排成最小的数
	 * 思路：类似于字典序的问题，即对于数字a和b，它们之间的组合有ab和ba两种，要求我们打印出最小的一个组合数字出来；
	 * 如果ab小于ba，则a是在b的前面; 如果ab大于ba，则b在a的前面;
	 * 按照这种思路，对于一个输入数组中的所有数字，我们按照冒泡排序的思想，每一轮进行两两比较，找出一个放在前面的数字出来。
	 */
	public String PrintMinNumber(int[] numbers) {

		if (numbers == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		String pre, last;
		int temp;
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				pre = numbers[i] + "" + numbers[j]; // 转换成字符串的形式
				last = numbers[j] + "" + numbers[i];
				if (pre.compareTo(last) > 0) { // 比较组合之后的ab和ba
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
				}
			}
			sb.append(numbers[i]);
		}

		return sb.toString();
	}

	// ====================测试代码====================
	private void test(String testName, int[] numbers, String expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("把数组排成最小的数:\nResult:%s\nExpect:%s\n", PrintMinNumber(numbers), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		int numbers[] = { 3, 5, 1, 4, 2 };
		test("test1", numbers, "12345");
	}

	private void test2() {
		int numbers[] = { 3, 32, 321 };
		test("test2", numbers, "321323");
	}

	private void test3() {
		int numbers[] = { 3, 323, 32123 };
		test("test3", numbers, "321233233");
	}

	private void test4() {
		int numbers[] = { 1, 11, 111 };
		test("test4", numbers, "111111");
	}

	private void test5() {
		int numbers[] = { 321 };
		test("test5", numbers, "321");
	}

	private void test6() {
		int numbers[] = null;
		test("test6", numbers, "null");
	}

	public static void main(String[] args) {

		E45_SortArrayForMinNumber exam = new E45_SortArrayForMinNumber();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
	}
}
