// BFS
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        Map<Character, Integer> inDegree = new HashMap<>();
        // 一个char，排在它后面的char有哪些
        Map<Character, Set<Character>> charAfter = new HashMap<>();
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
            }
        }
        
        outer:
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
                    continue outer; // 找到第一个不同后就break
                }  
            }
            if (word1.length() > word2.length()) {
                return "";
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
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        boolean[][] graph = new boolean[26][26];
        boolean[] hasChar =  new boolean[26];
        // 0: not visited
        // 1: visiting
        // 2: finished visiting
        int[] visited = new int[26];
        StringBuilder sb = new StringBuilder();
        if (!buildGraph(words, visited, graph, hasChar)) {
            return "";
        }
        for (int i = 0; i < 26; i++) {
            if (visited[i] == 0) {    // DFS, 必须检查没访问过才开始访问
                if (hasCycle(graph, i, visited, sb)) {
                    return "";
                }
            }
        }
        sb.reverse();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (hasChar[sb.charAt(i) - 'a']) {
                res.append(sb.charAt(i));
            }
        }
        return res.toString();
    }
    
    public boolean hasCycle(boolean[][] graph, int i, int[] visited, StringBuilder sb) {
        if (visited[i] == 2) {
            return false;
        }
        if (visited[i] == 1) {
            return true;
        }
        visited[i] = 1;
        for (int j = 0; j < 26; j++) {
            if (graph[i][j]) {
                if (hasCycle(graph, j, visited, sb)) {
                    return true;
                }
            }
        }
        visited[i] = 2;
        sb.append((char)(i + 'a'));
        return false;
    }
        
    public boolean buildGraph(String[] words, int[] visited, boolean[][] graph, boolean[] hasChar) {
        outer:
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                hasChar[c - 'a'] = true;
            }
            if (i > 0) {
                String word1 = words[i - 1];
                String word2 = words[i];
                int len = Math.min(word1.length(), word2.length());
                for (int j = 0; j < len; j++) {
                    char c1 = word1.charAt(j);
                    char c2 = word2.charAt(j);
                    if (c1 != c2) {
                        graph[c1 - 'a'][c2 - 'a'] = true;
                        continue outer;
                    }
                }
                if (word1.length() > word2.length()) {
                    return false;
                }
            }
        }
        return true;
    }
}

// this  solution doesn't work for some new test case
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        boolean[][] graph = new boolean[26][26];
        // 0: not exist
        // 1: not visited
        // 2: visiting
        // 3: finished visiting
        int[] visited = new int[26];
        StringBuilder sb = new StringBuilder();
        buildGraph(words, visited, graph);
        
        for (int i = 0; i < 26; i++) {
            if (visited[i] == 1) {    // DFS, 必须检查没访问过才开始访问
                if (hasCycle(graph, i, visited, sb)) {
                    return "";
                }
            }
        }
        return sb.reverse().toString();
    }
    
    public boolean hasCycle(boolean[][] graph, int i, int[] visited, StringBuilder sb) {
        if (visited[i] == 3) {
            return false;
        }

        visited[i] = 2;
        for (int j = 0; j < 26; j++) {
            if (graph[i][j]) {
                if (visited[j] == 2) {  // 不能少
                    return true;
                }
                if (visited[j] == 1) {
                    if (hasCycle(graph, j, visited, sb)) {
                        return true;
                    }
                }
            }
        }
        visited[i] = 3;
        sb.append((char)(i + 'a'));
        return false;
    }
        
    public void buildGraph(String[] words, int[] visited, boolean[][] graph) {
        for (int i = 0; i < words.length; i++) {
            
            for (char c : words[i].toCharArray()) {
                visited[c - 'a'] = 1;
            }
            
            if (i > 0) {
                String word1 = words[i - 1];
                String word2 = words[i];
                int len = Math.min(word1.length(), word2.length());
                for (int j = 0; j < len; j++) {
                    char c1 = word1.charAt(j);
                    char c2 = word2.charAt(j);
                    if (c1 != c2) {
                        graph[c1 - 'a'][c2 - 'a'] = true;
                        break;
                    }
                }
            }
            
        }
    }
}
