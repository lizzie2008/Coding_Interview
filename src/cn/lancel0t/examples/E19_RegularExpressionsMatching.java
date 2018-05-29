/**
 * 
 * 【剑指Offer】面试题19 ：正则表达式匹配
 * 【  题目描述 】请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E19_RegularExpressionsMatching {

	// 正则表达式匹配
	public boolean match(char[] str, char[] pattern) {
		if (str == null || pattern == null)
			return false;

		return matchCore(str, pattern, 0, 0);
	}

	// 匹配递归算法
	private boolean matchCore(char[] str, char[] pattern, int s, int p) {

		// 匹配串和模式串都到达尾，说明成功匹配
		if (s >= str.length && p >= pattern.length) {
			return true;
		}

		// 只有模式串到达结尾，说明匹配失败
		if (s != str.length && p >= pattern.length) {
			return false;
		}

		// 模式串未结束，匹配串有可能结束有可能未结束

		// p位置的下一个字符中为*号
		if (p + 1 < pattern.length && pattern[p + 1] == '*') {

			// 匹配串已经结束
			if (s >= str.length) {
				return matchCore(str, pattern, s, p + 2);
			}
			// 匹配串还没有结束
			else {
				if (pattern[p] == str[s] || pattern[p] == '.') {
					return
					// 匹配串向后移动一个位置，模式串向后移动两个位置
					matchCore(str, pattern, s + 1, p + 2)
							// 匹配串向后移动一个位置，模式串不移动
							|| matchCore(str, pattern, s + 1, p)
							// 匹配串不移动，模式串向后移动两个位置
							|| matchCore(str, pattern, s, p + 2);
				} else {
					return matchCore(str, pattern, s, p + 2);
				}
			}
		}

		// 匹配串已经结束
		if (s >= str.length) {
			return false;
		}
		// 匹配串还没有结束
		else {
			if (str[s] == pattern[p] || pattern[p] == '.') {
				return matchCore(str, pattern, s + 1, p + 1);
			}
		}

		return false;
	}

	// ====================测试代码====================
	private void test(String testName, String str, String pattern, boolean expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("匹配结果：Result:%b \t Expect:%b\n\n", match(str.toCharArray(), pattern.toCharArray()),
					expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {

		E19_RegularExpressionsMatching exam = new E19_RegularExpressionsMatching();

		exam.test("test1", "", "", true);
		exam.test("test2", "", ".*", true);
		exam.test("test3", "", ".", false);
		exam.test("test4", "", "c*", true);
		exam.test("test5", "a", ".*", true);
		exam.test("test6", "a", "a.", false);
		exam.test("test7", "a", "", false);
		exam.test("test8", "a", ".", true);
		exam.test("test9", "a", "ab*", true);
		exam.test("test10", "a", "ab*a", false);
		exam.test("test11", "aa", "aa", true);
		exam.test("test12", "aa", "a*", true);
		exam.test("test13", "aa", ".*", true);
		exam.test("test14", "aa", ".", false);
		exam.test("test15", "ab", ".*", true);
		exam.test("test16", "ab", ".*", true);
		exam.test("test17", "aaa", "aa*", true);
		exam.test("test18", "aaa", "aa.a", false);
		exam.test("test19", "aaa", "a.a", true);
		exam.test("test20", "aaa", ".a", false);
		exam.test("test21", "aaa", "a*a", true);
		exam.test("test22", "aaa", "ab*a", false);
		exam.test("test23", "aaa", "ab*ac*a", true);
		exam.test("test24", "aaa", "ab*a*c*a", true);
		exam.test("test25", "aaa", ".*", true);
		exam.test("test26", "aab", "c*a*b", true);
		exam.test("test27", "aaca", "ab*a*c*a", true);
		exam.test("test28", "aaba", "ab*a*c*a", false);
		exam.test("test29", "bbbba", ".*a*a", true);
		exam.test("test30", "bcbbabab", ".*a*a", false);

	}
}
