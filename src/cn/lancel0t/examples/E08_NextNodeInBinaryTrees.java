/**
 * 
 * 【剑指Offer】面试题8 ：二叉树的下一个结点
 * 【  题目描述 】给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E08_NextNodeInBinaryTrees {

	// 节点定义
	public class TreeLinkNode {
		int val;
		TreeLinkNode left = null;
		TreeLinkNode right = null;
		TreeLinkNode next = null;

		TreeLinkNode(int val) {
			this.val = val;
		}
	}

	public TreeLinkNode GetNext(TreeLinkNode pNode) {
		if (pNode == null)
			return null;

		TreeLinkNode curNode = null;

		// 第一步：判断是否有右孩子，找右孩子最左叶子节点
		if (pNode.right != null) {
			curNode = pNode.right;
			while (curNode.left != null)
				curNode = curNode.left;
			return curNode;
		}

		// 第二步：判断是否是其父节点的左孩子，下个节点即父节点
		if (pNode.next == null)
			return null;
		if (pNode == pNode.next.left) {
			return pNode.next;
		}

		// 第三步：向上找其父节点，直到父节点是其父节点的父节点的左孩子
		curNode = pNode.next;
		while (curNode.next != null) {
			if (curNode.next.left == curNode)
				return curNode.next;
			// 继续向上找父节点
			curNode = curNode.next;
		}

		return null;
	}

	private void connectTreeNodes(TreeLinkNode node, TreeLinkNode left, TreeLinkNode right) {
		if (node != null) {
			node.left = left;
			node.right = right;
			if (left != null)
				left.next = node;
			if (right != null)
				right.next = node;
		}
	}

	// ====================测试代码====================
	private void test(TreeLinkNode pNode, String expect) {
		try {
			TreeLinkNode node = GetNext(pNode);
			System.out.printf("节点%s下一个结点：Result:%s \t Expect:%s\n", pNode.val, node == null ? "∅" : node.val, expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 *      8
	 *  6      10
	 * 5 7    9  11
	 */
	private void test1() {
		TreeLinkNode pNode8 = new TreeLinkNode(8);
		TreeLinkNode pNode6 = new TreeLinkNode(6);
		TreeLinkNode pNode10 = new TreeLinkNode(10);
		TreeLinkNode pNode5 = new TreeLinkNode(5);
		TreeLinkNode pNode7 = new TreeLinkNode(7);
		TreeLinkNode pNode9 = new TreeLinkNode(9);
		TreeLinkNode pNode11 = new TreeLinkNode(11);

		connectTreeNodes(pNode8, pNode6, pNode10);
		connectTreeNodes(pNode6, pNode5, pNode7);
		connectTreeNodes(pNode10, pNode9, pNode11);

		System.out.println("=====Test1=====");
		test(pNode8, "9");
		test(pNode6, "7");
		test(pNode10, "11");
		test(pNode5, "6");
		test(pNode7, "8");
		test(pNode9, "10");
		test(pNode11, "∅");
		System.out.println();
	}

	/*
	 *        5
	 *      4
	 *    3
	 *  2
	 */
	private void test2() {
		TreeLinkNode pNode5 = new TreeLinkNode(5);
		TreeLinkNode pNode4 = new TreeLinkNode(4);
		TreeLinkNode pNode3 = new TreeLinkNode(3);
		TreeLinkNode pNode2 = new TreeLinkNode(2);

		connectTreeNodes(pNode5, pNode4, null);
		connectTreeNodes(pNode4, pNode3, null);
		connectTreeNodes(pNode3, pNode2, null);

		System.out.println("=====Test2=====");
		test(pNode5, "∅");
		test(pNode4, "5");
		test(pNode3, "4");
		test(pNode2, "3");
		System.out.println();
	}

	/*
	 *    2
	 *     3
	 *      4
	 *       5
	 */
	private void test3() {
		TreeLinkNode pNode2 = new TreeLinkNode(2);
		TreeLinkNode pNode3 = new TreeLinkNode(3);
		TreeLinkNode pNode4 = new TreeLinkNode(4);
		TreeLinkNode pNode5 = new TreeLinkNode(5);

		connectTreeNodes(pNode2, null, pNode3);
		connectTreeNodes(pNode3, null, pNode4);
		connectTreeNodes(pNode4, null, pNode5);

		System.out.println("=====Test3=====");
		test(pNode5, "∅");
		test(pNode4, "5");
		test(pNode3, "4");
		test(pNode2, "3");
		System.out.println();
	}

	/*
	 *   单个节点 
	 */
	private void test4() {
		TreeLinkNode pNode5 = new TreeLinkNode(5);

		System.out.println("=====Test4=====");
		test(pNode5, "∅");
		System.out.println();
	}

	public static void main(String[] args) {

		E08_NextNodeInBinaryTrees exam = new E08_NextNodeInBinaryTrees();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
	}
}
