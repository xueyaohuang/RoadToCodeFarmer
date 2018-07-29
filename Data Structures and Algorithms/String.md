String不是primitive type 

StringBuffer 与 StringBuilder： 前者保证线程安全，后者不是，但单线程下效率高一些，一般使用 StringBuilder.  
  
String concatenation（s1 + s2） vs StringBuilder append  
  * String concatenation会不停产生新的String object，浪费时间和内存。StringBuilder append不产生新的object。
  * 在for loop外，compiler会自动把String concatenation变成StringBuilder append，这时没有区别。但是在for loop中不会，需要用StringBuilder append
