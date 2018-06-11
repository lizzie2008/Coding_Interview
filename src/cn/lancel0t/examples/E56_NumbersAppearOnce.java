/**
 * 
 * 【剑指Offer】	面试题56 ：数组中只出现一次的两个数字
 * 【题目描述】	一个整型数组里除了两个数字之外，其他的数字都出现了两次。
 *  请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E56_NumbersAppearOnce {

	/*
	 * 数组中只出现一次的两个数字
	 * 思路：考虑如果是求数组中只出现一次的数字，我们可以从头到尾，异或数组中每一个数字，最终异或的值即为出现一次的数字；
	 * 同理，我们如上异或数组中每一个数字，最终的值应该是出现一次的两个数字的异或值，且不为0，肯定有1个bit位为1，
	 * 将原先的数组根据对应bit位是否为1，分成2个数组，出现一次的两个数字肯定分布在2个数组中。最后分别采用异或方法处理
	 * 2个数组，即得到结果。
	 */
	public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {

		if (array == null || array.length < 2)
			return;

		// 获得只出现一次的两个数字异或值
		int resultExclusiveOR = 0;
		for (int i = 0; i < array.length; ++i)
			resultExclusiveOR ^= array[i];

		int indexOf1 = FindFirstBitIs1(resultExclusiveOR);

		// 根据bit1不同分组异或
		for (int j = 0; j < array.length; ++j) {
			if (isBit1(array[j], indexOf1))
				num1[0] ^= array[j];
			else
				num2[0] ^= array[j];
		}
	}

	// 查找非0数字倒数第几位才为1
	private int FindFirstBitIs1(int num) {
		int indexBit = 0;
		while (((num & 1) == 0) && (indexBit < 32)) {
			num = num >> 1;
			++indexBit;
		}

		return indexBit;
	}

	// 判断数字倒数第几位是否是1
	private boolean isBit1(int num, int indexBit) {
		num = num >> indexBit;
		return (num & 1) > 0;
	}

	// ====================测试代码====================
	private void test(String testName, int[] array, int num1[], int num2[], String expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			FindNumsAppearOnce(array, num1, num2);
			System.out.printf("数组中只出现一次的两个数字:\nResult:%d,%d\nExpect:%s\n", num1[0], num2[0], expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		int data[] = { 2, 4, 3, 6, 3, 2, 5, 5 };
		test("test1", data, new int[1], new int[1], "4,6");
	}

	private void test2() {
		int data[] = { 4, 6 };
		test("test2", data, new int[1], new int[1], "4,6");
	}

	private void test3() {
		int data[] = { 4, 6, 1, 1, 1, 1 };
		test("test3", data, new int[1], new int[1], "4,6");
	}

	public static void main(String[] args) {

		E56_NumbersAppearOnce exam = new E56_NumbersAppearOnce();

		exam.test1();
		exam.test2();
		exam.test3();
	}
}
