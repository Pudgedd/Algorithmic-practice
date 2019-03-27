package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.TreeNode;

/**
 * @description:请实现两个函数，分别用来序列化和反序列化二叉树
 * @author: kendall
 * @since: 2019/3/27
 */
public class SerializeAndDeserialize {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2;n1.right=n3;n2.left=n4;n2.right=n5;n3.left=n6;n3.right=n7;
        String serialize = Serialize(n1);
        System.out.println(serialize);
        TreeNode deserialize = Deserialize(serialize);
        System.out.println(Serialize(deserialize));
    }

    /**
     * 思路：选用前序、中序、后序任意一种，但是必须和反序列化方式一样。每个节点的值后加一个结尾符号"!"，用以区分数字,若为null则为"#！"。
     *
     * @param root
     * @return
     */
    private static String Serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private static void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#!");
            return;
        }
        sb.append(root.val + "!");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    /**
     * 反序列化的方式必须和序列化一样，将str按照"！"分割成数组
     *
     * @param str
     * @return
     */
    private static TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        String[] values = str.split("!");
        return deserializeByPreOrder(values);
    }

    private static int index = -1;
    private static TreeNode deserializeByPreOrder(String[] values) {
        index++;
        //不用判断index是否越界，因为最后肯定是两个"#"，直接return
        if (index >= values.length || values[index].equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(values[index]));
        node.left = deserializeByPreOrder(values);
        node.right = deserializeByPreOrder(values);
        return node;
    }
}
