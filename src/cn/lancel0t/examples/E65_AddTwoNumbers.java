/**
 * 
 * 【剑指Offer】	面试题65 ：不用加减乘除做加法
 * 【题目描述】	写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷四则运算符号。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E65_AddTwoNumbers {

	/*
	 * 不用加减乘除做加法
	 * 思路：模拟每位相加并考虑进位
	 * 第一步不考虑进位情况下，对每一位进行异或，就完成了不进位的相加；
	 * 第二步考虑进位，只有位数都是1的情况下，才可能进行进位，可以等同于将2个数进行位与，再左移1位；
	 * 将前两步结果相加，然后重复以上操作，不产生进位为止。
	 */
	public int Add(int num1, int num2) {
		int sum, carry;
		do {
			sum = num1 ^ num2;
			carry = (num1 & num2) << 1;

			num1 = sum;
			num2 = carry;
		} while (num2 != 0);

		return num1;
	}

	// ====================测试代码====================
	private void test(String testName, int num1, int num2, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("%d+%d结果:\nResult:%d\nExpect:%d\n", num1, num2, Add(num1, num2), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		E65_AddTwoNumbers exam = new E65_AddTwoNumbers();

		exam.test("test1", 1, 2, 3);
		exam.test("test2", 111, 899, 1010);
		exam.test("test3", -1, 2, 1);
		exam.test("test4", 1, -2, -1);
		exam.test("test5", 3, 0, 3);
		exam.test("test6", 0, -4, -4);
		exam.test("test7", -2, -8, -10);
	}
}
