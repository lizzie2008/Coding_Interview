/**
 * 
 * 【剑指Offer】面试题14 ：剪绳子
 * 【  题目描述 】给你一根长度为n的绳子，请把绳子剪成m段 (m和n都是整数，n>1并且m>1)每段绳子的长度记为k[0],k[1],...,k[m].
 * 请问k[0]*k[1]*...*k[m]可能的最大乘积是多少？
 * 例如，当绳子的长度为8时，我们把它剪成长度分别为2,3,3的三段，此时得到的最大乘积是18.
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example14 {

	/*
	 * 动态规划算法
	 * 容易推导 f(n)=max(f(i)*f(n-i))，其中0<i<n
	 * 如果用递归，效率不高，我们可以从简单往复杂依次计算：f(2),f(3),f(4).....f(n)
	 */
	public int maxAfterCutting(int length) {

		if (length < 2)
			return 0;
		if (length == 2)
			return 1;
		if (length == 3)
			return 2;

		// 当length <= 3时，不要对进行剪开，因为无论怎么剪，都小于本身
		// 而大于3的绳子，剪开可能比本身大，或至少等于本身，3是一个底线
		int[] f = new int[length + 1];
		f[0] = 0;
		f[1] = 1;
		f[2] = 2;
		f[3] = 3;

		int max = 0;
		for (int i = 4; i <= length; i++) {
			max = 0;
			// m*n与n*m一样，所以没必要都遍历
			for (int j = 1; j <= i / 2; j++) {
				int product = f[j] * f[i - j];
				// 取最大值
				if (max < product)
					max = product;
				f[i] = max;
			}
		}

		return f[length];
	}

	public static void main(String[] args) {

		Example14 exam = new Example14();

		System.out.println(exam.maxAfterCutting(1) + "[0]");
		System.out.println(exam.maxAfterCutting(2) + "[1]");
		System.out.println(exam.maxAfterCutting(3) + "[2]");
		System.out.println(exam.maxAfterCutting(4) + "[4]");
		System.out.println(exam.maxAfterCutting(5) + "[6]");
		System.out.println(exam.maxAfterCutting(6) + "[9]");
		System.out.println(exam.maxAfterCutting(7) + "[12]");
		System.out.println(exam.maxAfterCutting(9) + "[27]");
		System.out.println(exam.maxAfterCutting(10) + "[36]");
		System.out.println(exam.maxAfterCutting(50) + "[86093442]");

	}
}
