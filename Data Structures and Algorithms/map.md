# Map

1. Java 的map有HashMap和TreeMap, HashMap被用来快速访问，而TreeMap则保证『键』始终有序。
2. Map 可以返回键的 Set, 值的 Collection, 键值对的 Set.

```
Set<String> set = map.keySet();
// iterate Map
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    int value = entry.getValue();
    // do some thing
}
```

3. [Hashmap vs Hashtable](https://www.geeksforgeeks.org/differences-between-hashmap-and-hashtable-in-java/)

## TreeMap

1. TreeMap uses a red-black tree in the background which makes sure that there are no duplicates; additionally it also maintains the elements in a sorted order.

2. For operations like add, remove, containsKey, time complexity is O(log n where n is number of elements present in TreeMap.
3. HashMap implements Hashing, while TreeMap implements Red-Black Tree(a Self Balancing Binary Search Tree). Therefore all differences between Hashing and Balanced Binary Search Tree apply here.
4. Both HashMap and TreeMap have their counterparts HashSet and TreeSet. HashSet and TreeSet implement Set interface. In HashSet and TreeSet, we have only key, no value, these are mainly used to see presence/absence in a set.

## LinkedHashMap

1. 

LRU cache
