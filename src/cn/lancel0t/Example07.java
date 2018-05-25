/**
 * 
 * 【剑指Offer】面试题7 ：重建二叉树
 * 【  题目描述 】输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如：前序遍历序列｛ 1, 2, 4, 7, 3, 5, 6, 8｝和中序遍历序列｛4, 7, 2, 1, 5, 3, 8，6}，
 * 重建出下图所示的二叉树并输出它的头结点。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example07 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		// 输入的合法性判断，两个数组都不能为空，长度相等且有数据
		if (pre == null || in == null || pre.length != in.length || pre.length < 1) {
			return null;
		}

		return construct(pre, in, 0, pre.length - 1, 0, in.length - 1);
	}

	// 递归法，根据前序和中序，分别生成左右子树
	private TreeNode construct(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
		if (preStart > preEnd) {
			return null;
		}

		// 前序遍历第1个节点为根节点
		int rootVal = preOrder[preStart];

		// 获取根节点在中序遍历的索引位置
		int index = inStart;
		while (index <= inEnd && inOrder[index] != rootVal)
			index++;

		if (index > inEnd) {
			throw new RuntimeException("Invalid input");
		}

		TreeNode root = new TreeNode(rootVal);

		// 递归构建当前根结点的左子树，左子树的元素个数：index-inStart+1个
		// 左子树对应的前序遍历的位置在[preStart + 1, preStart + index - inStart]
		// 左子树对应的中序遍历的位置在[inStart, index-1]
		root.left = construct(preOrder, inOrder, preStart + 1, preStart + index - inStart, inStart, index - 1);
		// 递归构建当前根结点的右子树，右子树的元素个数：inEnd-index个
		// 右子树对应的前序遍历的位置在[preStart + index - inStart + 1, preEnd]
		// 右子树对应的中序遍历的位置在[index+1, inEnd]
		root.right = construct(preOrder, inOrder, preStart + index - inStart + 1, preEnd, index + 1, inEnd);

		return root;
	}

	// 中序遍历打印树
	public void printTree(TreeNode root) {
		if (root != null) {
			printTree(root.left);
			System.out.print(root.val + " ");
			printTree(root.right);
		}

	}

	public static void main(String[] args) {

		Example07 exam = new Example07();

		int[] preorder = { 10, 6, 4, 8, 14, 12, 16 };
		int[] inorder = { 4, 6, 8, 10, 12, 14, 16 };
		TreeNode root = exam.reConstructBinaryTree(preorder, inorder);
		System.out.print("中序遍历：");
		exam.printTree(root);
		System.out.println("[4 6 8 10 12 14 16 ]");
	}
}
