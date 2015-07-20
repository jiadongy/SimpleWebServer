# SimpleWebServer原型验证
## 模块间通信
同步模式下用synchronized同步块，Lock接口，Object自带的wait/yield/sleep/notify静态方法都是阻塞的，不太适用于短连接高并发的场景，尝试用非阻塞的方式更好的利用系统资源。

### Callback（Unblocking）
回调在Javascript中用的很多，nodejs实现的后台虽然只有单线程，但是效果能和主流Server不相上下。Java Swing中大量用了基于接口的回调。
通过类似
```java
Interface CompletionHandler<K,V>{
    completed(K result,V attechment);
    failed(V attechment)
}
```
回调。
观察者模式也是callback，在原来上下级直接耦合中间插入一层Observable和Observer，隔离性更好，优点是一对多，但缺点是每个Observable/Observer对只能发出一种消息。

### MessageBus
MessageBus在MessageQueue基础上进一步包装，rabbitMQ用的挺多。
先尝试在单机做个MB，再把它扩展到网络分布式。
Bus是一个中介者，注意和命令模式/责任链模式区别。
local不考虑消息可靠性/持久化的问题，只管Bus消息容量和尽可能扩展设备数量和消息饱和容量。

#### v2.2 Implementation
Note：Message Router。一个阻塞的实现。进一步包装消息为Event可以转化为事件驱动模型。

结构：
[单线程Bus]<----->[BusAgent<->单线程Module]<--->[ExecutorService]

流程：
- BusAgent：register时提供一个ServiceDescription指定所提供的服务。
- Bus：队列用BlockingQueue实现，实例化后BusAgent可以Post消息，Bus.start()后根据Message的类型把消息推送给符合条件的BusAgent的信箱。
- Module：检测到信箱收到一个/多个消息后把任务提交给线程池处理。

现在定义了4种基本类型消息，扩展自Message抽象类，尽量满足开闭原则：
1. RequestResponseMessage 请求/响应式
2. SubscribePublishMessage 订阅/发布式
3. BroadcastMessage 广播式
4. ProducerCustomerMessage 生产者/消费者式

每种消息有不同的筛选目的地的方法，如请求响应指定响应消息必须由请求者接受；订阅发布只需要指定发布者的服务类型，Bus会推送到所有订阅此服务类型消息的BusAgent。

**测试：Bus在不同高并发量场景下消息的延迟**
VusualVM
MultithreadedTC

**preview：**
- 分布式架构（如多个前端Server和多个文件Server连起来），用RMI通信
- 消息持久化，保证可靠投递
- 尽量缩短投递时间

preview2： a “ESB”
- WebService
- 用JSON定义一个服务描述语言
- 如何发现服务
- 不同服务之间的协议可以自动转化


## WebServer
### nginx、apache
- nginx，nodejs：基于事件，解决C10K问题，还有C10M（外内核？）
- apache：基于线程

异步可能的问题：
- 不同消息间需要很好的调度，不然前后处理逻辑无法顺利连接导致消息没法被很快消费掉

Socket问题：
如果处于time_wait的socket很多怎么办？

reference：[tengine.taobao.org/book/](http://tengine.taobao.org/book/ "tengine.taobao.org/book/")

JDK1.4的同步非阻塞IO，使用Selector和SocketChannel；用nio.Buffer和FileChannel在一些情况下能实现更快的FileIO

JDK1.7的异步IO，AsynchronousChannel接口（SocketIO和FileIO），把操作托管给Kernel，和IOCP比较不知如何？




