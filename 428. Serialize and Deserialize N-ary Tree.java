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
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preorderSerialize(root, sb);
        return sb.toString();
    }
    
    private void preorderSerialize(Node root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val + "," + root.children.size() + ",");
        for (Node node : root.children) {
            preorderSerialize(node, sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] dataArr = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(dataArr));
        return preorderDeserialize(queue);
    }
    
    private Node preorderDeserialize(Queue<String> queue) {
        Node root = new Node(Integer.parseInt(queue.poll()), new ArrayList<>());
        int childrenSize = Integer.parseInt(queue.poll());
        
        for (int i = 0; i < childrenSize; i++) {
            root.children.add(preorderDeserialize(queue));
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
