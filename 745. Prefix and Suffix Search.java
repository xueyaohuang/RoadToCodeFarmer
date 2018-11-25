// 1. 这种一次implement多个function的，要先搞清楚哪个function被call的次数多
// https://leetcode.com/problems/prefix-and-suffix-search/discuss/110044/Three-ways-to-solve-this-problem-in-Java
// 2. 这题可以用trie，也可以用hashmap

// 假设f被call的次数占大多数，假设面试官要我写trie，是sol1
// sol1:

/*
Time Complexity: 
WordFilter: O(NK^2)
f: O(K) 
where N is the number of words, K is the average length of a word.

Space Complexity: O(NK^2), the size of the trie.

*/

/* Take "apple" as an example, we will insert add "apple{apple", "pple{apple", "ple{apple", "le{apple", 
"e{apple", "{apple" into the Trie Tree.
If the query is: prefix = "app", suffix = "le", we can find it by querying our trie for
"le { app".
We use '{' because in ASCii Table, '{' is next to 'z', so we just need to create new TrieNode[27] instead of 26.
Also, compared with tradition Trie, we add the attribute weight in class TrieNode.
为什么把suffix放到prefix前面，因为这样可以减少最开始插入词的个数，比如apple。只需要变化suffix，prefix一直是apple。
如果prefix在前面，prefix还需要a,ap,app,appl,apple.*/

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

// trie的children可以用hashmap实现
class WordFilter {
    
    class TrieNode {
        int weight;
        Map<Character, TrieNode> children;
        public TrieNode() {
            this.weight = 0;
            this.children = new HashMap<>();
        }
    }
    
    private TrieNode root;

    public WordFilter(String[] words) {
        root = new TrieNode();
        int len = words.length;
        for (int i = 0; i < len; i++) {
            int size = words[i].length();
            for (int j = size; j >= 0; j--) {
                String toInsert = words[i].substring(j) + "{" + words[i];
                insert(toInsert, i);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        return startsWith(suffix + "{" + prefix);
    }
    
    private void insert(String word, int weight) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur.children.get(c).weight = weight;
            cur = cur.children.get(c);
        }
    }
    
    private int startsWith(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return -1;
            }
            cur = cur.children.get(c);
        }
        return cur.weight;
    }
}

// 假设f被call的次数占大多数，假设面试官没有要我写trie，是sol2
// sol2:
/*
Time:
WordFilter: Time = O(NL^2)
f: Time = O(1)
Space:
O(NL^2)
*/
class WordFilter {
    
    Map<String, Integer> map;

    public WordFilter(String[] words) {
        map = new HashMap<>();
        int len = words.length;
        for (int i = 0; i < len; i++) {
            int size = words[i].length();
            for (int j = 0; j <= size; j++) {
                for (int k = size; k >= 0; k--) {
                    String prefix = words[i].substring(0, j);
                    String suffix = words[i].substring(k);
                    map.put(prefix + "," + suffix, i);
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        String toSearch = prefix + "," + suffix;
        if (map.containsKey(toSearch)) {
            return map.get(toSearch);
        }
        return -1;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
