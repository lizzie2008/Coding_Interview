/**
 * 
 * 【剑指Offer】	面试题29 ：顺时针打印矩阵
 * 【题目描述】	输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;

public class E29_PrintMatrix {

	/*
	 * 顺时针打印矩阵
	 */
	public ArrayList<Integer> printMatrix(int[][] matrix) {
		ArrayList<Integer> printList = new ArrayList<Integer>();

		int rows = matrix.length;
		if (rows <= 0)
			return printList;
		int cols = matrix[0].length;
		if (cols <= 0)
			return printList;

		int start = 0;
		while (rows > start * 2 && cols > start * 2) {
			PrintMatrixInCircle(matrix, rows, cols, start, printList);
			++start;
		}
		return printList;
	}

	// 顺时针打印矩阵
	private void PrintMatrixInCircle(int[][] matrix, int rows, int cols, int start,
			ArrayList<Integer> printList) {

		int endX = cols - 1 - start;
		int endY = rows - 1 - start;

		// 从左向右打印一行
		for (int i = start; i <= endX; i++) {
			printList.add(matrix[start][i]);
		}

		// 从上到下打印一列
		if (start < endY) {
			for (int i = start + 1; i <= endY; i++) {
				printList.add(matrix[i][endX]);
			}
		}

		// 从右到左打印一行
		if (start < endX && start < endY) {
			for (int i = endX - 1; i >= start; i--) {
				printList.add(matrix[endY][i]);
			}
		}

		// 从下到上打印一列
		if (start < endX && start < endY - 1) {
			for (int i = endY - 1; i >= start + 1; i--) {
				printList.add(matrix[i][start]);
			}
		}
	}

	// ====================测试代码====================
	private void test(String testName, int[][] matrix) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.print("顺时针打印数组：");
			ArrayList<Integer> list = printMatrix(matrix);
			if (list.isEmpty()) {
				System.out.print("∅");
			} else {
				for (Integer val : list) {
					System.out.print(val + " ");
				}
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	1    
	*/
	private void test1() {
		int[][] matrix = new int[][] { { 1 } };
		test("test1", matrix);
	}

	/*
	1    2
	3    4
	*/
	private void test2() {
		int[][] matrix = new int[][] { { 1, 2 }, { 3, 4 } };
		test("test2", matrix);
	}

	/*
	1    2    3    4
	5    6    7    8
	9    10   11   12
	13   14   15   16
	*/
	private void test3() {
		int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		test("test3", matrix);
	}

	/*
	1    2    3    4    5
	6    7    8    9    10
	11   12   13   14   15
	16   17   18   19   20
	21   22   23   24   25
	*/
	private void test4() {
		int[][] matrix = new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 },
				{ 16, 17, 18, 19, 20 }, { 21, 22, 23, 24, 25 } };
		test("test4", matrix);
	}

	/*
	1
	2
	3
	4
	5
	*/
	private void test5() {
		int[][] matrix = new int[][] { { 1 }, { 2 }, { 3 }, { 4 }, { 5 } };
		test("test5", matrix);
	}

	/*
	1    2
	3    4
	5    6
	7    8
	9    10
	*/
	private void test6() {
		int[][] matrix = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 }, { 9, 10 } };
		test("test6", matrix);
	}

	/*
	1    2    3
	4    5    6
	7    8    9
	10   11   12
	13   14   15
	*/
	private void test7() {
		int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9, }, { 10, 11, 12 },
				{ 13, 14, 15 } };
		test("test7", matrix);
	}

	/*
	1    2    3    4
	5    6    7    8
	9    10   11   12
	13   14   15   16
	17   18   19   20
	*/
	private void test8() {
		int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 },
				{ 17, 18, 19, 20 } };
		test("test8", matrix);
	}

	/*
	1    2    3    4    5
	*/
	private void test9() {
		int[][] matrix = new int[][] { { 1, 2, 3, 4, 5 } };
		test("test9", matrix);
	}

	/*
	1    2    3    4    5
	6    7    8    9    10
	*/
	private void test10() {
		int[][] matrix = new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 } };
		test("test10", matrix);
	}

	/*
	1    2    3    4    5
	6    7    8    9    10
	11   12   13   14   15
	*/
	private void test11() {
		int[][] matrix = new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 } };
		test("test11", matrix);
	}

	/*
	1    2    3    4    5
	6    7    8    9    10
	11   12   13   14   15
	16   17   18   19   20
	*/
	private void test12() {
		int[][] matrix = new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 },
				{ 16, 17, 18, 19, 20 } };
		test("test12", matrix);
	}

	public static void main(String[] args) {

		E29_PrintMatrix exam = new E29_PrintMatrix();

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
