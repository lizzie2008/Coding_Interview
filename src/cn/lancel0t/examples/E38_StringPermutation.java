/**
 * 
 * 【剑指Offer】	面试题38 ：字符串的排列
 * 【题目描述】	输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;
import java.util.Collections;

public class E38_StringPermutation {

	/*
	 * 字符串的排列
	 * 思路：首先将可能的字符串分成2个部分，第一字符位置和后面所有字符；
	 * 考虑第1个字符的所有可能，即将第一个字符和后面所有的字符交换；
	 * 固定第1个字符，递归处理求出后面字符的所有排列：固定第2个，第3个....
	 */
	public ArrayList<String> Permutation(String str) {

		if (str == null)
			return null;

		ArrayList<String> result = new ArrayList<String>();
		Permutation(str.toCharArray(), 0, result);
		Collections.sort(result);
		return result;
	}

	/*
	 * 排列的递归算法
	 * i-表示当前计算第i个元素之后的全排列
	 */
	private void Permutation(char[] str, int i, ArrayList<String> resultList) {
		if (i >= str.length)
			return;
		// 满足条件，添加到结果队列
		if (i == str.length - 1) {
			String val = String.valueOf(str);
			if (!resultList.contains(val))
				resultList.add(val);
		} else {
			for (int j = i; j < str.length; j++) {
				// 第i个元素可以取之后的任意元素
				char temp = str[j];
				str[j] = str[i];
				str[i] = temp;

				// 固定第i个元素，跟i+1后面的全排列组合
				Permutation(str, i + 1, resultList);

				// 还原数组
				temp = str[j];
				str[j] = str[i];
				str[i] = temp;
			}
		}
	}

	// ====================测试代码====================
	private void test(String testName, String str) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.println("字符串的排列:");
			for (String item : Permutation(str)) {
				System.out.print(item + " ");
			}
			System.out.println();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		E38_StringPermutation exam = new E38_StringPermutation();

		exam.test("test1", "");
		exam.test("test2", "a");
		exam.test("test3", "ab");
		exam.test("test4", "abc");
		exam.test("test4", "aa");
	}
}
