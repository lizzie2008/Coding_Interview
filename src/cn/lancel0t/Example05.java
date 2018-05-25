/**
 * 
 * 【剑指Offer】面试题5 ： 替换空格
 * 【  题目描述 】请实现一个函数，把字符串中的每个空格替换成"%20"，
 * 例如“We are happy.”，则输出“We%20are%20happy.”。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example05 {

	// 利用Java类库
	public String replaceSpace(StringBuffer str) {
		// 找到空格出现的地方
		while (str.indexOf(" ") != -1) {
			int indexSpace = str.indexOf(" ");
			str.replace(indexSpace, indexSpace + 1, "%20");
		}

		return str.toString();
	}

	/*
	 * 时间复杂度O(n)算法
	 * length为字符数组的总容量
	 * 异常返回-1，否则返回替换后字符数组长度
	 */
	public int replaceSpace(char[] str, int length) {

		// 有效性检查
		if (str == null || length <= 0) {
			return -1;
		}

		// 统计字符数组中的空白字符数
		int originalLength = 0;
		int numberOfBlank = 0;

		for (int i = 0; str[i] != '\0';) {
			originalLength++;
			if (str[i++] == ' ')
				numberOfBlank++;
		}
		// 计算替换空格后的长度
		int newLength = originalLength + 2 * numberOfBlank;
		if (newLength > length)
			return -1;

		int index1st = originalLength;
		int index2nd = newLength;

		// 替换空格
		while (index1st >= 0 && index2nd > index1st) {
			if (str[index1st] == ' ') {
				str[index2nd--] = '0';
				str[index2nd--] = '2';
				str[index2nd--] = '%';
			} else {
				str[index2nd--] = str[index1st];
			}
			index1st--;
		}
		return newLength;
	}

	public static void main(String[] args) {

		Example05 exam = new Example05();
		// 测试1
		System.out.println("====测试1：" + exam.replaceSpace(new StringBuffer("we are happy.")));

		// 测试2
		char[] str = new char[50];
		str[0] = 'w';
		str[1] = 'e';
		str[2] = ' ';
		str[3] = 'a';
		str[4] = 'r';
		str[5] = 'e';
		str[6] = ' ';
		str[7] = 'h';
		str[8] = 'a';
		str[9] = 'p';
		str[10] = 'p';
		str[11] = 'y';
		str[12] = '.';
		int len = exam.replaceSpace(str, 50);
		System.out.println("====测试2：" + new String(str).substring(0, len));
	}

}
