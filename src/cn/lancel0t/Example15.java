/**
 * 
 * 【剑指Offer】面试题15 ：二进制中1 的个数
 * 【  题目描述 】输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example15 {

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

	public static void main(String[] args) {

		Example15 exam = new Example15();

		System.out.println("=============算法1输出：");
		System.out.println(exam.NumberOf1I(0B00000000_00000000_00000000_00000000)); // 0
		System.out.println(exam.NumberOf1I(0B00000000_00000000_00000000_00000001)); // 1
		System.out.println(exam.NumberOf1I(0B11111111_11111111_11111111_11111111)); // -1
		System.out.println(exam.NumberOf1I(0B01111111_11111111_11111111_11111111)); // Integer.MAX_VALUE
		System.out.println(exam.NumberOf1I(0B10000000_00000000_00000000_00000000)); // Integer.MIN_VALUE
		System.out.println("=============算法2输出：");
		System.out.println(exam.NumberOf1(0B00000000_00000000_00000000_00000000)); // 0
		System.out.println(exam.NumberOf1(0B00000000_00000000_00000000_00000001)); // 1
		System.out.println(exam.NumberOf1(0B11111111_11111111_11111111_11111111)); // -1
		System.out.println(exam.NumberOf1(0B01111111_11111111_11111111_11111111)); // Integer.MAX_VALUE
		System.out.println(exam.NumberOf1(0B10000000_00000000_00000000_00000000)); // Integer.MIN_VALUE

	}
}
