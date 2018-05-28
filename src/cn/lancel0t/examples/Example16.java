/**
 * 
 * 【剑指Offer】面试题16 ：数值的整数次方
 * 【  题目描述 】给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class Example16 {

	public double Power(double base, int exponent) {

		// 指数和底数同时为0
		if (base == 0 && exponent == 0) {
			return 0;
		}

		// 指数为0就返回1
		if (exponent == 0) {
			return 1;
		}

		// 求指数的绝对值
		long exp = exponent;
		if (exponent < 0) {
			exp = -exp;
		}

		// 求正整数次幂
		double result = powerWithUnsignedExponent(base, exp);

		// 指数是负数，要进行求倒数
		if (exponent < 0) {
			result = 1 / result;
		}

		// 返回结果
		return result;
	}

	// 求一个数的正整数次幂
	private double powerWithUnsignedExponent(double base, long exponent) {

		// 如果指数为0，返回1
		if (exponent == 0) {
			return 1;
		}

		// 指数为1，返回底数
		if (exponent == 1) {
			return base;
		}

		// 递归求一半的值
		double result = powerWithUnsignedExponent(base, exponent >> 1);

		// 求最终的值，如果是奇数就还要剩以一次底数
		result *= result;
		if (exponent % 2 != 0) {
			result *= base;
		}

		// 返回结果
		return result;
	}

	public static void main(String[] args) {

		Example16 exam = new Example16();

		System.out.println(exam.Power(2, 3) + "[8.0]");
		System.out.println(exam.Power(-2, 3) + "[-8.0]");
		System.out.println(exam.Power(2, -3) + "[0.125]");
		System.out.println(exam.Power(2, 0) + "[1.0]");
		System.out.println(exam.Power(0, 0) + "[0.0]");
		System.out.println(exam.Power(0, 4) + "[0.0]");
		System.out.println(exam.Power(0, -4) + "[Infinity]");
	}
}
