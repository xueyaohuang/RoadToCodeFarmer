class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= 6; i++) {
            min = Math.min(min, getRotation(A, B, i));
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private int getRotation(int[] A, int[] B, int n) {
        int rotateA = 0;
        int rotateB = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != n && B[i] != n) {
                return Integer.MAX_VALUE;
            }
            if (A[i] != n) {
                rotateA++;
            } 
            if (B[i] != n) {
                rotateB++;
            }
        }
        return Math.min(rotateA, rotateB);
    }
}

class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= 6; i++) {
            min = Math.min(min, getRotation(A, B, i));
            min = Math.min(min, getRotation(B, A, i));
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private int getRotation(int[] A, int[] B, int n) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == n) {
                continue;
            }
            if (B[i] != n) {
                return Integer.MAX_VALUE;
            }
            res++;
        }
        return res;
    }
}

class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int[] mapa = new int[7];
        int[] mapb = new int[7];
        int len = A.length;
        for (int i : A) {
            mapa[i]++;
        }
        for (int i : B) {
            mapb[i]++;
        }
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple a, Tuple b) {
                return b.count - a.count;
            }
        });
        for (int i = 1; i <= 6; i++) {
            if (mapa[i] + mapb[i] >= len) {
                pq.offer(new Tuple(i, Math.max(mapa[i], mapb[i]), mapa[i] >= mapb[i] ? 'a' : 'b'));
            }
        }
        int min = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Tuple cur = pq.poll();
            if (cur.arr == 'a') {
                min = Math.min(min, getRotation(A, B, cur.num));
            } else {
                min = Math.min(min, getRotation(B, A, cur.num));
            }
            if (min < Integer.MAX_VALUE) {
                break;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private int getRotation(int[] A, int[] B, int n) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == n) {
                continue;
            }
            if (B[i] != n) {
                return Integer.MAX_VALUE;
            }
            res++;
        }
        return res;
    }
}

class Tuple {
    int num;
    int count;
    char arr;
    public Tuple(int num, int count, char arr) {
        this.num = num;
        this.count = count;
        this.arr = arr;
    }
}

// what if no range lilitation for the number
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        Map<Integer, Integer> mapa = new HashMap<>();
        Map<Integer, Integer> mapb = new HashMap<>();
        int len = A.length;
        for (int i : A) {
            mapa.put(i, mapa.getOrDefault(i, 0) + 1);
        }
        for (int i : B) {
            mapb.put(i, mapb.getOrDefault(i, 0) + 1);
        }
        if (mapa.size() == 1 || mapb.size() == 1) {
            return 0;
        }
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple a, Tuple b) {
                return b.count - a.count;
            }
        });
        for (int i : mapa.keySet()) {
            if (mapb.containsKey(i) && mapa.get(i) + mapb.get(i) >= len) {
                int counta = mapa.get(i);
                int countb = mapb.get(i);
                pq.offer(new Tuple(i, Math.max(counta, countb), counta >= countb ? 'a' : 'b'));
            }
        }
        int min = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Tuple cur = pq.poll();
            if (cur.arr == 'a') {
                min = Math.min(min, getRotation(A, B, cur.num));
            } else {
                min = Math.min(min, getRotation(B, A, cur.num));
            }
            if (min < Integer.MAX_VALUE) {
                break;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private int getRotation(int[] A, int[] B, int n) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == n) {
                continue;
            }
            if (B[i] != n) {
                return Integer.MAX_VALUE;
            }
            res++;
        }
        return res;
    }
}

class Tuple {
    int num;
    int count;
    char arr;
    public Tuple(int num, int count, char arr) {
        this.num = num;
        this.count = count;
        this.arr = arr;
    }
}
