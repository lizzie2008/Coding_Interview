/**
 * 
 * 【剑指Offer】面试题4 ：二维数组中的查找
 * 【  题目描述 】在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E04_FindInPartiallySortedMatrix {

	/*
	 * 定义数组下标i,j始终指向矩阵右上角的位置
	 * 如果matrix[i][j]大于目标值，往前面的列 查找
	 * 如果matrix[i][j]小于目标值，往后面的行查找
	 */
	public boolean Find(int target, int[][] array) {
		// 有效性检查
		if (array == null || array.length <= 0 || array[0].length <= 0)
			return false;

		int rows = array.length;
		int cols = array[0].length;

		// i,j始终指向矩阵右上角的位置
		for (int i = 0, j = cols - 1; i <= rows - 1 && j >= 0;) {
			if (array[i][j] > target) {
				j--;
			} else if (array[i][j] < target) {
				i++;
			} else {
				return true;
			}
		}

		return false;
	}

	// ====================测试代码====================
	private void test(String testName, int target, int[][] array, boolean expect) {
		System.out.println(testName);
		System.out.printf("二维数组中的查找：Result:%b \t Expect:%b\n\n", Find(target, array), expect);
	}

	public static void main(String[] args) {
		E04_FindInPartiallySortedMatrix exam = new E04_FindInPartiallySortedMatrix();

		// 1 2 8 9
		// 2 4 9 12
		// 4 7 10 13
		// 6 8 11 15
		// 要查找的数在数组中
		int[][] matrix1 = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
		exam.test("=====Test1=====", 7, matrix1, true);

		// 1 2 8 9
		// 2 4 9 12
		// 4 7 10 13
		// 6 8 11 15
		// 要查找的数不在数组中
		int[][] matrix2 = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
		exam.test("=====Test2=====", 5, matrix2, false);

		// 1 2 8 9
		// 2 4 9 12
		// 4 7 10 13
		// 6 8 11 15
		// 要查找的数是数组中最小的数字
		int[][] matrix3 = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
		exam.test("=====Test3=====", 1, matrix3, true);

		// 1 2 8 9
		// 2 4 9 12
		// 4 7 10 13
		// 6 8 11 15
		// 要查找的数是数组中最大的数字
		int[][] matrix4 = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
		exam.test("=====Test4=====", 15, matrix4, true);

		// 1 2 8 9
		// 2 4 9 12
		// 4 7 10 13
		// 6 8 11 15
		// 要查找的数比数组中最小的数字还小
		int[][] matrix5 = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
		exam.test("=====Test5=====", 0, matrix5, false);

		// 1 2 8 9
		// 2 4 9 12
		// 4 7 10 13
		// 6 8 11 15
		// 要查找的数比数组中最大的数字还大
		int[][] matrix6 = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
		exam.test("=====Test6=====", 16, matrix6, false);

		// 鲁棒性测试，输入空指针
		exam.test("=====Test7=====", 16, null, false);
	}

}
