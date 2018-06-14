/**
 * 
 * 【剑指Offer】	面试题37 ：序列化二叉树
 * 【题目描述】	请实现两个函数，分别用来序列化和反序列化二叉树
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.LinkedList;
import java.util.Queue;

import cn.lancel0t.utilities.BinaryTreeNode;

public class E37_SerializeBinaryTrees {

	/*
	 * 序列化
	 * 思路：如果二叉树的序列化是从根节点开始，那么对应的而反序列化也是从根节点开始的。
	 * 因此可以使用二叉树的前序遍历来序列化二叉树，数值之间用","隔开，当前序遍历碰到null值时，使用"#"表示。
	 */
	public String Serialize(BinaryTreeNode root) {

		StringBuilder sb = new StringBuilder();

		if (root == null)
			return sb.append("#,").toString();

		sb.append(root.val + ",");
		sb.append(Serialize(root.left));
		sb.append(Serialize(root.right));
		return sb.toString();
	}

	/*
	 * 反序列化
	 * 思路：将序列化的字符串按照","分隔成单个元素，依次遍历，构造节点和左右子树，"#"按照无对应叶节点处理。
	 */
	public BinaryTreeNode Deserialize(String str) {
		String[] elements = str.split(",");
		Queue<String> queue = new LinkedList<String>();
		for (String ele : elements) {
			queue.offer(ele);
		}
		return Deserialize(queue);
	}

	private BinaryTreeNode Deserialize(Queue<String> queue) {
		BinaryTreeNode node = null;
		String val = queue.poll();
		if (!val.equals("#")) {
			node = new BinaryTreeNode(Integer.parseInt(val));
			node.left = Deserialize(queue);
			node.right = Deserialize(queue);
		}
		return node;
	}

	// ====================测试代码====================
	private boolean isSameTree(BinaryTreeNode root1, BinaryTreeNode root2) {
		if (root1 == null && root2 == null)
			return true;

		if (root1 == null || root2 == null)
			return false;

		if (root1.val != root2.val)
			return false;

		return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
	}

	private void test(String testName, BinaryTreeNode root) {
		try {
			System.out.printf("=====%s=====\n", testName);
			String str = Serialize(root);
			BinaryTreeNode node = Deserialize(str);
			if (isSameTree(root, node)) {
				System.out.println("序列化和反序列化成功！");
			} else {
				System.out.println("序列化和反序列化失败！");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 *      8
	 *  6      10
	 * 5 7    9  11
	 */
	private void test1() {
		BinaryTreeNode pNode8 = new BinaryTreeNode(8);
		BinaryTreeNode pNode6 = new BinaryTreeNode(6);
		BinaryTreeNode pNode10 = new BinaryTreeNode(10);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode7 = new BinaryTreeNode(7);
		BinaryTreeNode pNode9 = new BinaryTreeNode(9);
		BinaryTreeNode pNode11 = new BinaryTreeNode(11);

		BinaryTreeNode.connectTreeNodes(pNode8, pNode6, pNode10);
		BinaryTreeNode.connectTreeNodes(pNode6, pNode5, pNode7);
		BinaryTreeNode.connectTreeNodes(pNode10, pNode9, pNode11);

		test("test1", pNode8);
	}

	/*
	 *          5
	 *         /
	 *        4
	 *       /
	 *      3
	 *     /
	 *    2
	 */
	private void test2() {
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode3 = new BinaryTreeNode(3);
		BinaryTreeNode pNode2 = new BinaryTreeNode(2);

		BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
		BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
		BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);

		test("test2", pNode5);
	}

	/*
	 *   5
	 *    \
	 *     4
	 *      \
	 *       3
	 *        \
	 *         2
	 */
	private void test3() {

		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode3 = new BinaryTreeNode(3);
		BinaryTreeNode pNode2 = new BinaryTreeNode(2);

		BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
		BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
		BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);

		test("test3", pNode5);
	}

	// 树中只有1个结点
	private void test4() {
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		test("test4", pNode5);
	}

	// 树中没有结点
	private void test5() {
		test("test5", null);
	}

	/*
	 *   5
	 *    5
	 *     5
	 *    5
	 *   5
	 *  5 5
	 * 5   5
	 */
	private void test6() {
		BinaryTreeNode pNode1 = new BinaryTreeNode(5);
		BinaryTreeNode pNode2 = new BinaryTreeNode(5);
		BinaryTreeNode pNode3 = new BinaryTreeNode(5);
		BinaryTreeNode pNode4 = new BinaryTreeNode(5);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode61 = new BinaryTreeNode(5);
		BinaryTreeNode pNode62 = new BinaryTreeNode(5);
		BinaryTreeNode pNode71 = new BinaryTreeNode(5);
		BinaryTreeNode pNode72 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNode1, null, pNode2);
		BinaryTreeNode.connectTreeNodes(pNode2, null, pNode3);
		BinaryTreeNode.connectTreeNodes(pNode3, pNode4, null);
		BinaryTreeNode.connectTreeNodes(pNode4, pNode5, null);
		BinaryTreeNode.connectTreeNodes(pNode5, pNode61, pNode62);
		BinaryTreeNode.connectTreeNodes(pNode61, pNode71, null);
		BinaryTreeNode.connectTreeNodes(pNode62, null, pNode72);

		test("test6", pNode5);
	}

	public static void main(String[] args) {

		E37_SerializeBinaryTrees exam = new E37_SerializeBinaryTrees();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
	}
}
