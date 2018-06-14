/**
 * 
 * 【剑指Offer】	面试题64 ：求1+2+…+n
 * 【题目描述】	求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E64_Accumulate {

	/*
	 * 求1+2+…+n
	 * 思路：用逻辑与&&与短路特性实现递归终止
	 * 当n>0时，会一直递归执行Sum_Solution(n-1)
	 * 直到n==0，开始return sum
	 */
	public int Sum_Solution(int n) {
		int sum = n;
		@SuppressWarnings("unused")
		boolean ans = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
		return sum;
	}

	// ====================测试代码====================
	private void test(String testName, int n, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("求1+2+…+n:\nResult:%d\nExpect:%d\n", Sum_Solution(n), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		test("test1", 1, 1);
	}

	private void test2() {
		test("test2", 5, 15);
	}

	private void test3() {
		test("test3", 10, 55);
	}

	private void test4() {
		test("test4", 0, 0);
	}

	public static void main(String[] args) {

		E64_Accumulate exam = new E64_Accumulate();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
	}
}
