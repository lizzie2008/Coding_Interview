/**
 * 
 * 【剑指Offer】	面试题42 ：连续子数组的最大和
 * 【题目描述】	输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E42_GreatestSumOfSubarrays {

	/*
	 * 连续子数组的最大和
	 * 思路：首先模拟一个输入数组{1,-2,3,10,-4,7,2,-5}
	 * 	步骤		操作				累加和		最大子数组和
	 * 	1		加1				1			1
	 * 	2		加-2				-1			1
	 * 	3		抛弃前面-1,加3	3			3
	 * 	4		加10				13			13
	 * 	5		加-4				9			13
	 * 	6		加7				16			16
	 * 	7		加2				18			18
	 * 	8		加-5				13			18
	 * 总结出上面的规律，和容易写出算法
	 */
	public int FindGreatestSumOfSubArray(int[] array) {

		if (array == null || array.length <= 0)
			throw new RuntimeException("输入参数非法！");

		int curSum = 0;
		int greatestSum = Integer.MIN_VALUE;

		for (int i = 0; i < array.length; i++) {
			// 如果当前累加和小于等于0，如示例第3步，累加和改为当前元素值
			if (curSum <= 0)
				curSum = array[i];
			// 继续将当前值累积到当前累加和中
			else
				curSum += array[i];

			// 如果当前值大于当前最大子数组和，更新最大子数组和
			if (curSum > greatestSum)
				greatestSum = curSum;
		}

		return greatestSum;
	}

	// ====================测试代码====================
	private void test(String testName, int[] array, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("连续子数组的最大和:Result:%d \t Expect:%d\n", FindGreatestSumOfSubArray(array),
					expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		E42_GreatestSumOfSubarrays exam = new E42_GreatestSumOfSubarrays();

		exam.test("test1", new int[] { 1, -2, 3, 10, -4, 7, 2, -5 }, 18);
		exam.test("test2", new int[] { -2, -8, -1, -5, -9 }, -1);
		exam.test("test3", new int[] { 2, 8, 1, 5, 9 }, 25);
		exam.test("test4", null, 0);
	}
}
