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
	 * 用2个数组来存储骰子点数每一个总数出现的次数，下次循环时，加上一个新骰子，此时和为n的骰子等于上一次循环
	 * 中骰子点数和为n-1、n-2、n-3、n-4、n-5、n-6次数总和，依次将最新循环计数分别来回记录在2个数组中。
	 */
	public void PrintProbability(int number) {
		if (number < 1)
			return;

		int[][] pProbabilities = new int[][] { new int[6 * number + 1], new int[6 * number + 1] };

		int flag = 0;
		for (int i = 1; i <= 6; i++)
			pProbabilities[flag][i] = 1;

		for (int k = 2; k <= number; k++) {

			// 数值范围[k,6k],所以0-k置零
			for (int i = 0; i < k; i++)
				pProbabilities[1 - flag][i] = 0;

			for (int i = k; i <= 6 * k; i++) {
				pProbabilities[1 - flag][i] = 0;
				// 等于上一次循环 中骰子点数和为n-1、n-2、n-3、n-4、n-5、n-6次数总和
				for (int j = 1; j <= i && j <= 6; j++)
					pProbabilities[1 - flag][i] += pProbabilities[flag][i - j];
			}

			flag = 1 - flag;
		}

		int total = (int) Math.pow((double) 6, number);
		for (int i = number; i <= 6 * number; i++) {
			System.out.printf("P(s=%d) = %d/%d\n", i, pProbabilities[flag][i], total);
		}
	}

	// 递归法
	public void PrintProbabilityI(int n) {
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

		// exam.test("test1", 1);
		exam.test("test2", 2);
		exam.test("test3", 3);
		exam.test("test4", 4);
		exam.test("test5", 11);
		exam.test("test6", 0);
	}
}
