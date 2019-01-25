class Solution {
    
    class TrieNode {
        TrieNode[] children;
        int index;
        List<Integer> list;
        public TrieNode() {
            children = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            searchWord(root, words[i], i, res);
        }
        return res;
    }
    
    private void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            if (root.children[word.charAt(i) - 'a'] == null) {
                root.children[word.charAt(i) - 'a'] = new TrieNode();
            }
            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }
            root = root.children[word.charAt(i) - 'a'];
        }
        root.index = index;
        root.list.add(index);
    }
    
    private void searchWord(TrieNode root, String word, int index, List<List<Integer>> res) {
        // word的部分长度与另一个完整单词是palindrome，word剩下部分自身是palindrome
        for (int i = 0; i < word.length(); i++) {
            if (isPalindrome(word, i, word.length() - 1) && root.index >= 0 && root.index != index) {
                res.add(Arrays.asList(index, root.index));
            }
            root = root.children[word.charAt(i) - 'a'];
            if (root == null) {
                return;
            }
        }
        // word的全部长度与另一单词的部分长度是palindrome，另一单词剩下部分自身是palindrome
        for (int i : root.list) {
            if (i != index) {
                res.add(Arrays.asList(index, i));
            }
        }
    }
    
    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
}
