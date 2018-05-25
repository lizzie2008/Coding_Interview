/**
 * 
 * 【剑指Offer】面试题12 ：矩阵中的路径
 * 【  题目描述 】请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 
 * 例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example12 {

	// 回溯搜索算法
	public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
		// 参数校验
		if (matrix == null || matrix.length != rows * cols || str == null || str.length < 1) {
			return false;
		}

		// 变量初始化
		boolean[] visited = new boolean[rows * cols];

		// 记录结果的数组，
		int pathLength = 0;
		// 以每一个点为起始进行搜索
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (hasPathCore(matrix, rows, cols, str, visited, i, j, pathLength)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, boolean[] visited, int row, int col,
			int pathLength) {

		if (pathLength == str.length)
			return true;

		boolean hasPath = false;

		// 判断位置是否合法
		if (row >= 0 && row < rows && col >= 0 && col < cols && matrix[row * cols + col] == str[pathLength]
				&& !visited[row * cols + col]) {

			visited[row * cols + col] = true;
			pathLength++;

			// 按左上右下进行回溯
			hasPath = hasPathCore(matrix, rows, cols, str, visited, row, col - 1, pathLength)
					|| hasPathCore(matrix, rows, cols, str, visited, row - 1, col, pathLength)
					|| hasPathCore(matrix, rows, cols, str, visited, row, col + 1, pathLength)
					|| hasPathCore(matrix, rows, cols, str, visited, row + 1, col, pathLength);

			if (!hasPath) {
				pathLength--;
				visited[row * cols + col] = false;
			}
		}

		return hasPath;
	}

	public static void main(String[] args) {

		Example12 exam = new Example12();

		/*
		 * 输入矩阵
		 * abce
		 * sfcs
		 * adee
		 */
		System.out.println(exam.hasPath("abcesfcsadee".toCharArray(), 3, 4, "bcced".toCharArray()) + "[true]");
		System.out.println(exam.hasPath("abcesfcsadee".toCharArray(), 3, 4, "abcb".toCharArray()) + "[false]");
	}
}
