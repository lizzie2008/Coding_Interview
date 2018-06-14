/**
 * 
 * 【剑指Offer】	面试题68 ：树中两个结点的最低公共祖先
 * 【题目描述】	输入两个树结点，求它们的最低公共祖先。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;
import java.util.List;
import cn.lancel0t.utilities.TreeNode;

public class E68_CommonParentInTree {

	/*
	 * 树中两个结点的最低公共祖先（假设普通树（多个子树），子树没有指向父节点的指针）
	 * 思路：用两个链表分别保存从根节点到输入的两个节点的路径，然后把问题转换成两个链表的最后公共节点。
	 */
	public TreeNode getLastCommonParent(TreeNode root, TreeNode p1, TreeNode p2) {

		// path1和path2分别存储根节点到p1和p2的路径（不包括p1和p2）
		List<TreeNode> path1 = new ArrayList<TreeNode>();
		List<TreeNode> path2 = new ArrayList<TreeNode>();
		List<TreeNode> tmpList = new ArrayList<TreeNode>();

		getNodePath(root, p1, tmpList, path1);
		getNodePath(root, p2, tmpList, path2);
		// 如果路径不存在，返回空
		if (path1.size() == 0 || path2.size() == 0)
			return null;

		return getLastCommonParent(path1, path2);
	}

	// 获取根节点到目标节点的路径
	private void getNodePath(TreeNode root, TreeNode target, List<TreeNode> tmpList,
			List<TreeNode> path) {

		if (root == null || root == target)
			return;

		tmpList.add(root);
		List<TreeNode> children = root.children;
		for (TreeNode node : children) {
			if (node == target) {
				path.addAll(tmpList);
				break;
			}
			getNodePath(node, target, tmpList, path);
		}

		tmpList.remove(tmpList.size() - 1);
	}

	// 将问题转化为求链表最后一个共同节点
	private TreeNode getLastCommonParent(List<TreeNode> p1, List<TreeNode> p2) {
		TreeNode tmpNode = null;
		for (int i = 0; i < p1.size() && i < p2.size(); i++) {
			if (p1.get(i) == p2.get(i))
				tmpNode = p1.get(i);
		}

		return tmpNode;
	}

	// ====================测试代码====================
	private void test(String testName, TreeNode root, TreeNode p1, TreeNode p2, TreeNode expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("树中两个结点的最低公共祖先:\nResult:%s\nExpect:%s\n",
					getLastCommonParent(root, p1, p2), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 * 形状普通的树
	 *             1
	 *           /   \
	 *          2     3
	 *      /       \
	 *     4         5
	 *    / \      / |  \
	 *   6   7    8  9  10
	 */
	private void test1() {
		TreeNode pNode1 = new TreeNode(1);
		TreeNode pNode2 = new TreeNode(2);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode6 = new TreeNode(6);
		TreeNode pNode7 = new TreeNode(7);
		TreeNode pNode8 = new TreeNode(8);
		TreeNode pNode9 = new TreeNode(9);
		TreeNode pNode10 = new TreeNode(10);

		TreeNode.connectTreeNodes(pNode1, pNode2);
		TreeNode.connectTreeNodes(pNode1, pNode3);
		TreeNode.connectTreeNodes(pNode2, pNode4);
		TreeNode.connectTreeNodes(pNode2, pNode5);
		TreeNode.connectTreeNodes(pNode4, pNode6);
		TreeNode.connectTreeNodes(pNode4, pNode7);
		TreeNode.connectTreeNodes(pNode5, pNode8);
		TreeNode.connectTreeNodes(pNode5, pNode9);
		TreeNode.connectTreeNodes(pNode5, pNode10);

		test("test1", pNode1, pNode6, pNode8, pNode2);
	}

	/*
	 * 树退化成一个链表
	 *              1
	 *             /
	 *            2
	 *           /
	 *          3
	 *         /
	 *        4
	 *       /
	 *      5
	 */
	private void test2() {
		TreeNode pNode1 = new TreeNode(1);
		TreeNode pNode2 = new TreeNode(2);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode5 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNode1, pNode2);
		TreeNode.connectTreeNodes(pNode2, pNode3);
		TreeNode.connectTreeNodes(pNode3, pNode4);
		TreeNode.connectTreeNodes(pNode4, pNode5);

		test("test2", pNode1, pNode5, pNode4, pNode3);
	}

	/*
	 * 树退化成一个链表，一个结点不在树中
	 *               1
	 *              /
	 *             2
	 *            /
	 *           3
	 *          /
	 *         4
	 *        /
	 *       5
	 */
	private void test3() {
		TreeNode pNode1 = new TreeNode(1);
		TreeNode pNode2 = new TreeNode(2);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode5 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNode1, pNode2);
		TreeNode.connectTreeNodes(pNode2, pNode3);
		TreeNode.connectTreeNodes(pNode3, pNode4);
		TreeNode.connectTreeNodes(pNode4, pNode5);

		TreeNode pNode6 = new TreeNode(6);

		test("test3", pNode1, pNode5, pNode6, null);
	}

	// 输入nullptr
	private void test4() {
		test("test3", null, null, null, null);
	}

	public static void main(String[] args) {

		E68_CommonParentInTree exam = new E68_CommonParentInTree();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
	}
}
