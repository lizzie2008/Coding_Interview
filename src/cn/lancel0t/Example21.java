/**
 * 
 * 【剑指Offer】面试题21 ：调整数组顺序使奇数位于偶数前面
 * 【  题目描述 】输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example21 {

	// 保证原先顺序，冒泡排序思想，比较当前元素和下一个元素，如果当前元素是偶数且下一个元素为奇数，交换这两个数。
	// 每次冒泡都会使得最后一个偶数在正确的位置。
	public void reOrderArray(int[] array) {

		if (array == null || array.length == 0)
			return;

		int size = array.length;
		for (int i = 0; i < size - 1; ++i) {
			for (int j = 0; j < size - i - 1; ++j) {
				// 当前元素是偶数且下一个元素为奇数
				if ((array[j] & 0x1) == 0 && ((array[j + 1] & 0x1) == 1)) {
					int tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
				}
			}
		}
	}

	// 不保证原先顺序，前后2个指针向中间扫描，低指针找偶数，高指针找奇数，找到交换位置
	public void reOrderArrayI(int[] array) {

		if (array == null || array.length == 0)
			return;

		int lo = 0;
		int hi = array.length - 1;

		while (lo < hi) {

			// 向后移动lo，直到它指向偶数
			while (lo < hi && (array[lo] & 0x1) != 0)
				lo++;

			// 向后前移动hi，直到它指向奇数
			while (lo < hi && (array[hi] & 0x1) == 0)
				hi--;

			// 如果奇数比偶数靠后，交换位置
			if (lo < hi) {
				int tmp = array[lo];
				array[lo] = array[hi];
				array[hi] = tmp;
			}
		}
	}

	private void test(int[] array) {
		reOrderArray(array);
		if (array != null) {
			System.out.print("===调整后数组：");
			for (int i : array) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		Example21 exam = new Example21();

		exam.test(new int[] { 1, 2, 3, 4, 5, 6, 7 });
		exam.test(new int[] { 2, 4, 6, 1, 3, 5, 7 });
		exam.test(new int[] { 1, 3, 5, 7, 2, 4, 6 });
		exam.test(new int[] { 1 });
		exam.test(new int[] { 2 });
		exam.test(null);

	}
}
