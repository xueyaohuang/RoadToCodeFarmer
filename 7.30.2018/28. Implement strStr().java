// naive way
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            for (; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}

// kmp algo:
public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (needle.length() > haystack.length() || haystack.length() == 0) return -1;
        char[] ndl = needle.toCharArray();
        char[] hay = haystack.toCharArray();
        int[] pai = new int[ndl.length];
        pai[0] = -1;
        int k = -1;
        for (int i = 1; i < ndl.length; i++) {
            while (k > -1 && ndl[k + 1] != ndl[i]) {
                k = pai[k];
            }
            if (ndl[k + 1] == ndl[i]) {
                k++;
            }
            pai[i] = k;

        }
        k = -1;
        for (int i = 0; i < hay.length; i++) {
            while (k > -1 && ndl[k + 1] != hay[i]) {
                k = pai[k];
            }
            if (ndl[k + 1] == hay[i]) {
                k++;
                if (k == ndl.length - 1) {
                    return i - k;
                }
            }
        }
        return -1;
    }
}

public int strStr(String haystack, String needle){
    if (haystack == null || needle == null)
        return -1;
    //generate next array, need O(n) time
    int i = -1, j = 0, m = haystack.length(), n = needle.length();
    int[] next = new int[n];
    if (next.length > 0) 
        next[0] = -1;
    while (j < n - 1) {
        if (i == -1 || needle.charAt(i) == needle.charAt(j))
            next[++j] = ++i;
        else 
            i = next[i];
    }
    //check through the haystack using next, need O(m) time
    i = 0; j = 0;
    while (i < m && j < n) {
        if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
            i++;
            j++;
        }
        else 
            j = next[j];
    }
    if (j == n)
        return i - j;
    return -1;
}

//Rabin-Karp algorithm.
public class Solution {
    long R = 31L;
	long M = 10000000000000003L;
	long RK; // R^(pattern.length) % M

	public String strStr(String haystack, String needle) {
		if (haystack == null || needle == null || haystack.length() < needle.length())
		    return null;
		if(needle.length() == 0)
		    return haystack;

		long target = hash(needle, 0, needle.length() - 1);
		long hash = hash(haystack, 0, needle.length() - 1);

		RK = 1;
		for (int i = 0; i < needle.length(); i++) {
			RK = (RK * R) % M;
		}
		RK %= M;

		if (hash == target)
			return haystack;
		for (int i = 1; i <= haystack.length() - needle.length(); i++) {
			long tmp = nextHash(hash, haystack.charAt(i - 1), haystack.charAt(i + needle.length() - 1));
			if (tmp == target)
				return haystack.substring(i);
			hash = tmp;
		}
		return null;
	}

	long hash(String s, int start, int end) {
		long sum = 0;
		for (int i = start; i <= end; i++) {
			sum = sum * R % M + (int) s.charAt(i) % M;
		}
		return sum % M;
	}

	long nextHash(long hash, char oldFirst, char next) {
		long a = hash * R % M;
		long b = next % M;
		long c = oldFirst % M * RK % M;

		return (a + b - c + M) % M;
	}
}
