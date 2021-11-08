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
