// fast add, slow find
class TwoSum {

    Map<Integer, Integer> map;
    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, 2);
        }
        else {
            map.put(number, 1);
        }
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        Iterator<Integer> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            int num1 = iter.next();
            int num2 = value - num1;
            if (map.containsKey(num2)) {
                if (num1 != num2 || map.get(num1) == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}

// slow add, fast find

class TwoSum {

    Set<Integer> num;
    Set<Integer> sum;
    /** Initialize your data structure here. */
    public TwoSum() {
        num = new HashSet<>();
        sum = new HashSet<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (num.contains(number)) {
            sum.add(number * 2);
        }
        else {
            Iterator<Integer> iter = num.iterator();
            while (iter.hasNext()) {
                sum.add(number + iter.next());
            }
            num.add(number);
        }
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        return sum.contains(value);
    }
}
