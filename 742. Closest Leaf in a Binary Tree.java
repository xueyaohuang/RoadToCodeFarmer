/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// best
class Solution {
    public int findClosestLeaf(TreeNode root, int k) {
        if (root == null) return -1;

        int [] res = new int[1];
        int [] dist = new int[1];
        dist[0] = Integer.MAX_VALUE;
        dist(root, k, -1, res, dist);

        return res[0];
    }

    private int dist(TreeNode root, int k, int level, int[] res, int [] dist) {
        if (root == null) return -1;

        if (root.left == null && root.right == null) {
            if (dist[0] > level && level != -1) {
                dist[0] = level;
                res[0] = root.val;
            }
            
            if (root.val == k) {
                dist[0] = 0;
                res[0] = k;
            }
        }

        int dis = -1;
        if (root.val == k) {
            dis = 1;
            level = 1;
        }

        int left = dist(root.left, k, level == - 1? -1 : level+1, res, dist);
        int right = dist(root.right, k, level == - 1? -1 : level+1, res, dist);

        if (left != -1) {
            // Find the node in left tree;
            dist(root.right, k, left + 1, res, dist);
            dis = left + 1;
        } else if (right != -1) {
            dist(root.left, k, right + 1, res, dist);
            dis = right + 1;
        }

        return dis;
    }
}


// lca
class Solution {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, Integer> map = new HashMap<>();
        getDistance(root, map, 0);
        TreeNode node = findNode(root, k);
        int res = Integer.MAX_VALUE, minDist = Integer.MAX_VALUE;
        
        for (TreeNode cur : map.keySet()) {
            if (cur.left == null && cur.right == null) {
                TreeNode lca = lowestCommonAncestor(root, node, cur);
                int curDist = map.get(node) + map.get(cur) - 2 * map.get(lca);
                
                if (curDist < minDist) {
                    minDist = curDist;
                    res = cur.val;
                }
            }
        }
        
        return res;
    }
    
    private void getDistance(TreeNode root, Map<TreeNode, Integer> map, int dist) {
        if (root == null) {
            return;
        }
        map.put(root, dist);
        getDistance(root.left, map, dist + 1);
        getDistance(root.right, map, dist + 1);
    }
    
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
    
    private TreeNode findNode(TreeNode root, int k) {
        if (root.val == k) {
            return root;
        }
        if (root.left != null) {
            TreeNode left = findNode(root.left, k);
            if (left != null) {
                return left;
            }
        }
        if (root.right != null) {
            TreeNode right = findNode(root.right, k);
            if (right != null) {
                return right;
            }
        }
        return null;
    }
    
    private TreeNode findNode2(TreeNode root, int k) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                if (p.val == k) {
                    return p;
                }
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
        return root;
    }
}

// 把树当成图run bfs，从一个node，它的adj list中最多有三个点，left，right和parent
class Solution {
    HashMap<TreeNode,TreeNode> hmap = new HashMap<>();
    int targetVal = 0;
    TreeNode target = null;
    public int findClosestLeaf(TreeNode root, int k) {
        if(root == null){
            return 0;
        }
        targetVal = k;
        dfs(root,null);
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); 
        HashSet<TreeNode> hset = new HashSet<TreeNode>();
        queue.add(target);
        hset.add(target);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode tmp = queue.poll();
                if(tmp.left == null && tmp.right == null){
                    return tmp.val;
                }
                if(tmp.left != null){
                    if(!hset.contains(tmp.left)){
                        queue.add(tmp.left);
                        hset.add(tmp.left);
                    }
                }
                if(tmp.right != null){
                    if(!hset.contains(tmp.right)){
                        queue.add(tmp.right);
                        hset.add(tmp.right);
                    }
                }
                TreeNode parent = hmap.get(tmp);
                if(parent != null){
                    if(!hset.contains(parent)){
                        queue.add(parent);
                        hset.add(parent);
                    }
                }
            }
        }
      
        return 0;
    }
    
    public void dfs(TreeNode root,TreeNode parent){
        if(root == null){
            return;
        }
        if(root.val == targetVal){
            target = root;
        }
        hmap.put(root,parent);
        dfs(root.left,root);
        dfs(root.right,root);
    }
}

// 把树转换成图的adj list形式用bfs
class Solution {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap();
        dfs(graph, root, null);

        Queue<TreeNode> queue = new LinkedList();
        Set<TreeNode> seen = new HashSet();

        for (TreeNode node: graph.keySet()) {
            if (node != null && node.val == k) {
                queue.add(node);
                seen.add(node);
            }
        }

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                if (graph.get(node).size() <= 1)
                    return node.val;
                for (TreeNode nei: graph.get(node)) {
                    if (!seen.contains(nei)) {
                        seen.add(nei);
                        queue.add(nei);
                    }
                }
            }
        }
        throw null;
    }

    public void dfs(Map<TreeNode, List<TreeNode>> graph, TreeNode node, TreeNode parent) {
        if (node != null) {
            if (!graph.containsKey(node)) graph.put(node, new LinkedList<TreeNode>());
            if (!graph.containsKey(parent)) graph.put(parent, new LinkedList<TreeNode>());
            graph.get(node).add(parent);
            graph.get(parent).add(node);
            dfs(graph, node.left, node);
            dfs(graph, node.right, node);
        }
    }
}
