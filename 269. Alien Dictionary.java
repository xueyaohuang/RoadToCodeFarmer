// BFS
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, Set<Character>> charAfter = new HashMap<>();
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
            }
        }
        
        for (int i = 0; i + 1 < words.length; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    if (!charAfter.containsKey(c1)) {
                        charAfter.put(c1, new HashSet<Character>());
                    }
                    if (!charAfter.get(c1).contains(c2)) {
                        charAfter.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }  
            } 
        }
        
        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            if (charAfter.containsKey(c)) {
                for (char chAfterC : charAfter.get(c)) {
                    inDegree.put(chAfterC, inDegree.get(chAfterC) - 1);
                    if (inDegree.get(chAfterC) == 0) {
                        queue.offer(chAfterC);
                    }
                }
            }
        }
        
        return sb.length() == inDegree.size() ? sb.toString() : "";   
    }
}

// DFS

private final int N = 26;
public String alienOrder(String[] words) {
    boolean[][] adj = new boolean[N][N];
    int[] visited = new int[N];
    buildGraph(words, adj, visited);

    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < N; i++) {
        if(visited[i] == 0) {                 // unvisited
            if(!dfs(adj, visited, sb, i)) return "";
        }
    }
    return sb.reverse().toString();
}

public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
    visited[i] = 1;                            // 1 = visiting
    for(int j = 0; j < N; j++) {
        if(adj[i][j]) {                        // connected
            if(visited[j] == 1) return false;  // 1 => 1, cycle   
            if(visited[j] == 0) {              // 0 = unvisited
                if(!dfs(adj, visited, sb, j)) return false;
            }
        }
    }
    visited[i] = 2;                           // 2 = visited
    sb.append((char) (i + 'a'));
    return true;
}

public void buildGraph(String[] words, boolean[][] adj, int[] visited) {
    Arrays.fill(visited, -1);                 // -1 = not even existed
    for(int i = 0; i < words.length; i++) {
        for(char c : words[i].toCharArray()) visited[c - 'a'] = 0;
        if(i > 0) {
            String w1 = words[i - 1], w2 = words[i];
            int len = Math.min(w1.length(), w2.length());
            for(int j = 0; j < len; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if(c1 != c2) {
                    adj[c1 - 'a'][c2 - 'a'] = true;
                    break;
                }
            }
        }
    }
}
