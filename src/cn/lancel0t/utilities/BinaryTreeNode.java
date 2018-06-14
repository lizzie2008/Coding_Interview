/**
 * 二叉树节点及辅助方法
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.utilities;

public class BinaryTreeNode {

	public int val = 0;
	public BinaryTreeNode left = null;
	public BinaryTreeNode right = null;

	public BinaryTreeNode(int val) {
		this.val = val;
	}

	public static void connectTreeNodes(BinaryTreeNode pParent, BinaryTreeNode pLeft,
			BinaryTreeNode pRight) {
		if (pParent != null) {
			pParent.left = pLeft;
			pParent.right = pRight;
		}
	}

	public static void PrintTreeNode(BinaryTreeNode pNode) {
		if (pNode != null) {
			System.out.printf("节点值: %s\t", pNode.val);
			System.out.printf("左孩子: %s\t", pNode.left != null ? pNode.left.val : "∅");
			System.out.printf("右孩子: %s\n", pNode.right != null ? pNode.right.val : "∅");
		} else {
			System.out.printf("节点为∅\n");
		}
	}

	public static void printTree(BinaryTreeNode pRoot) {
		PrintTreeNode(pRoot);

		if (pRoot != null) {
			if (pRoot.left != null)
				printTree(pRoot.left);

			if (pRoot.right != null)
				printTree(pRoot.right);
		}
	}

	@Override
	public String toString() {
		return val + "";
	}

}
