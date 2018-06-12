/**
 * 
 * 【剑指Offer】	面试题60 ：n个骰子的点数
 * 【题目描述】	把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E60_DicesProbability {

	/*
	 * n个骰子的点数
	 * 思路：设n个骰子某次投掷点数和为s的出现次数是F(n, s)，
	 * 那么，F(n, s)等于n - 1个骰子投掷的点数和为s - 1、s - 2、s - 3、s -4、s - 5、s - 6时的次数的总和：
	 * F(n , s) = F(n - 1, s - 1) + F(n - 1, s - 2) + F(n - 1, s - 3) + F(n - 1, s - 4) 
	 * + F(n - 1, s - 5) + F(n - 1, s - 6)。
	 */
	public void PrintProbability(int n) {
		int i = 0;
		int nTotal = (int) Math.pow((double) 6, n);
		for (i = n; i <= 6 * n; i++) {
			System.out.printf("P(s=%d) = %d/%d\n", i, Probability(n, i), nTotal);
		}
	}

	// 计算n个骰子某次投掷点数和为s的出现次数
	private int Probability(int n, int s) {
		// n个骰子点数之和范围在n到6n之间，否则数据不合法
		if (s < n || s > 6 * n)
			return 0;
		// 当有一个骰子时，一次骰子点数为s(1 <= s <= 6)的次数当然是1
		if (n == 1)
			return 1;
		else
			return Probability(n - 1, s - 6) + Probability(n - 1, s - 5) + Probability(n - 1, s - 4)
					+ Probability(n - 1, s - 3) + Probability(n - 1, s - 2) + Probability(n - 1, s - 1);
	}

	// ====================测试代码====================
	private void test(String testName, int number) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("%d个骰子的点数:\n", number);
			PrintProbability(number);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		E60_DicesProbability exam = new E60_DicesProbability();

		exam.test("test1", 1);
		exam.test("test2", 2);
		exam.test("test3", 3);
		exam.test("test4", 4);
		exam.test("test5", 11);
		exam.test("test6", 0);
	}
}
