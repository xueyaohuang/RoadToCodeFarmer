// 四中解法都值得学习

// 最秀解法
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, String> map = new HashMap<>();
        for (String[] pair : pairs) {
            String s1 = findRoot(map, pair[0]);
            String s2 = findRoot(map, pair[1]);
            if (s1 == null || !s1.equals(s2)) {
                map.put(s1, s2);
            }
        }
        for (int i = 0; i < words1.length; i++) {
            String s1 = findRoot(map, words1[i]);          
            String s2 = findRoot(map, words2[i]);
            if (s1 == null || !s1.equals(s2)) {
                return false;
            }
        }
        return true;
    }
    private String findRoot(Map<String, String> map, String s) {
        String t = map.get(s);
        while (t != null) {
            s = t;
            t = map.get(s);
        }
        return s;
    }
}

// union find
class Solution {
    
    class UnionFind {
        
        int[] parent;
        int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int p) {
            if (p != parent[p]) {
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }
        
        public boolean isConnected(Integer a, Integer b) {
            
            if (a == null || b == null) {
                return false;
            }

            int rootA = find(a);
            int rootB = find(b);
            return rootA == rootB;
        }
        
        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }
            if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
            return true;
        }
        
    }
    
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        
        if (words1.length != words2.length) {
            return false;
        }
        
        int size = 0;
        Map<String, Integer> map = new HashMap<>();
        
        for (String[] pair : pairs) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], size);
                size++;
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], size);
                size++;
            }
        }
        
        UnionFind uf = new UnionFind(size);
        
        for (String[] pair : pairs) {
            uf.union(map.get(pair[0]), map.get(pair[1]));
        }
        
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            if (!uf.isConnected(map.get(words1[i]), map.get(words2[i]))) {
                return false;
            }
        }
        return true;
    }
}

// BFS slowest
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, Set<String>> map = new HashMap<>();
        for (String[] strs : pairs) {
            map.putIfAbsent(strs[0], new HashSet<String>());
            map.putIfAbsent(strs[1], new HashSet<String>());
            map.get(strs[0]).add(strs[1]);
            map.get(strs[1]).add(strs[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            } 
            if (!bfs(words1[i], words2[i], map, new HashSet<>())) {
                return false;
            } 
        }
        return true;
    }
    private boolean bfs(String s, String t, Map<String, Set<String>> map, Set<String> visited) {
        if (!map.containsKey(s)) {
            return false;
        }
        if (map.get(s).contains(t)) {
            return true;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);
        while (!queue.isEmpty()) {
            String str = queue.poll();
            for (String ss : map.get(str)) {
                if (!visited.contains(ss)) {
                    visited.add(ss);
                    if (ss.equals(t)) {
                        return true;
                    }
                    queue.add(ss);
                }
            }
        }
        return false;
    }
}

// DFS
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, Set<String>> map = new HashMap<>();
        for (String[] strs : pairs) {
            map.putIfAbsent(strs[0], new HashSet<String>());
            map.putIfAbsent(strs[1], new HashSet<String>());
            map.get(strs[0]).add(strs[1]);
            map.get(strs[1]).add(strs[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            } 
            if (!map.containsKey(words1[i])) {
                return false;
            }
            if (!dfs(words1[i], words2[i], map, new HashSet<>())) {
                return false;
            } 
        }
        return true;
    }
    private boolean dfs(String s, String t, Map<String, Set<String>> map, Set<String> visited) {
        if (map.get(s).contains(t)) {
            return true;
        }
        visited.add(s);
        for (String ss : map.get(s)) {
            if (!visited.contains(ss) && dfs(ss, t, map, visited)) {
                return true;
            }
        }
        return false;
    }
}
