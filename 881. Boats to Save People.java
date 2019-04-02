class Solution {
    public int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {
            return 0;
        }
        int len = people.length;
        Arrays.sort(people);
        int i = 0;
        int j = len - 1;
        int count = 0;
        while (i < j) {
            if (people[i] + people[j] <= limit) {
                i++;
                j--;
            } else {
                j--;
            }
            count++;
        }
        return i == j ? count + 1 : count;
    }
}

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int[] buckets = new int[limit + 1];
        for (int p: people) {
            buckets[p]++;
        }
        int start = 0;
        int end = buckets.length - 1;
        int count = 0;
        while (start <= end) {
			//make sure the start always point to a valid number
            while (start <= end && buckets[start] == 0) {
                start++;
            }
			//make sure end always point to valid number
            while (start <= end && buckets[end] == 0) {
                end--;
            }
			//no one else left on the ship, hence break.
            if (buckets[start] <= 0 && buckets[end] <= 0) {
                break;
            }
            if (start + end <= limit) {
                buckets[start]--; // both start and end can carry on the boat
            }
            buckets[end]--;
            count++;
        }
        return count;
    }
}
