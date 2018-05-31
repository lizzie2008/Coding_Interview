/**
 * 
 * 【剑指Offer】	面试题33 ：二叉搜索树的后序遍历序列
 * 【题目描述】	输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。
 * 假设输入的数组的任意两个数字都互不相同。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.Arrays;

public class E33_SquenceOfBST {

	/*
	 * 判断是不是后序遍历
	 * 思路：分析后序遍历的特性，先遍历节点的所有左孩子，再遍历节点的右孩子，最后访问该节点；
	 * 所以，数组的前面一部分是左孩子的节点，然后一部分是右孩子的所有节点，最后一位是当前节点；
	 * 可以首先获得数组最后一个元素即为该节点，我们 验证前面一部分所有元素小于该节点，满足是都左孩子，
	 * 再验证剩下一部分的所有元素大于该节点，满足都是右孩子，接着递归验证左孩子元素部分和右孩子元素部分即可。
	 */
	public boolean VerifySquenceOfBST(int[] sequence) {

		if (sequence == null || sequence.length <= 0)
			return false;

		int length = sequence.length;
		// 根节点元素
		int root = sequence[length - 1];

		// 记录到右孩子开始下标位置
		int i = 0;
		for (i = 0; i < length - 1; i++) {
			if (sequence[i] > root)
				break;
		}

		// 如果后续元素中不满足是右孩子的条件，说明违反后序遍历特性
		for (int j = i; j < length - 1; j++) {
			if (sequence[j] < root)
				return false;
		}

		// 判断左子树
		boolean left = true;
		if (i > 0)
			left = VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, i));

		// 判断右子树
		boolean right = true;
		if (i < length - 1)
			right = VerifySquenceOfBST(Arrays.copyOfRange(sequence, i, length - 1));

		return left && right;
	}

	// ====================测试代码====================
	private void test(String testName, int[] sequence, boolean expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("是否后序遍历：Result:%b \t Expect:%b", VerifySquenceOfBST(sequence), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n");
	}

	/*
	 *        10
	 *     /      \
	 *    6        14
	 *   /\        /\
	 *  4  8     12  16
	 */
	private void test1() {
		int data[] = { 4, 8, 6, 12, 16, 14, 10 };
		test("test1", data, true);
	}

	/*
	 *    5
	 *   / \
	 *  4   7
	 *     /
	 *    6
	 */
	private void test2() {
		int data[] = { 4, 6, 7, 5 };
		test("test2", data, true);
	}

	/*
	 *         5
	 *        /
	 *       4
	 *      /
	 *     3
	 *    /
	 *   2
	 *  /
	 * 1
	 */
	private void test3() {
		int data[] = { 1, 2, 3, 4, 5 };
		test("test3", data, true);
	}

	/*
	 * 1
	 *  \
	 *   2
	 *    \
	 *     3
	 *      \
	 *       4
	 *        \
	 *         5
	 */
	private void test4() {
		int data[] = { 5, 4, 3, 2, 1 };
		test("test4", data, true);
	}

	// 树中只有1个结点
	private void test5() {
		int data[] = { 5 };
		test("test5", data, true);
	}

	private void test6() {
		int data[] = { 7, 4, 6, 5 };
		test("test6", data, false);
	}

	private void test7() {
		int data[] = { 4, 6, 12, 8, 16, 14, 10 };
		test("test7", data, false);
	}

	private void test8() {
		int data[] = null;
		test("test8", data, false);
	}

	public static void main(String[] args) {

		E33_SquenceOfBST exam = new E33_SquenceOfBST();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
		exam.test8();
	}
}
