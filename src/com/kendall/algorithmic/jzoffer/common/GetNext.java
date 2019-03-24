package com.kendall.algorithmic.jzoffer.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * @author: kendall
 * @since: 2019/3/23
 */
public class GetNext {
    public static void main(String[] args) {
        //{8,6,10,5,7,9,11},8
        TreeLinkNode n1 = new TreeLinkNode(8);
        TreeLinkNode n2 = new TreeLinkNode(6);
        TreeLinkNode n3 = new TreeLinkNode(10);
        TreeLinkNode n4 = new TreeLinkNode(5);
        TreeLinkNode n5 = new TreeLinkNode(7);
        TreeLinkNode n6 = new TreeLinkNode(9);
        TreeLinkNode n7 = new TreeLinkNode(11);
        n1.left = n2;n1.right=n3;n2.left=n4;n2.right=n5;n3.left=n6;n3.right=n7;
        n2.next=n1;n3.next=n1;n4.next=n2;n5.next=n2;n6.next=n3;n7.next=n3;
        TreeLinkNode node = getNext(n1);
        System.out.println(node.val);
    }

    /**
     * 思路1：找到root节点，进行中序遍历，保存遍历结果。对遍历结果进行遍历，若遇到pNode节点，则下一个节点就是要找对节点。
     * O(N)
     * @param pNode
     * @return
     */
    private static TreeLinkNode getNext1(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        TreeLinkNode root = pNode;
        while (root != null && root.next != null) {
            root = root.next;
        }

        Stack<TreeLinkNode> stack = new Stack<>();
        List<TreeLinkNode> list = new ArrayList<>();
        TreeLinkNode curNode =root;
        while (curNode != null || !stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = stack.pop();
                list.add(curNode);
                curNode = curNode.right;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == pNode && i+1 < list.size()) {
                return list.get(i + 1);
            }
        }
        return null;
    }

    /**
     * 思路2：中序遍历就是左中右
     * 1.当前节点有右子树，返回右子树最左节点。
     * 2.没有右子树，也就是说当前所在的子树遍历结束，如果有更上层的父节点，那么该子树就是父节点的左子树。
     * 下一个是该左子树的父节点。只要找到该子树的根节点即可找到父节点，而该左子树根节点肯定是根节点的左孩子。
     * 只要从当前节点一直向上遍历，找到第一次出现的是父节点的左孩子的节点，即为该左子树的根节点。下一个节点即是其父节点。
     * @param pNode
     * @return
     */
    private static TreeLinkNode getNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        //当前节点有右子树，返回右子树最左节点。
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }

        //没有右子树，则找到第一个节点，该节点是其父节点的左孩子
        while (pNode.next != null) {
            if (pNode.next.left == pNode) {
                return pNode.next;
            }
            pNode = pNode.next;
        }

        return null;
    }
}
