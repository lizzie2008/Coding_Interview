/**
 * 
 * 【剑指Offer】	面试题41 ：数据流中的中位数
 * 【题目描述】	如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E41_StreamMedian {

	private PriorityQueue<Integer> maxHeap, minHeap;

	public E41_StreamMedian() {
		// 最大堆，保存中位数左侧元素
		maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		// 最小堆，保存中位数右侧元素
		minHeap = new PriorityQueue<Integer>();
	}

	/*
	 * 数据流中的中位数
	 * 思路：维持两个heap,一个是最小堆，一个是最大堆。
	 * 1.要保持两个堆的数目之差不超过1：优先插入最大堆，如果最大堆数目大于最大堆数目，插入最小堆；
	 * 2.如果当前需要往最小堆里插：判断待插元素如果比最大堆里的最大值还小，应该将该元素和最大堆中的最大值交换；
	 * 3.如果当前需要往最大堆里插：判断待插元素如果比最小堆里的最大值还大，应该将该元素和最小堆中的最小值交换；
	 */
	public void Insert(Integer num) {

		// 需要往最小堆里插
		if (maxHeap.size() != minHeap.size()) {
			// 待插的元素比最大堆里最大值要小
			if (maxHeap.size() > 0 && num < maxHeap.peek()) {
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(num);
			} else {
				minHeap.offer(num);
			}
		}
		// 需要往最大堆里插
		else {
			// 待插的元素比最小堆里最小值要大
			if (minHeap.size() > 0 && num > minHeap.peek()) {
				maxHeap.offer(minHeap.poll());
				minHeap.offer(num);
			} else {
				maxHeap.offer(num);
			}
		}
	}

	/*
	 * 获取中位数
	 */
	public Double GetMedian() {
		if (maxHeap.isEmpty()) {
			return -1d;
		}
		if (maxHeap.size() == minHeap.size()) {
			return (double) (minHeap.peek() + maxHeap.peek()) / 2;
		} else {
			return (double) maxHeap.peek();
		}
	}

	// ====================测试代码====================
	private void test(String testName) {
		try {
			System.out.printf("=====%s=====\n", testName);

			StringBuilder sb = new StringBuilder();
			ArrayList<Integer> list = new ArrayList<>();
			addAndPrint(5, sb, list, 5.0);
			addAndPrint(2, sb, list, 3.5);
			addAndPrint(3, sb, list, 3.0);
			addAndPrint(4, sb, list, 3.5);
			addAndPrint(1, sb, list, 3.0);
			addAndPrint(6, sb, list, 3.5);
			addAndPrint(7, sb, list, 4.0);
			addAndPrint(0, sb, list, 3.5);
			addAndPrint(8, sb, list, 4.0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void addAndPrint(int num, StringBuilder sb, ArrayList<Integer> list, double expect) {
		System.out.println();
		Insert(num);
		list.add(num);
		list.sort(null);
		sb.append(num + " ");
		System.out.println("当前数据流：" + sb);
		System.out.print("数据排序后：");
		for (Integer val : list) {
			System.out.print(val + " ");
		}
		System.out.println();
		System.out.printf("当前中位数：Result:%f \t Expect:%f\n", GetMedian(), expect);
	}

	public static void main(String[] args) {

		E41_StreamMedian exam = new E41_StreamMedian();

		exam.test("test");
	}
}
