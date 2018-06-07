/**
 * 
 * 【剑指Offer】	面试题43 ：从1到n整数中1出现的次数
 * 【题目描述】	输入一个整数n，求从1到n这n个整数的十进制表示中1出现的次数。
 * 例如输入12，从1到12这些整数中包含1 的数字有1，10，11和12，1一共出现了5次。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E43_NumberOf1 {

	/*
	 * 从1到n整数中1出现的次数
	 * 思路1：最直观的方法，统计每个整数1出现的次数：如果大于10，每次求10的余判断个位，就能统计1的个数了。
	 * 思路2：（推荐）效率更高的方法，分析如下规律，（个位1出现次数）+（十位1出现次数）+（百位1出现次数）
	 * 534	=>	（53*1+1）+（5*10+10）+（0*100+100）= 214
	 * 530 	=>	（53*1）+（5*10+10）+（0*100+100） = 213
	 * 504 	=>	（50*1+1）+（5*10）+（0*100+100） = 201
	 * 514 	=>	（51*1+1）+（5*10+4+1）+（0*100+100） = 207
	 * 10 	=> 	（1*1）+（0*10+0+1） = 2
	 * 			round	weight	former		
	 * n	=	5		3		4				base=10
	 * 总结规律：
	 * 1.若weight为0，则1出现次数为round*base
	 * 2.若weight为1，则1出现次数为round*base+former+1
	 * 3.若weight大于1，则1出现次数为rount*base+base
	 */
	public int NumberOf1Between1AndN_Solution(int n) {
		if (n < 1)
			return 0;
		int count = 0;
		int base = 1;
		int round = n;
		while (round > 0) {
			int weight = round % 10;
			round /= 10;
			count += round * base;
			if (weight == 1)
				count += (n % base) + 1;
			else if (weight > 1)
				count += base;
			base *= 10;
		}
		return count;
	}

	// ====================测试代码====================
	private void test(String testName, int n, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("整数从1到%d出现的次数:\nResult:%d\nExpect:%d\n", n,
					NumberOf1Between1AndN_Solution(n), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		E43_NumberOf1 exam = new E43_NumberOf1();

		exam.test("test1", 1, 1);
		exam.test("test2", 5, 1);
		exam.test("test3", 10, 2);
		exam.test("test4", 55, 16);
		exam.test("test5", 99, 20);
		exam.test("test6", 10000, 4001);
		exam.test("test7", 21345, 18821);
		exam.test("test8", 0, 0);
	}
}
