// O(nm^2)
// replace each character in a String with a filler '*' and check if it already existed. if not, add to the set.
// use hashing to get O(nm), but may have collision
// https://leetcode.com/problems/strings-differ-by-one-character/discuss/801825/Python-Clean-set-%2B-string-hashing-solution-from-O(NM2)-to-O(NM)
class Solution {
    public boolean differByOne(String[] dict) {
        Set<String> set = new HashSet<>();
        for (String s : dict) {
            char[] ca = s.toCharArray();
            for (int i = 0; i < ca.length; i++) {
                char c = ca[i];
                ca[i] = '*';
                if (!set.add(String.valueOf(ca))) {
                    return true;
                }
                ca[i] = c;
            }
        }
        return false;
    }
}
