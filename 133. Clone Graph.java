/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> map = new HashMap<>();
        dfs(node, map);
        return map.get(node);
    }
    
    private void dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) {
            return;
        }
        Node cloned = new Node(node.val);
        map.put(node, cloned);
        for (Node n : node.neighbors) {
            dfs(n, map);
            cloned.neighbors.add(map.get(n));
        }
    }
}

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }
    
    private Node dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node cloned = new Node(node.val);
        map.put(node, cloned);
        for (Node n : node.neighbors) {
            cloned.neighbors.add(dfs(n, map));
        }
        return cloned;
    }
}
