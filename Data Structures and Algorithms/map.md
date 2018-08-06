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

## TreeMap

## LinkedHashMap

LRU cache
