class Solution {
    public int repeatedStringMatch(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        int min = lenB / lenA;
        int max = lenB / lenA + 2;
        for (int i = min; i <= max; i++) {
            String s = String.join("", Collections.nCopies(i, A));
            if (s.indexOf(B) != -1) {
                return i;
            }
        }
        return -1;
    }
}

class Solution {
    public int repeatedStringMatch(String A, String B) {
        int count = 1;
        StringBuilder sb = new StringBuilder(A);
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if (sb.toString().contains(B)) {
            return count;
        }
        if (sb.append(A).toString().contains(B)) {
            return count + 1;
        }
        return -1;      
    }
}

class Solution {
    public int repeatedStringMatch(String A, String B) {
        if (A == null || B == null) {
            return -1;
        }
        int lenA = A.length();
        int lenB = B.length();
        String doubleA = A + A;
        int idx = doubleA.indexOf(B.substring(0, Math.min(lenA, lenB)));
        if (idx == -1) {
            return -1;
        }
        int count = 1;
        int idxB = 0;
        while (idxB < lenB) {
            int countA = 0;
            int idxA = idx;
            while (countA < lenA && idxB< lenB) {
                if (A.charAt(idxA) != B.charAt(idxB)) {
                    return -1;
                }
                countA++;
                idxB++;
                idxA = (idxA + 1) % lenA;
                if (idxA == 0 && idxB < lenB) {
                    count++;
                }
            }
        }
        return count;
    }
}
