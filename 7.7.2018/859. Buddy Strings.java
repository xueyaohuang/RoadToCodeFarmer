class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (A.equals(B)) {
            int[] freq = new int[26];
            for (char c : A.toCharArray()) {
                if (freq[c - 'a'] >= 1) {
                    return true;
                }
                else {
                    freq[c - 'a']++;
                }
            }
            return false;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                if (list.size() == 2) {
                    return false;
                }
                list.add(i);
            }
        }
        return A.charAt(list.get(0)) == B.charAt(list.get(1)) && A.charAt(list.get(1)) == B.charAt(list.get(0));
    }
}
