/**
 * 
 * 【剑指Offer】面试题3 ：数组中重复的数字
 * 【  题目描述 】在一个长度为n的数组里的所有数字都在0到n-1的范围内。 
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 * 请找出数组中任意一个重复的数字。 
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E03_DuplicationInArray {

	/*
	 * 思路1：利用数组排序，时间复杂度：O(nlogn)，空间复杂度:O(1)
	 * 思路2：利用哈希表，时间复杂度：O(n)，空间复杂度:O(n)
	 * 
	 * 推荐思路：0~n-1正常的排序应该是A[i]=i；因此可以通过交换的方式，将它们都各自放回属于自己的位置；
	 * 时间复杂度：O(n)，空间复杂度：O(1)
	 */
	public boolean duplicate(int numbers[], int length, int[] duplication) {
		if (numbers == null || length <= 0)
			return false;

		for (int i = 0; i < length; i++) {
			if (numbers[i] < 0 || numbers[i] > length - 1)
				return false;
		}

		// 从头到尾扫描数组
		for (int i = 0; i < length; i++) {
			// 记录下标i的值
			int k = numbers[i];
			// 如果numbers[i]不等于i
			if (numbers[i] != i) {
				// 判断与numbers[k]是否相等
				if (numbers[i] == numbers[k]) {
					// 相等表示找到重复的元素，并返回
					duplication[0] = numbers[i];
					return true;
				} else {
					// 不是重复元素，交换位置
					numbers[i] = numbers[k];
					numbers[k] = k;
				}
			}
		}
		return false;
	}

	private void test(int numbers[], int expect) {
		int[] duplication = new int[1];
		int length = numbers == null ? 0 : numbers.length;
		if (duplicate(numbers, length, duplication))
			System.out.printf("含有重复元素：Result:%d\t Expect:%d\n", duplication[0], expect);
		else
			System.out.println("没有重复元素。");
	}

	public static void main(String[] args) {
		E03_DuplicationInArray exam = new E03_DuplicationInArray();

		// 重复的数字是数组中最小的数字
		exam.test(new int[] { 2, 1, 3, 1, 4 }, 1);
		// 重复的数字是数组中最大的数字
		exam.test(new int[] { 2, 4, 3, 1, 4 }, 4);
		// 数组中存在多个重复的数字
		exam.test(new int[] { 2, 4, 2, 1, 4 }, 2);
		// 没有重复的数字
		exam.test(new int[] { 2, 1, 3, 0, 4 }, -1);
		// 数字范围不在0~n-1
		exam.test(new int[] { 2, 1, 3, 5, 4 }, -1);
		// 无效的输入
		exam.test(null, -1);
	}

}
