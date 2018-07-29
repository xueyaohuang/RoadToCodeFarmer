1. String不是primitive type 

2. StringBuffer 与 StringBuilder： 前者保证线程安全，后者不是，但单线程下效率高一些，一般使用 StringBuilder.  
  
3. String concatenation（s1 + s2） vs StringBuilder append  
  * String concatenation会不停产生新的String object，浪费时间和内存。StringBuilder append不产生新的object。
  * 在for loop外，compiler会自动把String concatenation变成StringBuilder append，这时没有区别。但是在for loop中不会，需要用StringBuilder append
  比较下面2个程序
  ```
  ...
  String result = "";
  for (String s : hugeArray) {
      result = result + s;
  }
  ```
  is very time- and memory-wasteful compared with:  
  ```
  ...
  StringBuilder sb = new StringBuilder();
  for (String s : hugeArray) {
      sb.append(s);
  }
  String result = sb.toString();
  ```
4. String 常用函数：  
  [Java8 oracle String Class](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)
  * Constructors:  
    * String(), Initializes a newly created String object so that it represents an empty character sequence.  
    
    * String(char[] value), Allocates a new String so that it represents the sequence of characters currently contained in the character array argument.  
    * String(char[] value, int offset, int count), Allocates a new String that contains characters from a subarray of the character array argument.  
    * String(String original), Initializes a newly created String object so that it represents the same sequence of characters as the argument; in other words, the newly created string is a copy of the argument string.  
    * String(StringBuffer buffer), Allocates a new string that contains the sequence of characters currently contained in the string buffer argument.  
    * String(StringBuilder builder), Allocates a new string that contains the sequence of characters currently contained in the string builder argument.
  * Methods 
    * int	compareTo(String anotherString),  compareToIgnoreCase(String str)  
      Compares two strings lexicographically.
    
    * boolean	contains(CharSequence s)  
      Returns true if and only if this string contains the specified sequence of char values (Strings are CharSequences, i.e.       String implements CharSequence). Case sensitive
    * boolean	endsWith(String suffix), startsWith(String prefix)   
      Tests if this string ends with the specified suffix.
    * int	indexOf(int ch),  indexOf(int ch, int fromIndex),  indexOf(String str),  indexOf(String str, int fromIndex)  
      Returns the index within this string of the first occurrence of the specified character.
    * static String	join(CharSequence delimiter, CharSequence... elements),  join(CharSequence delimiter, Iterable<? extends CharSequence> elements)  
      Returns a new String composed of copies of the CharSequence elements joined together with a copy of the specified delimiter.  
      String.join("-> ", "Wake up", "Eat", "Play", "Sleep", "Wake up");  
      Wake up-> Eat-> Play-> Sleep-> Wake up
    * int	lastIndexOf(int ch), lastIndexOf(int ch, int fromIndex), lastIndexOf(String str), 	lastIndexOf(String str, int fromIndex)
      Returns the index within this string of the last occurrence of the specified character.
    * String	replace(char oldChar, char newChar)  
      Returns a string resulting from replacing all occurrences of oldChar in this string with newChar.
    * String[]	split(String regex)  
      Splits this string around matches of the given regular expression.
    * static String	valueOf(primitive type b), valueOf(char[] data), valueOf(Object obj)  
      Returns the string representation of the boolean argument.
