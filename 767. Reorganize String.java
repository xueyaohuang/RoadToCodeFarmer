// sol 1
/*
1.count letter appearance and store in freq[i]
2.find the letter with largest occurence.
3.put the letter into even index numbe (0, 2, 4 ...) char array
4.put the rest into the array, idx每次增加2
We construct the resulting string in sequence: at position 0, 2, 4, ... and then 1, 3, 5, ...
In this way, we can make sure there is always a gap between the same characters

Consider this example: "aaabbbcdd", we will construct the string in this way:

a _ a _ a _ _ _ _ // fill in "a" at position 0, 2, 4
a b a _ a _ b _ b // fill in "b" at position 6, 8, 1
a b a c a _ b _ b // fill in "c" at position 3
a b a c a d b d b // fill in "d" at position 5, 7
*/
class Solution {
    public String reorganizeString(String S) {
        int[] map = new int[26];
        char[] result = new char[S.length()];
        int max = 0, letter = 0, idx = 0;
        for (char c : S.toCharArray()) {
            map[c - 'a']++;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] > max) {
                max = map[i];
                letter = i;
            }
            if (max > (S.length() + 1) / 2) {
                return "";
            }
        }
        while (map[letter] > 0) {
            result[idx] = (char)(letter + 'a');
            map[letter]--;
            idx += 2;
        }
        for (int i = 0; i < map.length; i++) {
            while (map[i] > 0) {
                // in fact，idx只会折返一次，所以不会override
                if (idx >= result.length) {
                    idx = 1;
                }
                result[idx] = (char)(i + 'a');
                map[i]--;
                idx += 2;
            }
        }
        return String.valueOf(result);
    }
}

// sol 2: greddy 每次都取剩下最多frequency的两个char，取完放回PriorityQueue
/* time complexity
    O(n) to build map with <Character, Count>
    add to priority queue: k * lg (k) --> constant

    this step you just have 26 entries in map

    Building string - StringBuilder O(n * lg k ) , where k is 26

    you are potentially adding back values into queue

Overall O(n * lg(26)) --> O(n)
space: O(1) because map or PQ has size of alphabet
*/
class Solution {
    public String reorganizeString(String S) {
        int[] map = new int[26];
        int max = 0;
        int len = S.length();
        for (char c : S.toCharArray()) {
            map[c - 'a']++;
            if (max < map[c - 'a']) {
                max = map[c - 'a'];
            }
        }
        if (max >= len / 2 + len % 2 + 1) {
            return "";
        }
        
        PriorityQueue<CharCount> pq = new PriorityQueue<>(new Comparator<CharCount>() {
            @Override
            public int compare(CharCount a, CharCount b) {
                return b.count - a.count;
            }
        });
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                pq.offer(new CharCount((char)('a' + i), map[i]));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            CharCount first = pq.poll();
            first.count--;
            sb.append(first.c);
            if (pq.isEmpty()) {
                break;
            }
            CharCount second = pq.poll();
            second.count--;
            sb.append(second.c);
            if (first.count > 0) {
                pq.offer(first);
            }
            if (second.count > 0) {
                pq.offer(second);
            }
        }
        return sb.toString();
    }
}

class CharCount {
    char c;
    int count;
    public CharCount(char c, int count) {
        this.c = c;
        this.count = count;
    }
}
