/**
 * 
 * 【剑指Offer】	面试题34 ：二叉树中和为某一值的路径
 * 【题目描述】	输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;
import java.util.Stack;

import cn.lancel0t.utilities.BinaryTreeNode;

public class E34_PathInTree {

	/*
	 * 二叉树中和为某一值的路径
	 * 思路：前序遍历访问某个节点时，把节点添加到路径中，如果该节点是叶子节点，判断路径中元素和是否为目标值，如果是，返回路径；
	 * 如果该节点不是叶子节点，继续访问子节点；
	 * 当前节点访问结束后，返回它的父节点，同时删除路径中该节点
	 */
	public ArrayList<ArrayList<Integer>> FindPath(BinaryTreeNode root, int target) {

		ArrayList<ArrayList<Integer>> allPath = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return allPath;

		Stack<Integer> pathStack = new Stack<Integer>();
		FindPath(root, target, pathStack, 0, allPath);

		return allPath;
	}

	/*
	 * 递归方法
	 */
	private void FindPath(BinaryTreeNode root, int target, Stack<Integer> pathStack, int pathSum,
			ArrayList<ArrayList<Integer>> allPath) {

		// 添加当前节点到路径中
		pathSum += root.val;
		pathStack.push(root.val);

		// 如果是叶子节点
		if (root.left == null && root.right == null) {
			// 找到满足的路径
			if (target == pathSum) {
				ArrayList<Integer> path = new ArrayList<Integer>();
				for (int i = 0; i < pathStack.size(); i++) {
					path.add(pathStack.get(i));
				}
				allPath.add(path);
			}
		}
		// 不是叶子节点
		else {
			// 遍历它的子节点
			if (root.left != null)
				FindPath(root.left, target, pathStack, pathSum, allPath);
			if (root.right != null)
				FindPath(root.right, target, pathStack, pathSum, allPath);
		}

		// 在返回到父结点之前，在路径上删除当前结点
		pathSum -= root.val;
		pathStack.pop();
	}

	// ====================测试代码====================
	private void test(String testName, BinaryTreeNode root, int target) {
		try {
			System.out.printf("=====%s=====\n", testName);
			ArrayList<ArrayList<Integer>> allPath = FindPath(root, target);
			if (allPath.isEmpty()) {
				System.out.println("没有满足的路径！\n");
				return;
			}
			int i = 1;
			for (ArrayList<Integer> path : allPath) {
				System.out.printf("第%d条路径：", i++);
				for (Integer val : path) {
					System.out.print(val + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 * 有两条路径上的结点和为22
	 *       10
	 *    /      \
	 *   5        12
	 *  /\        
	 * 4  7     
	 */
	private void test1() {
		BinaryTreeNode pNode10 = new BinaryTreeNode(10);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode12 = new BinaryTreeNode(12);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode7 = new BinaryTreeNode(7);

		BinaryTreeNode.connectTreeNodes(pNode10, pNode5, pNode12);
		BinaryTreeNode.connectTreeNodes(pNode5, pNode4, pNode7);

		test("test1", pNode10, 22);
	}

	/*
	 *  没有路径上的结点和为15
	 *       10
	 *    /      \
	 *   5        12
	 *  /\        
	 * 4  7     
	 */
	private void test2() {
		BinaryTreeNode pNode10 = new BinaryTreeNode(10);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode12 = new BinaryTreeNode(12);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode7 = new BinaryTreeNode(7);

		BinaryTreeNode.connectTreeNodes(pNode10, pNode5, pNode12);
		BinaryTreeNode.connectTreeNodes(pNode5, pNode4, pNode7);

		test("test2", pNode10, 15);
	}

	/*
	 *  有一条路径上面的结点和为15
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
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode3 = new BinaryTreeNode(3);
		BinaryTreeNode pNode2 = new BinaryTreeNode(2);
		BinaryTreeNode pNode1 = new BinaryTreeNode(1);

		BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
		BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
		BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);
		BinaryTreeNode.connectTreeNodes(pNode2, pNode1, null);

		test("test3", pNode5, 15);
	}

	/*
	 *  没有路径上面的结点和为16
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
		BinaryTreeNode pNode1 = new BinaryTreeNode(1);
		BinaryTreeNode pNode2 = new BinaryTreeNode(2);
		BinaryTreeNode pNode3 = new BinaryTreeNode(3);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNode1, null, pNode2);
		BinaryTreeNode.connectTreeNodes(pNode2, null, pNode3);
		BinaryTreeNode.connectTreeNodes(pNode3, null, pNode4);
		BinaryTreeNode.connectTreeNodes(pNode4, null, pNode5);

		test("test4", pNode1, 16);
	}

	// 树中只有1个结点
	private void test5() {
		BinaryTreeNode pNode1 = new BinaryTreeNode(1);
		test("test4", pNode1, 1);
	}

	// 树中没有结点
	private void test6() {
		test("test5", null, 0);
	}

	public static void main(String[] args) {

		E34_PathInTree exam = new E34_PathInTree();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
	}
}
