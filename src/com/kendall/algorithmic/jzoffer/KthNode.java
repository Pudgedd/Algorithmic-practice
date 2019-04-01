package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.TreeNode;

import java.util.ArrayList;

/**
 * @description:给定一棵二叉搜索树，请找出其中的第k小的结点。 例如, (5, 3, 7, 2, 4, 6, 8)中，按结点数值大小顺序第三小结点的值为4。
 * @author: kendall
 * @since: 2019/4/1
 */
public class KthNode {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(7);
        TreeNode n4 = new TreeNode(2);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(8);
        n1.left = n2;n1.right=n3;n2.left=n4;n2.right=n5;n3.left=n6;n3.right=n7;

        System.out.println(kthNode(n1,3).val);
    }

    /**
     * 二叉查找树(Binary Search Tree),(又：二叉搜索树，二叉排序树)
     * 它或者是一棵空树，或者是具有下列性质的二叉树：
     * 1.若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
     * 2.若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
     * 3.它的左、右子树也分别为二叉排序树。
     *
     * @param pRoot
     * @param k
     * @return
     */
    private static TreeNode kthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k < 1) {
            return null;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        inOrder(pRoot, list);
        if (list.size() >= k && k - 1 >= 0) {

            return list.get(k - 1);
        }
        return null;
    }

    private static void inOrder(TreeNode pRoot, ArrayList<TreeNode> list) {
        if (pRoot == null) {
            return;
        }

        inOrder(pRoot.left,list);
        list.add(pRoot);
        inOrder(pRoot.right, list);
    }
}
