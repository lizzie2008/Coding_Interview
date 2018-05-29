/**
 * 
 * 【剑指Offer】面试题11 : 旋转数组的最小数字
 * 【  题目描述 】把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。 
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E11_MinNumberInRotatedArray {

	/*
	 * 一般解法时间复杂度O(n)
	 * 这里考虑用二分查找法O(logn)
	 */
	public int minNumberInRotateArray(int[] array) {

		if (array == null || array.length <= 0)
			return 0;

		// 定义高低2个指针lo、hi，二分位置mid
		int lo = 0;
		int hi = array.length - 1;
		int mid = lo;

		while (array[lo] >= array[hi]) {
			if (hi - lo == 1) {
				mid = hi;
				break;
			}
			mid = (lo + hi) / 2;

			// 如果lo、hi、mid三处相等，只能顺序查找
			if (array[lo] == array[mid] && array[mid] == array[hi]) {
				int result = array[lo];
				for (int i = lo + 1; i <= hi; i++) {
					if (array[i] < result)
						result = array[i];
				}
				return result;
			}

			// 二分查找
			if (array[mid] >= array[lo])
				lo = mid;
			else if (array[mid] <= array[hi])
				hi = mid;
		}

		return array[mid];
	}

	// ====================测试代码====================
	private void test(String testName, int[] array, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("旋转数组的最小数字：Result:%d \t Expect:%d\n\n", minNumberInRotateArray(array), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {

		E11_MinNumberInRotatedArray exam = new E11_MinNumberInRotatedArray();

		int array1[] = { 3, 4, 5, 1, 2 };
		exam.test("test1", array1, 1);

		int array2[] = { 3, 4, 5, 1, 1, 2 };
		exam.test("test2", array2, 1);

		int array3[] = { 3, 4, 5, 1, 2, 2 };
		exam.test("test3", array3, 1);

		int array4[] = { 1, 0, 1, 1, 1 };
		exam.test("test4", array4, 0);

		int array5[] = { 1, 2, 3, 4, 5 };
		exam.test("test5", array5, 1);

		int array6[] = { 2 };
		exam.test("test6", array6, 2);

		exam.test("test7", null, 0);
	}
}
