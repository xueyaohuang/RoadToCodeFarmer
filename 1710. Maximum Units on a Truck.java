class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int res = 0;
        int count = 0;
        for (int[] box : boxTypes) {
            int num = box[0];
            int unit = box[1];
            int take = Math.min(num, truckSize - count);
            res += take * unit;
            count += take;
            if (count == truckSize) {
                break;
            }
        }
        return res;
    }
}

// 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000,所以可以用bucket sort
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int[] buckets = new int[1001];
        for (int[] box : boxTypes) {
            // 可能有相等unit的box，所以是+=而不是=
            buckets[box[1]] += box[0];
        }
        int res = 0;
        int count = 0;
        for (int i = 1000; i >= 1; i--) {
            if (buckets[i] == 0) {
                continue;
            }
            int take = Math.min(buckets[i], truckSize - count);
            res += take * i;
            count += take;
            if (count == truckSize) {
                break;
            }
        }
        return res;
    }
}
