/**
 * 
 * 【剑指Offer】	面试题62 ：圆圈中最后剩下的数字
 * 【题目描述】	0, 1, …, n-1这n个数字排成一个圆圈，从数字0开始每次从这个圆圈里删除第m个数字。
 *  求出这个圆圈里剩下的最后一个数字。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E62_LastNumberInCircle {

	/*
	 * 圆圈中最后剩下的数字
	 * 思路：约瑟夫环问题，如果用数组取模，很容易实现，规律如下：
	 * f(n,m)=0  n=1
	 * f(n,m)=[f(n-1,m)+m]%n  n>1
	 */
	public int LastRemaining_Solution(int n, int m) {
		if (n < 1 || m < 1)
			return -1;

		int last = 0;
		for (int i = 2; i <= n; i++)
			last = (last + m) % i;

		return last;
	}

	// ====================测试代码====================
	private void test(String testName, int n, int m, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("圆圈中最后剩下的数字:\nResult:%d\nExpect:%d\n", LastRemaining_Solution(n, m),
					expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		test("test1", 5, 3, 3);
	}

	private void test2() {
		test("test2", 5, 2, 2);
	}

	private void test3() {
		test("test3", 6, 7, 4);
	}

	private void test4() {
		test("test4", 6, 6, 3);
	}

	private void test5() {
		test("test5", 0, 0, -1);
	}

	private void test6() {
		test("test6", 4000, 997, 1027);
	}

	public static void main(String[] args) {

		E62_LastNumberInCircle exam = new E62_LastNumberInCircle();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
	}
}
