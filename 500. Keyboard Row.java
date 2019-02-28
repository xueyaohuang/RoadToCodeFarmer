class Solution {
    public String[] findWords(String[] words) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('q', 1); map.put('w', 1); map.put('e', 1); map.put('r', 1); map.put('t', 1); 
        map.put('y', 1); map.put('u', 1); map.put('i', 1); map.put('o', 1); map.put('p', 1);
        map.put('a', 2); map.put('s', 2); map.put('d', 2); map.put('f', 2); map.put('g', 2);
        map.put('h', 2); map.put('j', 2); map.put('k', 2); map.put('l', 2); map.put('z', 3);
        map.put('x', 3); map.put('c', 3); map.put('v', 3); map.put('b', 3); map.put('n', 3); map.put('m', 3);
        List<String> temp = new ArrayList<>();
        for (String word : words) {
            char c = word.charAt(0);
            int line = Character.isLowerCase(c) ? map.get(c) : map.get((char)(c + 32));
            int i = 1;
            for (; i < word.length(); i++) {
                char ch = word.charAt(i);
                int cur = Character.isLowerCase(ch) ? map.get(ch) : map.get((char)(ch + 32));
                if (cur != line) {
                    break;
                }
            }
            if (i == word.length()) {
                temp.add(word);
            }
        }
        return temp.toArray(new String[temp.size()]);
    }
}

class Solution {
    public String[] findWords(String[] words) {
        int[] ws = new int[]{2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3};
        List<String> res = new ArrayList<>();
        //1. 遍历每个字符串
        for (String str : words) {
            //2. 判断一行
            int line = 0;
            boolean flag = true;
            for (char ch : str.toCharArray()) {
                int index = (ch > 96) ? ch - 97 : ch - 65;
                if (line == 0) {
                    line = ws[index];
                } else {
                    if (line != ws[index]){
                        flag = false;
                        break;
                    } 
                }
            }
            if (flag) {
                res.add(str); 
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
