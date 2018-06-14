/**
 * 
 * 【剑指Offer】	面试题66 ：构建乘积数组
 * 【题目描述】	给定一个数组A[0, 1, …, n-1]，请构建一个数组B[0, 1, …, n-1]，
 *  其中B中的元素B[i] =A[0]×A[1]×… ×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E66_ConstuctArray {

	/*
	 * 构建乘积数组
	 * 思路：使用一个数组，保存元素组某位置，例如i,保存i前的所以元素的乘积;
	 * 另外一个数组，保存i后所有元素的乘积，最后这两个数组相乘
	 */
	public int[] multiply(int[] A) {

		int[] B = new int[A.length];
		int[] C = new int[A.length];

		int n = A.length;
		B[0] = 1;
		C[n - 1] = 1;

		for (int i = 1; i < A.length; i++) {
			B[i] = B[i - 1] * A[i - 1];
			C[n - 1 - i] = C[n - i] * A[n - i];
		}

		for (int i = 0; i < n; i++) {
			B[i] = C[i] * B[i];
		}

		return B;
	}

	// ====================测试代码====================
	private void test(String testName, int[] A) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.println("构建乘积数组:");
			System.out.print("输入的数组:");
			for (int i : A) {
				System.out.print(i + " ");
			}
			System.out.println();
			System.out.print("输出的数组:");
			int[] B = multiply(A);
			for (int i : B) {
				System.out.print(i + " ");
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// 输入数组中没有0
	private void test1() {
		int numbers[] = { 1, 2, 3, 4, 5 };
		test("test1", numbers);
	}

	// 输入数组中有一个0
	private void test2() {
		int numbers[] = { 1, 2, 0, 4, 5 };
		test("test2", numbers);
	}

	// 输入数组中有两个0
	private void test3() {
		int numbers[] = { 1, 2, 0, 4, 0 };
		test("test3", numbers);
	}

	// 输入数组中有正、负数
	private void test4() {
		int numbers[] = { 1, -2, 3, -4, 5 };
		test("test4", numbers);
	}

	// 输入输入中只有两个数字
	private void test5() {
		int numbers[] = { 1, -2 };
		test("test5", numbers);
	}

	public static void main(String[] args) {

		E66_ConstuctArray exam = new E66_ConstuctArray();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
	}
}
