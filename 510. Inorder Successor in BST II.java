/*
Given a node in a binary search tree, return the in-order successor of that node in the BST. If that node has no in-order successor, return null.

The successor of a node is the node with the smallest key greater than node.val.

You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node. Below is the definition for Node:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

// if node.right is not null, find the smallest node in node.right subtree
// if node.right is null, find the lowest parent that has larger value
class Solution {
    public Node inorderSuccessor(Node node) {
        if (node.right != null) {
            return smallestNode(node.right);
        }
        return firstLargerParent(node);
    }
    
    private Node smallestNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    private Node firstLargerParent(Node node) {
        int val = node.val;
        while (node.parent != null) {
            node = node.parent;
            if (node.val > val) {
                return node;
            }
        }
        return null;
    }
}

// Follow up: Could you solve it without looking up any of the node's values?
// Just change the firstLargerParent()
// if node.right is not null, find the smallest node in node.right subtree
// if node.right is null, find the lowest parent that has larger value
class Solution {
    public Node inorderSuccessor(Node node) {
        if (node.right != null) {
            return smallestNode(node.right);
        }
        return firstLargerParent(node);
    }
    
    private Node smallestNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    private Node firstLargerParent(Node node) {
        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        return node.parent;
    }
}
