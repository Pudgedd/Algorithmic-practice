package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 * @author: kendall
 * @since: 2019/3/26
 */
public class PrintTreeByZ {

    /**
     * 思路：就是分层遍历，只是在偶数层逆序。维护一个last和nlast，last为当层最后一个node，nlast为当前push到队列的最后一个node
     * 当cur为last的时候，本层打印结束，更新last=nlast
     * @param pRoot
     * @return
     */
    private static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        TreeNode last = pRoot;
        TreeNode nlast = null;
        boolean printRev = false;
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
                if (printRev) {
                    Collections.reverse(list);
                    printRev = false;
                } else {
                    printRev = true;
                }
                res.add(list);
                list = new ArrayList<>();
                last = nlast;
            }
        }
        return res;
    }
}
