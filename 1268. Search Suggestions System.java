/*
insert and search time ccooomplexity O(word_length)
space complexity:
O(ALPHABET_SIZE * avg_word_length * N) where N is number of words in Trie
*/
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        buildTrie(products, root);
        List<List<String>> res = new ArrayList<>();
        TrieNode node = root;
        for (char c : searchWord.toCharArray()) {
            node = node != null ? node.children[c - 'a'] : null;
            if (node == null) {
                res.add(new ArrayList<>());
                continue;
            }
            if (!node.isSorted) {
                Collections.sort(node.startsWith);
                node.isSorted = true;
            }
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < Math.min(3, node.startsWith.size()); i++) {
                temp.add(node.startsWith.get(i));
            }
            res.add(new ArrayList<>(temp));
        }
        return res;
    }
    
    private void buildTrie(String[] words, TrieNode root) {
        for (String word : words) {
            insert(word, root);
        }
    }
    
    private void insert(String word, TrieNode root) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node.children[c - 'a'].startsWith.add(word);
            node = node.children[c - 'a'];
        }
    }
}

class TrieNode {
    TrieNode[] children;
    List<String> startsWith;
    boolean isSorted;
    public TrieNode() {
        children = new TrieNode[26];
        startsWith = new ArrayList<>();
    }
}

