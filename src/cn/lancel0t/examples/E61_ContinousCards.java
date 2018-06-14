/**
 * 
 * 【剑指Offer】	面试题61 ：扑克牌的顺子
 * 【题目描述】	从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 *  2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王可以看成任意数字。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.Arrays;

public class E61_ContinousCards {

	/*
	 * 扑克牌的顺子
	 * 思路：可以首先统计牌中大小王的总数，然后从小到大排序后，如果正常顺子，应该相邻间隔1，如果间隔大于1比如间隔3，
	 * 就至少需要3-1=2张大小王来冲抵。统计整个数组中间隔总数，比较间隔总数是否满足小于等于大小王的总数即可。
	 */
	public boolean isContinuous(int[] numbers) {

		if (numbers == null || numbers.length < 1)
			return false;

		// 数组排序
		Arrays.sort(numbers);

		int numberOfZero = 0;
		int numberOfGap = 0;

		// 统计数组中0的个数
		for (int i = 0; i < numbers.length && numbers[i] == 0; i++)
			numberOfZero++;

		// 统计数组中的间隔数目
		int small = numberOfZero;
		int big = small + 1;

		while (big < numbers.length) {

			// 两个数相等，有对子，不可能是顺子
			if (numbers[small] == numbers[big])
				return false;

			numberOfGap += numbers[big] - numbers[small] - 1;
			small = big;
			big++;
		}

		return (numberOfGap > numberOfZero) ? false : true;
	}

	// ====================测试代码====================
	private void test(String testName, int[] numbers, boolean expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("扑克牌是否是顺子:\nResult:%b\nExpect:%b\n", isContinuous(numbers), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		int numbers[] = { 1, 3, 2, 5, 4 };
		test("test1", numbers, true);
	}

	private void test2() {
		int numbers[] = { 1, 3, 2, 6, 4 };
		test("test2", numbers, false);
	}

	private void test3() {
		int numbers[] = { 0, 3, 2, 6, 4 };
		test("test3", numbers, true);
	}

	private void test4() {
		int numbers[] = { 0, 3, 1, 6, 4 };
		test("test4", numbers, false);
	}

	private void test5() {
		int numbers[] = { 1, 3, 0, 5, 0 };
		test("test5", numbers, true);
	}

	private void test6() {
		int numbers[] = { 1, 3, 0, 7, 0 };
		test("test6", numbers, false);
	}

	private void test7() {
		int numbers[] = { 1, 0, 0, 5, 0 };
		test("test7", numbers, true);
	}

	private void test8() {
		int numbers[] = { 1, 0, 0, 7, 0 };
		test("test8", numbers, false);
	}

	private void test9() {
		int numbers[] = { 3, 0, 0, 0, 0 };
		test("test9", numbers, true);
	}

	private void test10() {
		int numbers[] = { 0, 0, 0, 0, 0 };
		test("test10", numbers, true);
	}

	// 有对子
	private void test11() {
		int numbers[] = { 1, 0, 0, 1, 0 };
		test("test11", numbers, false);
	}

	// 鲁棒性测试
	private void test12() {
		int numbers[] = null;
		test("test12", numbers, false);
	}

	public static void main(String[] args) {

		E61_ContinousCards exam = new E61_ContinousCards();

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
	}
}
