/**
 * 
 * 【剑指Offer】面试题17 ：打印1 到最大的n 位数
 * 【  题目描述 】输入数字n,按顺序打印出从1到最大的n位十进制数，比如输入3,则打印出1,2,3一直到最大的3位数即999.
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example17 {

	// 数字排列法
	public void printOneToNthDigits(int n) {
		// 输入的数字不能为小于1
		if (n < 1) {
			throw new RuntimeException("The input number must larger than 0");
		}

		int[] numbers = new int[n];
		for (int i = 0; i < 10; ++i) {
			numbers[0] = i;
			printOneToNthDigitsCore(numbers, n, 1);
		}
	}

	// 我们把数字的每一位都从0到9排列一遍，就得到了所有的十进制数
	public void printOneToNthDigitsCore(int[] numbers, int n, int index) {
		// 构造数值完成，打印输出
		if (index == n) {
			print(numbers);
			return;
		}

		for (int i = 0; i < 10; i++) {
			numbers[index] = i;
			printOneToNthDigitsCore(numbers, n, index + 1);
		}
	}

	// 打印的时候，我们应该将前面的0补位去掉
	private void print(int[] numbers) {
		boolean isBeginning0 = true;

		for (int i = 0; i < numbers.length; ++i) {
			if (isBeginning0 && numbers[i] != 0)
				isBeginning0 = false;

			if (!isBeginning0) {
				System.out.print(numbers[i]);
			}
		}
		System.out.print(" ");
	}

	public static void main(String[] args) {

		Example17 exam = new Example17();
		System.out.printf("%d位整型数打印:\n", 2);
		exam.printOneToNthDigits(2);
	}
}
