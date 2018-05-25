/**
 * 
 * 【剑指Offer】面试题8 ：二叉树的下一个结点
 * 【  题目描述 】给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example08 {

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

	public void assemble(TreeLinkNode node, TreeLinkNode left, TreeLinkNode right, TreeLinkNode parent) {
		node.left = left;
		node.right = right;
		node.next = parent;
	}

	public static void main(String[] args) {

		Example08 exam = new Example08();

		TreeLinkNode n1 = exam.new TreeLinkNode(1);
		TreeLinkNode n2 = exam.new TreeLinkNode(2);
		TreeLinkNode n3 = exam.new TreeLinkNode(3);
		TreeLinkNode n4 = exam.new TreeLinkNode(4);
		TreeLinkNode n5 = exam.new TreeLinkNode(5);
		TreeLinkNode n6 = exam.new TreeLinkNode(6);
		TreeLinkNode n7 = exam.new TreeLinkNode(7);
		TreeLinkNode n8 = exam.new TreeLinkNode(8);
		TreeLinkNode n9 = exam.new TreeLinkNode(9);
		TreeLinkNode n10 = exam.new TreeLinkNode(10);
		TreeLinkNode n11 = exam.new TreeLinkNode(11);
		TreeLinkNode n12 = exam.new TreeLinkNode(12);
		TreeLinkNode n13 = exam.new TreeLinkNode(13);
		TreeLinkNode n14 = exam.new TreeLinkNode(14);
		TreeLinkNode n15 = exam.new TreeLinkNode(15);

		exam.assemble(n1, n2, n3, null);
		exam.assemble(n2, n4, n5, n1);
		exam.assemble(n3, n6, n7, n1);
		exam.assemble(n4, n8, n9, n2);
		exam.assemble(n5, n10, n11, n2);
		exam.assemble(n6, n12, n13, n3);
		exam.assemble(n7, n14, n15, n3);
		exam.assemble(n8, null, null, n4);
		exam.assemble(n9, null, null, n4);
		exam.assemble(n10, null, null, n5);
		exam.assemble(n11, null, null, n5);
		exam.assemble(n12, null, null, n6);
		exam.assemble(n13, null, null, n6);
		exam.assemble(n14, null, null, n7);
		exam.assemble(n15, null, null, n7);

		System.out.println(exam.GetNext(n1).val);
		System.out.println(exam.GetNext(n2).val);
		System.out.println(exam.GetNext(n3).val);
		System.out.println(exam.GetNext(n4).val);
		System.out.println(exam.GetNext(n5).val);
		System.out.println(exam.GetNext(n6).val);
		System.out.println(exam.GetNext(n7).val);
		System.out.println(exam.GetNext(n8).val);
		System.out.println(exam.GetNext(n9).val);
		System.out.println(exam.GetNext(n10).val);
		System.out.println(exam.GetNext(n11).val);
		System.out.println(exam.GetNext(n12).val);
		System.out.println(exam.GetNext(n13).val);
		System.out.println(exam.GetNext(n14).val);
		//System.out.println(exam.GetNext(n15).val);
	}
}
