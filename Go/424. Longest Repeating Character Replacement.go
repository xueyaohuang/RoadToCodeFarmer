func characterReplacement(s string, k int) int {
    var count [26]int
    max_count, max_len, start := 0, 0, 0
    for end := 0; end < len(s); end++ {
        count[s[end] - 'A']++
        max_count = max(max_count, count[s[end] - 'A'])
        if end - start + 1 - max_count > k {
            count[s[start] - 'A']--
            start++
        }
        max_len = max(end - start + 1, max_len)
    }
    return max_len
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}
