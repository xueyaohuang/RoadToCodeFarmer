// trie: Apparently, we need to do pruning when current character is not in any word
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length, n = board[0].length;
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, root, res, i, j);
            }
        }
        return res;
    }
    
    private void dfs(char[][] board, TrieNode node, List<String> res, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '.' || node.children[board[i][j] - 'a'] == null) {
            return;
        }
        char c = board[i][j];
        board[i][j] = '.';
        node = node.children[c - 'a'];
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        dfs(board, node, res, i + 1, j);
        dfs(board, node, res, i - 1, j);
        dfs(board, node, res, i, j + 1);
        dfs(board, node, res, i, j - 1);
        board[i][j] = c;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.word = w;
        }
        return root;
    }
}

class TrieNode {
    TrieNode[] children;
    String word;
    public TrieNode() {
        children = new TrieNode[26];
    }
}

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;
        TrieNode root = buildTrie(words);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floodFill(board, i, j, res, root);
            }
        }
        return res;
    }
    
    private void floodFill(char[][] board, int i, int j, List<String> res, TrieNode node) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        char c = board[i][j];
        if (c == '*' || node.next[c - 'a'] == null) {
            return;
        }
        node = node.next[c - 'a'];
        if (node.str != null) {
            res.add(node.str);
            node.str = null;
            // 这里不能return，因为可能有几个word的开头是一样的
        }
        board[i][j] = '*';
        floodFill(board, i - 1, j, res, node);
        floodFill(board, i + 1, j, res, node);
        floodFill(board, i, j - 1, res, node);
        floodFill(board, i, j + 1, res, node);
        board[i][j] = c;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
            }
            node.str = word;
        }
        return root;
    }
    
    public class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String str;
    }
}


// Using word search I, too slow
class Solution {
    
    private boolean[][] visited;
    
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        for (String word : words) {
            if (exist(board, word)) {
                res.add(word);
            }
        }
        return new ArrayList<String>(res);
    }
    
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (hereExisted(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean hereExisted(char[][] board, String word, int row, int col, int index) {
        if (index >= word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] == true || board[row][col] != word.charAt(index)) {
            return false;
        }
        visited[row][col] = true;
        if (hereExisted(board, word, row + 1, col, index + 1) ||
            hereExisted(board, word, row - 1, col, index + 1) ||
            hereExisted(board, word, row, col + 1, index + 1) ||
            hereExisted(board, word, row, col - 1, index + 1)) {
            return true;
        }
        visited[row][col] = false;
        return false;
    }
}
