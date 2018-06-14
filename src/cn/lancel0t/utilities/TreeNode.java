/**
 * 树节点及辅助方法
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.utilities;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	public int val = 0;
	public List<TreeNode> children = new ArrayList<>();

	public TreeNode(int val) {
		this.val = val;
	}

	public static void connectTreeNodes(TreeNode pParent, TreeNode pChild) {
		if (pParent != null) {
			pParent.children.add(pChild);
		}
	}

	@Override
	public String toString() {
		return val + "";
	}

}
