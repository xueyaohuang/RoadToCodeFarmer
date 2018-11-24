class WordDictionary {
    
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        public TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }
    
    private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
           cur = cur.children[c - 'a']; 
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchHelper(word, root);
    }
    
    private boolean searchHelper(String word, TrieNode root) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                String rest = word.substring(i + 1);
                for (TrieNode newRoot : cur.children) {
                    if (newRoot != null && searchHelper(rest, newRoot)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (cur.children[c - 'a'] == null) {
                    return false;
                }
               cur = cur.children[c - 'a'];
            }
        }
        return cur.isWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

class WordDictionary {

    private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isLeaf = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode cur = root;
        return searchHelper(word, cur, 0);
    }
    
    // dfs
    private boolean searchHelper(String word, TrieNode cur, int idx) {
        if (idx == word.length()) {
            return cur.isLeaf;
        }
        char c = word.charAt(idx);
        // 如果是"."，就搜索所有children
        if (c == '.') {
            for (TrieNode trie : cur.children) {
                if (trie != null) {
                    if (searchHelper(word, trie, idx + 1)) {
                        return true;
                    }
                }
            }
        } else if (cur.children[c - 'a'] != null) {
            return searchHelper(word, cur.children[c - 'a'], idx + 1);
        }
        return false;
    }
    
    class TrieNode {
        boolean isLeaf;
        TrieNode[] children;
        public TrieNode() {
            this.isLeaf = false;
            this.children = new TrieNode[26];
        }
    }
}



/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
