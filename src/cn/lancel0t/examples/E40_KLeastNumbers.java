/**
 * 
 * 【剑指Offer】	面试题40 ：最小的k个数
 * 【题目描述】	输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;
import java.util.TreeSet;

public class E40_KLeastNumbers {

	/*
	 * 最小的k个数
	 * 思路：如果允许修改数组且不要求最小k个数有序，可以用Partition解决，基于数组第k个元素调整，比第k个数字小的所有数字
	 * 位于数组的左边，比第k个数字大的所有数组位于数组的右边，递归找出前k个元素。该算法时间复杂度O(n)
	 * 这里采用另外一种思路：
	 * 可以利用java类库中红黑树TreeSet，如果容器中的数目小于k个，我们可以将当前元素添加到容器中；
	 * 当容器已经满的时候，需要找到容器中最大的数，如果待插入的数比容器中最大数还大，则抛弃；
	 * 否则的话，删除容器中最大值，插入当前元素。最终容器中的k个元素即为我们需要的。
	 */
	public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {

		ArrayList<Integer> result = new ArrayList<Integer>();

		if (input == null || input.length < k || k <= 0)
			return result;

		TreeSet<Integer> treeSet = new TreeSet<Integer>();

		for (int i = 0; i < input.length; i++) {
			// 容器未满，直接插入
			if (treeSet.size() < k) {
				treeSet.add(input[i]);
			} else {
				// 容器满了，比较与容器最大值，如果比最大值小，插入容器，否则抛弃
				int max = treeSet.last();
				if (input[i] < max) {
					treeSet.remove(max);
					treeSet.add(input[i]);
				}
			}
		}

		for (Integer val : treeSet) {
			result.add(val);
		}
		return result;
	}

	// ====================测试代码====================
	private void test(String testName, int[] input, int k) {
		try {
			System.out.printf("=====%s=====\n", testName);
			ArrayList<Integer> result = GetLeastNumbers_Solution(input, k);
			if (input != null) {
				System.out.println("输入数组为:");
				for (Integer val : input) {
					System.out.print(val + " ");
				}
				System.out.println();

				System.out.println("最小的k个数:");
				for (Integer val : result) {
					System.out.print(val + " ");
				}
				System.out.println();
			} else {
				System.out.println("输入数组非法！");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// k小于数组的长度
	private void test1() {
		test("test1", new int[] { 4, 5, 1, 6, 2, 7, 3, 8 }, 4);
	}

	// k等于数组的长度
	private void test2() {
		test("test2", new int[] { 4, 5, 1, 6, 2, 7, 3, 8 }, 8);
	}

	// k大于数组的长度
	private void test3() {
		test("test3", new int[] { 4, 5, 1, 6, 2, 7, 3, 8 }, 10);
	}

	// k等于1
	private void test4() {
		test("test4", new int[] { 4, 5, 1, 6, 2, 7, 3, 8 }, 1);
	}

	// k等于0
	private void test5() {
		test("test5", new int[] { 4, 5, 1, 6, 2, 7, 3, 8 }, 0);
	}

	// 数组中有相同的数字
	private void test6() {
		test("test6", new int[] { 4, 5, 1, 6, 2, 7, 2, 8 }, 4);
	}

	// 输入空指针
	private void test7() {
		test("test7", null, 0);
	}

	public static void main(String[] args) {

		E40_KLeastNumbers exam = new E40_KLeastNumbers();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
	}
}
