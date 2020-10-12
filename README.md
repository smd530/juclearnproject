# juclearnproject
juc学习项目

***

**多线程代码书写模版**

1. 高内聚低耦合的前提下 线程操作资源类 先写个资源类
2. 判断/干活/通知
3. 多线程交互中 必须要防止多线程的虚假唤醒 也即（判断使用while 不能用if）
4. 注意标志位的修改和定位

***

**让当前线程睡一会等操作可以使用 TimeUnit**

![](http://img.tomato530.com/TImeUnit.png)