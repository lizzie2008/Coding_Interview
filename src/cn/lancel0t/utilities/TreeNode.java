/**
 * 树节点及辅助方法
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.utilities;

public class TreeNode {

	public int val = 0;
	public TreeNode left = null;
	public TreeNode right = null;

	public TreeNode(int val) {
		this.val = val;
	}

	public static void connectTreeNodes(TreeNode pParent, TreeNode pLeft, TreeNode pRight) {
		if (pParent != null) {
			pParent.left = pLeft;
			pParent.right = pRight;
		}
	}

	public static void PrintTreeNode(TreeNode pNode) {
		if (pNode != null) {
			System.out.printf("节点值: %s\t", pNode.val);
			System.out.printf("左孩子: %s\t", pNode.left != null ? pNode.left.val : "∅");
			System.out.printf("右孩子: %s\n", pNode.right != null ? pNode.right.val : "∅");
		} else {
			System.out.printf("节点为∅\n");
		}
	}

	public static void printTree(TreeNode pRoot) {
		PrintTreeNode(pRoot);

		if (pRoot != null) {
			if (pRoot.left != null)
				printTree(pRoot.left);

			if (pRoot.right != null)
				printTree(pRoot.right);
		}
	}

	public static void main(String[] args) {

	}

}
