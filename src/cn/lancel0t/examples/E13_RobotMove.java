/**
 * 
 * 【剑指Offer】面试题13 ：机器人的运动范围
 * 【  题目描述 】地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
 * 每一次只能向左，右，上，下四个方向移动一格， 但是不能进入行坐标和列坐标的数位之和大于k的格子。 
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E13_RobotMove {

	public int movingCount(int threshold, int rows, int cols) {
		// 参数校验
		if (threshold < 0 || rows < 1 || cols < 1) {
			return 0;
		}

		boolean[] visited = new boolean[rows * cols];
		return movingCountCore(threshold, rows, cols, 0, 0, visited);
	}

	// 整型值的各个位之和
	private int getDigitSum(int number) {
		int result = 0;
		while (number > 0) {
			result += (number % 10);
			number /= 10;
		}

		return result;
	}

	// 判断机器人能否进入坐标为(row, col)的方格
	private boolean check(int threshold, int rows, int cols, int row, int col, boolean[] visited) {
		return col >= 0 && col < cols && row >= 0 && row < rows && !visited[row * cols + col]
				&& (getDigitSum(col) + getDigitSum(row) <= threshold);
	}

	// 递归方法
	private int movingCountCore(int threshold, int rows, int cols, int row, int col, boolean[] visited) {

		int count = 0;

		if (check(threshold, rows, cols, row, col, visited)) {
			visited[row * cols + col] = true;
			count = 1 + movingCountCore(threshold, rows, cols, row - 1, col, visited)
					+ movingCountCore(threshold, rows, cols, row, col - 1, visited)
					+ movingCountCore(threshold, rows, cols, row + 1, col, visited)
					+ movingCountCore(threshold, rows, cols, row, col + 1, visited);
		}

		return count;
	}

	public static void main(String[] args) {

		E13_RobotMove exam = new E13_RobotMove();

		System.out.println(exam.movingCount(5, 10, 10) + "[21]");
		System.out.println(exam.movingCount(15, 20, 20) + "[359]");
		System.out.println(exam.movingCount(10, 1, 100) + "[29]");
		System.out.println(exam.movingCount(10, 1, 10) + "[10]");
		System.out.println(exam.movingCount(15, 100, 1) + "[79]");
		System.out.println(exam.movingCount(15, 10, 1) + "[10]");
		System.out.println(exam.movingCount(5, 10, 10) + "[21]");
		System.out.println(exam.movingCount(12, 1, 1) + "[1]");
		System.out.println(exam.movingCount(-10, 10, 10) + "[0]");
	}
}
