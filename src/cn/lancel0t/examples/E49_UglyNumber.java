/**
 * 
 * 【剑指Offer】	面试题49 ：丑数
 * 【题目描述】	我们把只包含因子2、3和5的数称作丑数（Ugly Number）。求按从小到大的顺序的第1500个丑数。
 * 例如6、8都是丑数，但14不是，因为它包含因子7。习惯上我们把1当做第一个丑数。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E49_UglyNumber {

	/*
	 * 丑数
	 * 思路：最简单的思路，按从小到大顺序，依次判断当前元素是否是丑数，即验证是否能一直被2、3和5除，最后不为1。
	 * 这里时间复杂度更好的解法：
	 * 构建一个index大小的数组，将已知的丑数乘以2、3和5，依次添加到数组相应位置，最终数组最后的位置即为所求。
	 * 思路的关键怎么确保数组里的丑数是排好序的：记录当前的最大值M，如果已知的每个丑数乘以2，能得到若干结果，
	 * 因为小于或等于M的丑数肯定已经存在数组中了，所以不用考虑，我们只考虑第一个大于M的结果，同理乘以3、乘以5一样。
	 * 那么，下一个丑数取这3个大于M结果中最小的一个。
	 * 因为只考虑一个大于M的结果，我们没必要将已知所有的丑数进行乘法运算，对于乘以2的丑数来说，肯定存在某个丑数，
	 * 排它之前的所有丑数乘2的结果都会小于已有的最大丑数，在它之后的每个丑数乘以2的结果都会太大。记录这个丑数的位置T2，
	 * 同时每次生成新的丑数时更新这个T2即可，同理存在T3和T5。
	 */
	public int GetUglyNumber_Solution(int index) {

		if (index <= 0)
			return 0;

		int[] uglyNumbers = new int[index];
		uglyNumbers[0] = 1;

		int t2 = 0;
		int t3 = 0;
		int t5 = 0;
		for (int i = 1; i < index; i++) {

			uglyNumbers[i] = Min(uglyNumbers[t2] * 2, uglyNumbers[t3] * 3, uglyNumbers[t5] * 5);

			while (uglyNumbers[t2] * 2 <= uglyNumbers[i])
				t2++;
			while (uglyNumbers[t3] * 3 <= uglyNumbers[i])
				t3++;
			while (uglyNumbers[t5] * 5 <= uglyNumbers[i])
				t5++;

		}

		return uglyNumbers[index - 1];
	}

	// 取3个整数最小值
	private int Min(int number1, int number2, int number3) {
		int min = (number1 < number2) ? number1 : number2;
		min = (min < number3) ? min : number3;

		return min;
	}

	// ====================测试代码====================
	private void test(String testName, int index, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("从小到大的顺序的第%d丑数:\nResult:%d\nExpect:%d\n", index, GetUglyNumber_Solution(index),
					expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		test("test1", 1, 1);
	}

	private void test2() {
		test("test2", 2, 2);
	}

	private void test3() {
		test("test3", 3, 3);
	}

	private void test4() {
		test("test4", 4, 4);
	}

	private void test5() {
		test("test5", 5, 5);
	}

	private void test6() {
		test("test6", 6, 6);
	}

	private void test7() {
		test("test7", 7, 8);
	}

	private void test8() {
		test("test8", 8, 9);
	}

	private void test9() {
		test("test9", 9, 10);
	}

	private void test10() {
		test("test10", 10, 12);
	}

	private void test11() {
		test("test10", 11, 15);
	}

	private void test12() {
		test("test10", 1500, 859963392);
	}

	private void test13() {
		test("test10", 0, 0);
	}

	public static void main(String[] args) {

		E49_UglyNumber exam = new E49_UglyNumber();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
		exam.test8();
		exam.test9();
		exam.test10();
		exam.test11();
		exam.test12();
		exam.test13();
	}
}
