package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行
 * @author: kendall
 * @since: 2019/3/26
 */
public class PrintTreeNodeByLine {

    /**
     * 递归版
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        core(pRoot, 1, list);
        return list;
    }

    /**
     * 前序遍历的递归实现
     * @param root
     * @param depth
     * @param res
     */
    private void core(TreeNode root, int depth, ArrayList<ArrayList<Integer>> res) {
        if (root == null) {
            return;
        }
        //每到更深一层，先new一个list
        if (depth > res.size()) {
            res.add(new ArrayList<>());
        }
        //通过用depth去做index，可以保证每一层的二叉树节点肯定会被add进这一层的list，而且先序遍历保证了从左到右的顺序
        res.get(depth -1).add(root.val);

        core(root.left, depth + 1, res);
        core(root.right, depth + 1, res);
    }

    /**
     * 非递归，用队列实现BFS
     * @param pRoot
     * @return
     */
    private static ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        TreeNode last = pRoot;
        TreeNode nlast = null;
        while (!queue.isEmpty()) {
            TreeNode pNode = queue.poll();
            list.add(pNode.val);

            if (pNode.left != null) {
                queue.offer(pNode.left);
                nlast = pNode.left;
            }

            if (pNode.right != null) {
                queue.offer(pNode.right);
                nlast = pNode.right;
            }

            if (pNode == last) {
                res.add(list);
                list = new ArrayList<>();
                last = nlast;
            }
        }
        return res;
    }
}
