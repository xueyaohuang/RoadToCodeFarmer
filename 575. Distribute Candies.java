class Solution {
    // return Math.min(candies.length / 2, set.size())
    public int distributeCandies(int[] candies) {
        // By doing that, the compiler does not have to resize later time.
        Set<Integer> set = new HashSet<>(candies.length);
        for (int i : candies) {
            set.add(i);
            if (set.size() >= candies.length / 2) {
                return candies.length / 2;
            }
        }
        return set.size();
    }
}
