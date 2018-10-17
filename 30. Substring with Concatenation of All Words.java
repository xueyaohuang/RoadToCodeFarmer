// O(kn)
// k is the length of String[] words, n is the length of s
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int lens = s.length();
        int lenws = words.length;
        int lenwd = words[0].length();
        int lenWindow = lenws * lenwd;
        
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        for (int i = 0; i + lenWindow <= lens; i++) {
            String window = s.substring(i, i + lenWindow);
            Map<String, Integer> windowMap = new HashMap<>();
            
            for (int j = 0; j < lenws; j++) {
                String curWord = window.substring(j * lenwd, (j + 1) * lenwd);
                int count = windowMap.getOrDefault(curWord, 0) + 1;
                if (count > map.getOrDefault(curWord, 0)) {
                    break;
                } else if (j == lenws - 1) {
                    res.add(i);
                } else {
                    windowMap.put(curWord, count);
                }
            }
        }
        
        return res;
    }
}

// O(n) sliding window
// outer for loop O(length of each word(wl))
// inner for loop, it is a sliding window, O(n/wl)
// mostly 2 times travel for each word, one left side of the window, the other right side of the window
// so, time complexity O(wl * 2 * n/wl) = O(n)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> windowMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int lens = s.length();
        int lenws = words.length;
        int lenwd = words[0].length();
        int lenWindow = lenws * lenwd;
        
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        for (int i = 0; i < lenwd; i++) {
            int left = i, right = i, count = 0;
            windowMap = new HashMap<>();
            // sliding window right pointer
            for (; right + lenwd <= lens; right += lenwd) {
                String curWord = s.substring(right, right + lenwd);
                // 没有当前word，所有清零重来
                if (!map.containsKey(curWord)) {
                    count = 0;
                    left = right + lenwd;
                    windowMap = new HashMap<>();
                } else {
                    windowMap.put(curWord, windowMap.getOrDefault(curWord, 0) + 1);
                    count++;
                    // 移动left pointer
                    while (windowMap.get(curWord) > map.get(curWord)) {
                        String removedLeft = s.substring(left, left + lenwd);
                        windowMap.put(removedLeft, windowMap.get(removedLeft) - 1);
                        count--;
                        left += lenwd;
                    }
                    if (count == lenws) {
                        res.add(left);
                    }
                }
            }
        }
        return res;
    }
}



