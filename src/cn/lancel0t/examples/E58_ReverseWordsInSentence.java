/**
 * 
 * 【剑指Offer】	面试题58 ：翻转单词顺序
 * 【题目描述】	输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S，
 *  如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;
import java.util.Collections;

public class E58_ReverseWordsInSentence {

	/*
	 * 翻转单词顺序
	 * 思路：用java自身提供的方法很容易实现
	 */
	public String ReverseSentence(String str) {

		if (str == null || str.length() <= 0 || str.trim().isEmpty())
			return str;

		ArrayList<String> list = new ArrayList<String>();
		for (String item : str.split(" ")) {
			list.add(item);
		}
		Collections.reverse(list);

		String.join(" ", list);

		return String.join(" ", list);
	}

	// ====================测试代码====================
	private void test(String testName, String str, String expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("翻转单词%s的顺序:\nResult:%s\nExpect:%s\n", str, ReverseSentence(str), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// 功能测试，句子中有多个单词
	private void test1() {
		test("test1", "I am a student.", "student. a am I");
	}

	// 功能测试，句子中只有一个单词
	private void test2() {
		test("test2", "Wonderful", "Wonderful");
	}

	// 鲁棒性测试
	private void test3() {
		test("test3", null, null);
	}

	// 边界值测试，测试空字符串
	private void test4() {
		test("test4", "", "");
	}

	// 边界值测试，字符串中只有空格
	private void test5() {
		test("test5", "   ", "   ");
	}

	public static void main(String[] args) {

		E58_ReverseWordsInSentence exam = new E58_ReverseWordsInSentence();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
	}
}
