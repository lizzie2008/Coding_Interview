/**
 * 
 * 【剑指Offer】	面试题50 ：字符串中第一个只出现一次的字符
 * 【题目描述】	在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.HashMap;

public class E50_FirstNotRepeatingChar {

	/*
	 * 字符串中第一个只出现一次的字符
	 * 思路：利用哈希表，可以定义哈希表的键为字符，值为出现的次数。
	 * 扫描字符串，每扫一个字符，将对应项次数加1。
	 * 再次扫描字符串，获取当前字符对应的出现次数，第一次出现1的即所求。
	 */
	public int FirstNotRepeatingChar(String str) {

		if (str == null)
			return -1;

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char[] strChars = str.toCharArray();
		// 每扫一个字符，将对应项次数加1
		for (char item : strChars) {
			if (map.containsKey(item))
				map.put(item, map.get(item) + 1);
			else
				map.put(item, 1);
		}
		// 扫描第一次出现次数为1的元素位置
		for (int i = 0; i < str.length(); i++) {
			if (map.get(strChars[i]) == 1) {
				return i;
			}
		}

		return -1;
	}

	// ====================测试代码====================
	private void test(String testName, String str, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("字符串%s中第一个只出现一次的字符:\nResult:%d\nExpect:%d\n", str, FirstNotRepeatingChar(str),
					expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// 常规输入测试，存在只出现一次的字符
	private void test1() {
		test("test1", "google", 4);
	}

	// 常规输入测试，不存在只出现一次的字符
	private void test2() {
		test("test2", "aabccdbd", -1);
	}

	// 常规输入测试，所有字符都只出现一次
	private void test3() {
		test("test3", "abcdefg", 0);
	}

	// 鲁棒性测试，输入null
	private void test4() {
		test("test4", null, -1);
	}

	public static void main(String[] args) {

		E50_FirstNotRepeatingChar exam = new E50_FirstNotRepeatingChar();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
	}
}
