/**
 * 
 * 【剑指Offer】	面试题63 ：股票的最大利润
 * 【题目描述】	假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖交易该股票可能获得的利润是多少？
 *  例如一只股票在某些时间节点的价格为{9, 11, 8, 5, 7, 12, 16, 14}。
 *  如果我们能在价格为5的时候买入并在价格为16时卖出，则能收获最大的利润11。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E63_MaximalProfit {

	/*
	 * 股票的最大利润
	 * 思路：记录最低的买入价格、最大的利润，遍历时判断之前最低价格是否更小，如果更小，更新最低的买入价格；
	 * 此时判断最大的利润是否比之前保存的利润还大，如果还大，则更新最大利润值。
	 */
	public int MaxDiff(int[] numbers) {

		if (numbers == null || numbers.length < 2)
			return 0;

		int min = numbers[0];// 用来保存买入时的最低价格
		int maxDiff = numbers[1] - min;// 用来保存最大的利润

		for (int i = 2; i < numbers.length; i++) {
			if (numbers[i - 1] < min)
				min = numbers[i - 1];

			int currentDiff = numbers[i] - min;
			if (currentDiff > maxDiff)
				maxDiff = currentDiff;
		}

		return maxDiff;
	}

	// ====================测试代码====================
	private void test(String testName, int[] numbers, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("股票的最大利润:\nResult:%d\nExpect:%d\n", MaxDiff(numbers), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		int numbers[] = { 4, 1, 3, 2, 5 };
		test("test1", numbers, 4);
	}

	// 价格递增
	private void test2() {
		int numbers[] = { 1, 2, 4, 7, 11, 16 };
		test("test2", numbers, 15);
	}

	// 价格递减
	private void test3() {
		int numbers[] = { 16, 11, 7, 4, 2, 1 };
		test("test3", numbers, -1);
	}

	// 价格全部相同
	private void test4() {
		int numbers[] = { 16, 16, 16, 16, 16 };
		test("test4", numbers, 0);
	}

	private void test5() {
		int numbers[] = { 9, 11, 5, 7, 16, 1, 4, 2 };
		test("test5", numbers, 11);
	}

	private void test6() {
		int numbers[] = { 2, 4 };
		test("test6", numbers, 2);
	}

	private void test7() {
		int numbers[] = { 4, 2 };
		test("test7", numbers, -2);
	}

	private void test8() {
		int numbers[] = null;
		test("test8", numbers, 0);
	}

	public static void main(String[] args) {

		E63_MaximalProfit exam = new E63_MaximalProfit();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
		exam.test8();
	}
}
