/**
 * 
 * 【剑指Offer】	面试题15 ：二进制中1 的个数
 * 【题目描述】	输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E15_NumberOf1InBinary {

	/*
	 * 常规解法，flag初始为1，每次左移1位，n和flag做与运算，从低位依次统计
	 */
	public int NumberOf1I(int n) {
		int count = 0;
		int flag = 1;
		while (flag != 0) {
			if ((n & flag) != 0)
				count++;

			flag = flag << 1;
		}
		return count;
	}

	/*
	 * 推荐解法
	 * 利用二进制特性：(整数-1)&整数，会将该整数最右边的1变成0
	 */
	public int NumberOf1(int n) {
		int count = 0;

		while (n != 0) {
			count++;
			n = (n - 1) & n;
		}
		return count;
	}

	// ====================测试代码====================
	private void test(String testName, int n, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("二进制：%s\n", Integer.toBinaryString(n));
			System.out.printf("二进制中1 的个数：Result:%d \t Expect:%d\n\n", NumberOf1(n), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {

		E15_NumberOf1InBinary exam = new E15_NumberOf1InBinary();

		exam.test("test1", 0, 0);
		exam.test("test2", 1, 1);
		exam.test("test3", 10, 2);
		exam.test("test4", 0x7FFFFFFF, 31);
		exam.test("test5", 0xFFFFFFFF, 32);
		exam.test("test6", 0x80000000, 1);

	}
}
