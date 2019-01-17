package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:输入一棵二叉树，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * @author: kendall
 * @since: 2019/1/15
 */
public class TreeDepth {

    /**
     * 思路：二叉树的遍历，同时记录最长路径的节点数
     * @return
     */
    private static int preOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = preOrder(root.left);
        int rightDepth = preOrder(root.right);

        return Math.max(leftDepth + 1, rightDepth + 1);
    }

    /**
     * 思路：非递归，其实就是分层打印二叉树的题，只是分层遍历的时候，记录层数
     * @return
     */
    private static int preOrder1(TreeNode root) {
        if (root == null) {
            return 0;
        }


        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode popNode = root;
        TreeNode last = root;
        TreeNode nLast = root;
        queue.offer(root);
        int depth = 0;
        while (queue.size() != 0) {
            popNode = queue.poll();
            if (popNode.left != null) {
                queue.offer(popNode.left);
                nLast = popNode.left;
            }
            if (popNode.right != null) {
                queue.offer(popNode.right);
                nLast = popNode.right;
            }
            if (popNode == last) {
                last = nLast;
                depth++;
            }
        }
        return depth;
    }

}
