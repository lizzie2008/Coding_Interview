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
package cn.lancel0t;

public class Example19 {

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

	private void test(String str, String pattern, boolean expect) {
		System.out.printf("匹配结果：%b [%b]\n", match(str.toCharArray(), pattern.toCharArray()), expect);
	}

	public static void main(String[] args) {

		Example19 exam = new Example19();

		exam.test("", "", true);
		exam.test("", ".*", true);
		exam.test("", ".", false);
		exam.test("", "c*", true);
		exam.test("a", ".*", true);
		exam.test("a", "a.", false);
		exam.test("a", "", false);
		exam.test("a", ".", true);
		exam.test("a", "ab*", true);
		exam.test("a", "ab*a", false);
		exam.test("aa", "aa", true);
		exam.test("aa", "a*", true);
		exam.test("aa", ".*", true);
		exam.test("aa", ".", false);
		exam.test("ab", ".*", true);
		exam.test("ab", ".*", true);
		exam.test("aaa", "aa*", true);
		exam.test("aaa", "aa.a", false);
		exam.test("aaa", "a.a", true);
		exam.test("aaa", ".a", false);
		exam.test("aaa", "a*a", true);
		exam.test("aaa", "ab*a", false);
		exam.test("aaa", "ab*ac*a", true);
		exam.test("aaa", "ab*a*c*a", true);
		exam.test("aaa", ".*", true);
		exam.test("aab", "c*a*b", true);
		exam.test("aaca", "ab*a*c*a", true);
		exam.test("aaba", "ab*a*c*a", false);
		exam.test("bbbba", ".*a*a", true);
		exam.test("bcbbabab", ".*a*a", false);

	}
}
