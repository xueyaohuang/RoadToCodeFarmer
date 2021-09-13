/*
For those who are wondering about time complexity, it is O ( N * L ^ 3 )
N = Number of Words
L = Highest length of word
The idea is - For each word , we are using 2 nested for loops. So it will be L ^ 2. Now inside the for loop we are using substring method whose TC is O ( L ) . So if you consider that, it will lead to O ( L ^ 3 ) TC for each word. There are N such words. Therefore, O ( N * L ^ 3 ).

PS: I got to know today that Java substring TC has changed from O(1) to O(N) since Java 7. LOL! Thanks!
*/
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            if (canConcat(word, set)) {
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }
    
    private boolean canConcat(String word, Set<String> dict) {
        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && dict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
