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
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        serializeTree(root, sb);
        sb.append(']');
        return sb.toString();
    }

    public void serializeTree(Node root, StringBuilder sb) {
        if (root == null) return;

        sb.append(root.val);
        sb.append('#');

        if (root.children != null && root.children.size() > 0) {
            sb.append('[');
            for (Node node : root.children) {
                serializeTree(node, sb);
            }
            sb.append(']');
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        char[] str = data.toCharArray();

        // serialized string for null == "[]"
        if (str.length <= 2)
            return null;

        List<Node> rst = new ArrayList<>();

        deserializeTree(str, 1, rst);

        return rst.get(0);
    }

    //对每一层[] 做recursion
    int deserializeTree(char[] str, int index, List<Node> children) {
        while (index < str.length && str[index] != ']') {
            int value = 0;
            while (str[index] != '#') {
                value = value * 10 + str[index++] - '0';
            }

            index++;
            Node cur = new Node(value, new ArrayList<>());
            children.add(cur);

            if (str[index] == '[') {
                index = deserializeTree(str, index + 1, cur.children);
            }
        }

        return index + 1;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
