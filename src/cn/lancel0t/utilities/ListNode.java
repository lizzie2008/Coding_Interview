/**
 * 链表节点及辅助方法
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.utilities;

public class ListNode {

	public int val;
	public ListNode next = null;

	public ListNode(int val) {
		this.val = val;
	}

	public static void print(ListNode head) {
		if (head == null) {
			System.out.println("∅");
			return;
		}

		ListNode currNode = head;
		while (currNode != null) {
			System.out.print(currNode.val + " ");
			currNode = currNode.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {

	}

}
