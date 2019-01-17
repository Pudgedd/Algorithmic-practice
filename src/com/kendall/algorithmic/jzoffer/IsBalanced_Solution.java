package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.TreeNode;

/**
 * @description: 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * @author: kendall
 * @since: 2019/1/17
 */
public class IsBalanced_Solution {

    /**
     * 思路：从子树开始遍历，若子树部位平衡二叉树，则直接返回，这样整棵树只遍历一次。
     * 其实就是求左右树的高度，然后判断是否高度差在1以内。
     * @param root
     * @return
     */
    private static boolean isBalanced1(TreeNode root) {
        boolean[] res = new boolean[1];
        res[0] = true;

        getHight(root, res);

        return res[0];
    }

    private static int getHight(TreeNode root, boolean[] res) {
        if (root == null) {
            return 0;
        }
        int lHight = getHight(root.left, res);
        //提前判断，当子树不为平衡树时直接返回。去掉其实也可以实现功能，不过这样做可以减少不必要的遍历。
        if (!res[0]) {
            return lHight;
        }

        int rHight = getHight(root.right, res);
        if (!res[0]) {
            return rHight;
        }

        if (Math.abs(lHight - rHight) > 1) {
            res[0] = false;
        }
        return Math.max(lHight, rHight) + 1;
    }

    /**
     * 思路：平衡二叉树是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
     * 因此判断一棵树是否是平衡二叉树，只需要递归求出左右两个子树的高度差不超过1就行了。
     *
     * 这个做法有个问题，就是判断上层节点时会重复遍历节点。
     *
     * @param root
     * @return
     */
    private static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftDepth = getDepthOfTree(root.left);
        int rightDepth = getDepthOfTree(root.right);

        if (Math.abs(leftDepth - rightDepth) >= 1) {
            return false;
        }

        boolean leftRes = isBalanced(root.left);
        boolean rightRes = isBalanced(root.right);

        return leftRes && rightRes;
    }

    private static int getDepthOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepthOfTree(root.left), getDepthOfTree(root.right))
    }
}