// 1. 把连接在一起的idxs组成group, 用dfs组成group，也可以用union find
// 2. 把同一group内的chars sort
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        List<Integer>[] adj = new ArrayList[n];
        for (int i= 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (List<Integer> pair : pairs) {
            adj[pair.get(0)].add(pair.get(1));
            adj[pair.get(1)].add(pair.get(0));
        }

        boolean[] visited = new boolean[n];
        List<List<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> group = new ArrayList<>();
                dfs(i, visited, group, adj);
                Collections.sort(group);
                groups.add(group);
            }
        }
        
        char[] res = new char[n];
        for (List<Integer> group : groups) {
            char[] chars = new char[group.size()];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = s.charAt(group.get(i));
            }
            Arrays.sort(chars);
            for (int i = 0; i < chars.length; i++) {
                res[group.get(i)] = chars[i];
            }
        }
        
        return String.valueOf(res);
    }
    
    private void dfs(int i, boolean[] visited, List<Integer> group, List<Integer>[] adj) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        group.add(i);
        for (int j : adj[i]) {
            dfs(j, visited, group, adj);
        }
    }
}

// use union find
/*
Problem abstract: Sort the characters within each connected group.

    For each the given pairs, create connected groups using union-find. Always mark the smaller index as parent;
    For each character in s, create mapping from root -> a list of candidate char. Since we want to use the smallest one every time we pick from them, use PriorityQueue.
    Finally, for each index, choose the first char in the associated pq and append into result.
*/
class Solution {
    private int[] parent;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() == 0) {
            return null;
        }
        parent = new int[s.length()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }
        
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            int root = find(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).offer(sChar[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sChar.length; i++) {
            sb.append(map.get(find(i)).poll());
        }
        return sb.toString();
    }
    private int find(int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
    private void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }
}

