// 和133，138几乎一样
// one pass
class Solution {
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> map = new HashMap<>();
        return copyNodes(root, map);
    }
    
    private NodeCopy copyNodes(Node root, Map<Node, NodeCopy> map) {
        if (map.containsKey(root)) {
            return map.get(root);
        }
        if (root == null) {
            return null;
        }
        NodeCopy copy = new NodeCopy(root.val);
        map.put(root, copy);
        copy.left = copyNodes(root.left, map);
        copy.right = copyNodes(root.right, map);
        copy.random = copyNodes(root.random, map);
        return copy;
    }
}

// two pass
class Solution {
    public NodeCopy copyRandomBinaryTree(Node root) {
        if (root == null) {
            return null;
        }
        Map<Node, NodeCopy> map = new HashMap<>();
        copyNodes(root, map);
        connectNodes(root, map);
        return map.get(root);
    }
    
    private void copyNodes(Node root, Map<Node, NodeCopy> map) {
        if (root == null) {
            return;
        }
        map.put(root, new NodeCopy(root.val));
        copyNodes(root.left, map);
        copyNodes(root.right, map);
    }
    
    private void connectNodes(Node root, Map<Node, NodeCopy> map) {
        if (root == null) {
            return;
        }
        map.get(root).random = map.get(root.random);
        map.get(root).left = map.get(root.left);
        map.get(root).right = map.get(root.right);
        connectNodes(root.left, map);
        connectNodes(root.right, map);
    }
}
