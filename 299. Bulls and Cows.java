// O(n) one pass
class Solution {
    public String getHint(String secret, String guess) {
        int[] freqS = new int[10];
        int[] freqG = new int[10];
        int bull = 0;
        int cow = 0;
        for (int i = 0; i < secret.length(); i++) {
            char cs = secret.charAt(i);
            char cg = guess.charAt(i);
            if (cs == cg) {
                bull++;
            } else {
                freqS[cs - '0']++;
                freqG[cg - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            cow += Math.min(freqS[i], freqG[i]);
        }
        return bull + "A" + cow + "B";
    }
}

// O(n) two pass
class Solution {
    public String getHint(String secret, String guess) {
        Map<Character, Integer> freq = new HashMap<>();
        int n = secret.length();
        int bulls = 0, cows = 0;
        boolean[] bullIdx = new boolean[n];
        // first pass get bulls
        for (int i = 0; i < n; i++) {
            char c = secret.charAt(i);
            if (c == guess.charAt(i)) {
                bulls++;
                bullIdx[i] = true;
            } else {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
        }
      // second pass get cows
        for (int i = 0; i < n; i++) {
            if (bullIdx[i]) {
                continue;
            }
            char c = guess.charAt(i);
            if (freq.containsKey(c) && freq.get(c) > 0) {
                cows++;
                freq.put(c, freq.get(c) - 1);
            }
        }
        return bulls + "A" + cows + "B";
    }
}

// O(n) one pass
class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        //  0 - 9, 10 numbers
        int[] nums = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                bulls++;
            } else {
                // nums[s] < 0 means prev part of guess has curr digit in secret
                // then we found a pair that has same digit with different position
                if (nums[s] < 0) {
                    cows++;
                }
                nums[s]++;
                // nums[g] > 0 means prev part of secret has curr digit in guess
                // then we found a pair that has same digit with different position
                if (nums[g] > 0) {
                    cows++;
                }
                nums[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
