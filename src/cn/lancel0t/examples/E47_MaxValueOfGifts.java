/**
 * 
 * 【剑指Offer】	面试题47 ：礼物的最大价值
 * 【题目描述】	在一个m×n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物，请计算你最多能拿到多少价值的礼物？
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E47_MaxValueOfGifts {

	/*
	 * 礼物的最大价值
	 * 思路：动态规划，申请一个与原矩阵行列数一样的二维数组maxValues[][]，
	 * 然后依次找到向左或向上两者的最大值，加上当前值更新maxValues的当前位置值。
	 */
	public int getMaxValueOfGifts(int[][] data) {

		if (data == null)
			return 0;

		int rows = data.length;
		int cols = data[0].length;
		if (rows == 0 || cols == 0)
			return 0;

		int[][] maxValues = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				int left = 0;
				int up = 0;

				if (i > 0)
					up = maxValues[i - 1][j];

				if (j > 0)
					left = maxValues[i][j - 1];

				//比较之前的位置能取到的最大值，并累计当前值
				maxValues[i][j] = Math.max(left, up) + data[i][j];
			}
		}

		return maxValues[rows - 1][cols - 1];
	}

	// ====================测试代码====================
	private void test(String testName, int[][] data, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("礼物的最大价值:\nResult:%d\nExpect:%d\n", getMaxValueOfGifts(data), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		// 三行三列
		int values[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		test("test1", values, 29);
	}

	private void test2() {
		// 四行四列
		int values[][] = { { 1, 10, 3, 8 }, { 12, 2, 9, 6 }, { 5, 7, 4, 11 }, { 3, 7, 16, 5 } };
		test("test2", values, 53);
	}

	private void test3() {
		// 一行四列
		int values[][] = { { 1, 10, 3, 8 } };
		test("test3", values, 22);
	}

	private void test4() {
		int values[][] = { { 1 }, { 12 }, { 5 }, { 3 } };
		test("test4", values, 21);
	}

	private void test5() {
		// 一行一列
		int values[][] = { { 3 } };
		test("test5", values, 3);
	}

	private void test6() {
		test("test6", null, 0);
	}

	public static void main(String[] args) {

		E47_MaxValueOfGifts exam = new E47_MaxValueOfGifts();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
	}
}
