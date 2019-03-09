/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        Node left = treeToDoublyList(root.left);
        Node right = treeToDoublyList(root.right);
        root.left = root;
        root.right = root;
        Node leftAndRoot = connectTwoDoublyList(left, root);
        return connectTwoDoublyList(leftAndRoot, right);
    }
    
    private Node connectTwoDoublyList(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        Node tail1 = head1.left;
        Node tail2 = head2.left;
        tail1.right = head2;
        head2.left = tail1;
        head1.left = tail2;
        tail2.right = head1;
        return head1;
    }
}
