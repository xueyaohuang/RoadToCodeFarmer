/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        postorderHelper(root, res);
        return res;
    }
    
    private void postorderHelper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Node node : root.children) {
            postorderHelper(node, res);
        }
        res.add(root.val);
    }
}

class Solution {
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (int i = 0; i < node.children.size(); i++) {
                stack.push(node.children.get(i));
            }
        }
        Collections.reverse(res);
        return res;
    }
}
