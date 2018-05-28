/**
 * 
 * 【剑指Offer】面试题10 ： 斐波那契数列
 * 【  题目描述 】大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class Example10 {

	// 迭代非递归
	public int Fibonacci(int n) {

		if (n < 2)
			return n;

		int fibn = 0;
		for (int fib0 = 0, fib1 = 1, i = 2; i <= n; i++) {
			fibn = fib0 + fib1;
			fib0 = fib1;
			fib1 = fibn;
		}
		return fibn;
	}

	/*
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
	 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
	 */
	public int JumpFloor(int target) {
		if (target <= 2)
			return target;

		int fibn = 0;
		for (int fib0 = 1, fib1 = 2, i = 3; i <= target; i++) {
			fibn = fib0 + fib1;
			fib0 = fib1;
			fib1 = fibn;
		}
		return fibn;
	}

	/*
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
	 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
	 */
	public int JumpFloorII(int target) {

		if (target < 2)
			return target;

		return 2 * JumpFloorII(target - 1);
	}

	/*
	 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
	 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
	 */
	public int RectCover(int target) {
		if (target <= 2)
			return target;

		return RectCover(target - 1) + RectCover(target - 2);
	}

	public static void main(String[] args) {

		Example10 exam = new Example10();

		System.out.print("===前20斐波那契数列：");
		for (int i = 0; i < 20; i++) {
			System.out.print(exam.Fibonacci(i) + " ");
		}
		System.out.println();

		System.out.println("===青蛙1跳20级台阶，跳法总数：" + exam.JumpFloor(20));

		System.out.println("===青蛙2跳20级台阶，跳法总数：" + exam.JumpFloorII(20));

		System.out.println("===2×8矩形覆盖，总共方法总数：" + exam.RectCover(8));
	}
}
