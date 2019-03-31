func findAndReplacePattern(words []string, pattern string) []string {
    norm := normalize(pattern)
    res := make([]string, 0)
    for _, word := range words {
        if reflect.DeepEqual(normalize(word), norm) {
            res = append(res, word)
        }
    }
    return res
}

func normalize(word string) []int {
    norm := make(map[rune]int)
    res := make([]int, len(word))
    for i, ch := range word {
        if _, ok := norm[ch]; !ok {
            norm[ch] = len(norm)
        }
        res[i] = norm[ch]
    }
    return res
}
