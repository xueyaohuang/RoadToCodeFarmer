/*
insert and search time complexity O(word_length)
space complexity:
O(ALPHABET_SIZE * avg_word_length * N) where N is number of words in Trie
*/
class Solution {
    TrieNode root;
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        root = new TrieNode();
        for (String product : products) {
            insert(product);
        }
        List<List<String>> res = new ArrayList<>();
        TrieNode node = root;
        for (char c : searchWord.toCharArray()) {
            node = node == null ? null : node.children[c - 'a'];
            if (node == null) {
                res.add(new ArrayList<>());
                continue;
            }
            if (!node.isSorted) {
                Collections.sort(node.startWith);
                node.isSorted = true;
            }
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < Math.min(3, node.startWith.size()); i++) {
                temp.add(node.startWith.get(i));
            }
            res.add(temp);
        }
        return res;
    }
    
    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node.children[c - 'a'].startWith.add(word);
            node = node.children[c - 'a'];
        }
    }
}

class TrieNode {
    TrieNode[] children;
    List<String> startWith;
    boolean isSorted;
    public TrieNode() {
        children = new TrieNode[26];
        startWith = new ArrayList<>();
        isSorted = false;
    }
}
