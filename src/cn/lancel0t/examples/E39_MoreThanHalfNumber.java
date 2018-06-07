/**
 * 
 * 【剑指Offer】	面试题39 ：数组中出现次数超过一半的数字
 * 【题目描述】	数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E39_MoreThanHalfNumber {

	/*
	 * 数组中出现次数超过一半的数字
	 * 思路：考虑保存当前判断的数字和次数，遍历数组，如果下一个数字跟保存的数字相等，则次数加1；
	 * 如果不相等，次数减1。如果次数为零，容易证明不可能为超过一半的数字，保存下一个数字，并设置次数为1；
	 * 最后保存的数字即为出现频率最高的数，再判断是否满足超过一半。
	 */
	public int MoreThanHalfNum_Solution(int[] array) {

		if (array == null || array.length <= 0)
			return 0;

		int result = array[0];
		int count = 1;

		for (int num : array) {
			// 如果不可能为超过一半的数字，替换为下一个数字
			if (count == 0) {
				result = num;
				count = 1;
			} else if (result == num) {
				// 增加
				count++;
			} else {
				// 抵消
				count--;
			}
		}

		// 判断频率最高的数，是否满足超过一半
		if (!CheckMoreThanHalf(array, result))
			result = 0;
		return result;
	}

	// 检查是否超过一半
	private boolean CheckMoreThanHalf(int[] array, int number) {
		int times = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == number)
				times++;
		}

		boolean isMoreThanHalf = true;
		if (times * 2 <= array.length) {
			isMoreThanHalf = false;
		}

		return isMoreThanHalf;
	}

	// ====================测试代码====================
	private void test(String testName, int[] array, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("出现次数超过一半的数字:Result:%d \t Expect:%d\n", MoreThanHalfNum_Solution(array),
					expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		E39_MoreThanHalfNumber exam = new E39_MoreThanHalfNumber();

		exam.test("test1", new int[] { 1, 2, 3, 2, 2, 2, 5, 4, 2 }, 2);
		exam.test("test2", new int[] { 1, 2, 3, 2, 4, 2, 5, 2, 3 }, 0);
		exam.test("test3", new int[] { 2, 2, 2, 2, 2, 1, 3, 4, 5 }, 2);
		exam.test("test4", new int[] { 1, 3, 4, 5, 2, 2, 2, 2, 2 }, 2);
		exam.test("test5", new int[] { 1 }, 1);
		exam.test("test6", null, 0);
	}
}
