class WordFilter {
    
    private TrieNode root;

    public WordFilter(String[] words) {
        root = new TrieNode();
        // 如果有新的更大的weight，会更新
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            int len = s.length();
            for (int j = 0; j <= len; j++) {
                String ist = s.substring(j, len) + '{' + s;
                insert(ist, i);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        return startsWith(suffix + "{" + prefix);
    }
    
    
    public void insert(String s, int weight) {
        if (s == null || s.length() == 0) {
            return;
        }
        TrieNode cur = root;
        
        for (char c : s.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
            cur.weight = weight;
        }
    }

    public int startsWith(String prefix) {
        TrieNode cur = root;
        
        for (char c : prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return -1;
            }
            cur = cur.children[c - 'a'];
        }
        return cur.weight;
    }
    
    class TrieNode {
        TrieNode[] children;
        int weight;
        public TrieNode() {
            this.children = new TrieNode[27];
            this.weight = 0;
        }
    }
}
