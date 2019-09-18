## other
other工程是记录一些平时练习的小例子，例如：算法，并发，异步之类的。
### `futures`包
该包是练习一下，`CompletableFuture`
### `lru`包
是练习写一个简单的LRU（最近最少使用）的缓存淘汰策略

1、`linked`子包是使用`LinkedHashMap`实现的，`LinkedHashMap`默认自带两个特性可以支持LRU策略
* `accessOrder=true`表示按照最近使用的排序策略把最近使用的排在前面，默认是`false`是按照插入顺序
* 重写`LinkedHashMap`的下面方法，根据条件淘汰最近最少使用的
```java
@Override
protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    //当大小查过传入的乏值时，将按照最近最少使用淘汰数据（LRU）
    return size() > capacity;
}
```
2、`lhm`子包是自定义的`HashMap`+链表的实现方式。
3、`casfaa`子包是使用锁、CAS和FAA原语来并发操作同一个资源。