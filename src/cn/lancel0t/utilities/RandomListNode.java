/**
 * 复杂链表节点及辅助方法
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.utilities;

public class RandomListNode {

	public int label;
	public RandomListNode next = null;
	public RandomListNode random = null;

	public RandomListNode(int label) {
		this.label = label;
	}

	public static void PrintList(RandomListNode pHead) {
		RandomListNode pNode = pHead;
		while (pNode != null) {
			System.out.printf("节点值为: %d.\n", pNode.label);

			if (pNode.random != null)
				System.out.printf("该节点指向另一个节点: %d.\n", pNode.random.label);
			else
				System.out.printf("该节点没有指向别的节点.\n");
			pNode = pNode.next;
		}
	}

	public static void connectListNodes(RandomListNode node, RandomListNode next, RandomListNode random) {
		if (node == null) {
			return;
		}
		node.next = next;
		node.random = random;

	}
}
