/**
 * 
 * 【剑指Offer】	面试题48 ：最长不含重复字符的子字符串
 * 【题目描述】	请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 假设字符串中只包含从'a'到'z'的字符。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E48_LongestSubstringWithoutDup {

	/*
	 * 最长不含重复字符的子字符串
	 * 思路：使用动态规划，记录当前字符之前的最长非重复子字符串长度f(i-1)，其中i为当前字符的位置。
	 * 每次遍历当前字符时，分两种情况：
	 * 1.若当前字符第一次出现，则最长非重复子字符串长度f(i) = f(i-1)+1。 
	 * 2.若当前字符不是第一次出现，则首先计算当前字符与它上次出现位置之间的距离d。
	 * 	a)若d大于f(i-1)，即说明前一个非重复子字符串中没有包含当前字符，则可以添加当前字符到前一个非重复子字符串中，
	 * 	     所以，f(i) = f(i-1)+1。
	 * 	b)若d小于或等于f(i-1)，即说明前一个非重复子字符串中已经包含当前字符，则不可以添加当前字符，所以，f(i) = d。
	 */
	public int longestSubstringWithoutDuplication(String str) {
		int curLength = 0;
		int maxLength = 0;

		int[] position = new int[26];
		// 初始化为-1，负数表示没出现过
		for (int i = 0; i < 26; i++)
			position[i] = -1;

		for (int i = 0; i < str.length(); i++) {
			int prevIndex = position[str.charAt(i) - 'a'];
			// 当前字符与它上次出现位置之间的距离
			int distance = i - prevIndex;
			// 当前字符第一次出现，或者前一个非重复子字符串中没有包含当前字符
			if (prevIndex < 0 || distance > curLength)
				curLength++;
			else {
				// 更新最长非重复子字符串的长度
				if (curLength > maxLength)
					maxLength = curLength;

				curLength = distance;
			}
			// 更新字符出现的位置
			position[str.charAt(i) - 'a'] = i;
		}

		if (curLength > maxLength)
			maxLength = curLength;

		return maxLength;
	}

	// ====================测试代码====================
	private void test(String testName, String str, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("最长不含重复字符的子字符串的长度:\nResult:%d\nExpect:%d\n", longestSubstringWithoutDuplication(str),
					expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		test("test1", "abcacfrar", 4);
	}

	private void test2() {
		test("test2", "acfrarabc", 4);
	}

	private void test3() {
		test("test3", "arabcacfr", 4);
	}

	private void test4() {
		test("test4", "aaaa", 1);
	}

	private void test5() {
		test("test5", "abcdefg", 7);
	}

	private void test6() {
		test("test6", "aaabbbccc", 2);
	}

	private void test7() {
		test("test7", "abcdcba", 4);
	}

	private void test8() {
		test("test8", "abcdaef", 6);
	}

	private void test9() {
		test("test9", "a", 1);
	}

	private void test10() {
		test("test10", "", 0);
	}

	public static void main(String[] args) {

		E48_LongestSubstringWithoutDup exam = new E48_LongestSubstringWithoutDup();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
		exam.test8();
		exam.test9();
		exam.test10();
	}
}
