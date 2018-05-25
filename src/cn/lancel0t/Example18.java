/**
 * 
 * 【剑指Offer】面试题18 ：在O(1)时间删除链表结点
 * 【  题目描述 】给定单向链表的头指针和一个结点指针，定义一个函数在O(1)时间删除该结点
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example18 {

	// 链表结点
	public class ListNode {
		int value;
		ListNode next;

		ListNode(int value) {
			this.value = value;
		}
	}

	// O(1)时间删除链表结点
	public ListNode deleteNode(ListNode head, ListNode toBeDeleted) {

		if (head == null || toBeDeleted == null)
			return head;

		// 如果删除的是最后一个元素
		if (toBeDeleted.next == null) {
			// 找到删除节点的前驱
			ListNode currNode = head;
			while (currNode.next != toBeDeleted) {
				currNode = currNode.next;
			}
			// 删除结点
			currNode.next = null;
		} else {
			// 拷贝待删除节点的下个节点的值给删除节点，并删除待删除节点的下个节点
			toBeDeleted.value = toBeDeleted.next.value;
			toBeDeleted.next = toBeDeleted.next.next;
		}

		return head;
	}

	public static void main(String[] args) {

		Example18 exam = new Example18();

		ListNode node1 = exam.new ListNode(1);
		ListNode node2 = exam.new ListNode(2);
		ListNode node3 = exam.new ListNode(3);
		ListNode node4 = exam.new ListNode(4);
		ListNode node5 = exam.new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		ListNode head = exam.deleteNode(node1, node3);
		ListNode currNode = head;
		while (currNode != null) {
			System.out.print(currNode.value + " ");
			currNode = currNode.next;
		}
		System.out.println("[1 2 4 5]");
	}
}
