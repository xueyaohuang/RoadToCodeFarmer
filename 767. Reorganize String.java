// sol 1
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

// sol 2
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
