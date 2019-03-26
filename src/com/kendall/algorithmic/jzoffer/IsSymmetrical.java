package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * @author: kendall
 * @since: 2019/3/25
 */
public class IsSymmetrical {

    public static void main(String[] args) {
        //{8,6,6,5,7,7,5}
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(5);
        n1.left = n2;n1.right=n3;n2.left=n4;n2.right=n5;n3.left=n6;n3.right=n7;
        boolean res = isSymmetricalBFS(n1);
        System.out.println(res);
    }

    /**
     * 思路1：
     * 1、若节点为null，返回false
     * 2、若节点没有左右节点，返回true
     * 3、节点有左子树、右子树，且左子树的左子树等于右子树的右子树、左子树的右子树等于右子树的左子节点，返回true
     * @param pRoot
     * @return
     */
    private static boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }

        return isSymmetrical(pRoot.left, pRoot.right);
    }

    private static boolean isSymmetrical(TreeNode nodeL,TreeNode nodeR) {
        if (nodeL == null && nodeR == null) {
            return true;
        }
        if (nodeL != null && nodeR != null) {
            return nodeL.val == nodeR.val
                    && isSymmetrical(nodeL.left, nodeR.right)
                    && isSymmetrical(nodeL.right, nodeR.left);
        }
        return false;
    }

    private static boolean isSymmetricalBFS(TreeNode pRoot) {
        if(pRoot == null) return true;
        LinkedList<TreeNode> s = new LinkedList<>();
        s.offer(pRoot.left);
        s.offer(pRoot.right);
        while(!s.isEmpty()) {
            TreeNode right = s.poll();
            TreeNode left = s.poll();
            //注意：linkedlist的offer方法会把null当成正常节点加入(add)链表中，所以isEmpty方法会返回false
            if(left == null && right == null) continue;
            if(left == null || right == null) return false;
            if(left.val != right.val) return false;
            //成对插入
            s.offer(left.left);
            s.offer(right.right);
            s.offer(left.right);
            s.offer(right.left);
        }
        return true;
    }
}
