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

import cn.lancel0t.utilities.TreeNode;

public class E37_SerializeBinaryTrees {

	/*
	 * 序列化
	 * 思路：如果二叉树的序列化是从根节点开始，那么对应的而反序列化也是从根节点开始的。
	 * 因此可以使用二叉树的前序遍历来序列化二叉树，数值之间用","隔开，当前序遍历碰到null值时，使用"#"表示。
	 */
	public String Serialize(TreeNode root) {

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
	public TreeNode Deserialize(String str) {
		String[] elements = str.split(",");
		Queue<String> queue = new LinkedList<String>();
		for (String ele : elements) {
			queue.offer(ele);
		}
		return Deserialize(queue);
	}

	private TreeNode Deserialize(Queue<String> queue) {
		TreeNode node = null;
		String val = queue.poll();
		if (!val.equals("#")) {
			node = new TreeNode(Integer.parseInt(val));
			node.left = Deserialize(queue);
			node.right = Deserialize(queue);
		}
		return node;
	}

	// ====================测试代码====================
	private boolean isSameTree(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;

		if (root1 == null || root2 == null)
			return false;

		if (root1.val != root2.val)
			return false;

		return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
	}

	private void test(String testName, TreeNode root) {
		try {
			System.out.printf("=====%s=====\n", testName);
			String str = Serialize(root);
			TreeNode node = Deserialize(str);
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
		TreeNode pNode8 = new TreeNode(8);
		TreeNode pNode6 = new TreeNode(6);
		TreeNode pNode10 = new TreeNode(10);
		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode7 = new TreeNode(7);
		TreeNode pNode9 = new TreeNode(9);
		TreeNode pNode11 = new TreeNode(11);

		TreeNode.connectTreeNodes(pNode8, pNode6, pNode10);
		TreeNode.connectTreeNodes(pNode6, pNode5, pNode7);
		TreeNode.connectTreeNodes(pNode10, pNode9, pNode11);

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
		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode2 = new TreeNode(2);

		TreeNode.connectTreeNodes(pNode5, pNode4, null);
		TreeNode.connectTreeNodes(pNode4, pNode3, null);
		TreeNode.connectTreeNodes(pNode3, pNode2, null);

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

		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode2 = new TreeNode(2);

		TreeNode.connectTreeNodes(pNode5, pNode4, null);
		TreeNode.connectTreeNodes(pNode4, pNode3, null);
		TreeNode.connectTreeNodes(pNode3, pNode2, null);

		test("test3", pNode5);
	}

	// 树中只有1个结点
	private void test4() {
		TreeNode pNode5 = new TreeNode(5);
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
		TreeNode pNode1 = new TreeNode(5);
		TreeNode pNode2 = new TreeNode(5);
		TreeNode pNode3 = new TreeNode(5);
		TreeNode pNode4 = new TreeNode(5);
		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode61 = new TreeNode(5);
		TreeNode pNode62 = new TreeNode(5);
		TreeNode pNode71 = new TreeNode(5);
		TreeNode pNode72 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNode1, null, pNode2);
		TreeNode.connectTreeNodes(pNode2, null, pNode3);
		TreeNode.connectTreeNodes(pNode3, pNode4, null);
		TreeNode.connectTreeNodes(pNode4, pNode5, null);
		TreeNode.connectTreeNodes(pNode5, pNode61, pNode62);
		TreeNode.connectTreeNodes(pNode61, pNode71, null);
		TreeNode.connectTreeNodes(pNode62, null, pNode72);

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
