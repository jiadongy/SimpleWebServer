# SimpleWebServerԭ��̽��+��֤
## ģ���ͨ��
ͬ��ģʽ����synchronizedͬ���飬Lock�ӿڣ�Object�Դ���wait/yield/sleep/notify��̬�������������ģ���̫�����ڶ����Ӹ߲����ĳ����������÷������ķ�ʽ���õ�����ϵͳ��Դ��

### Callback��Unblocking��
�ص���Javascript���õĺܶ࣬nodejsʵ�ֵĺ�̨��Ȼֻ�е��̣߳�����Ч���ܺ�����Server�������¡�Java Swing�д������˻��ڽӿڵĻص���
ͨ������
```java
Interface CompletionHandler<K,V>{
    completed(K result,V attechment);
    failed(V attechment)
}
```
�ص���
�۲���ģʽҲ��callback����ԭ�����¼�ֱ������м����һ��Observable��Observer�������Ը��ã��ŵ���һ�Զ࣬��ȱ����ÿ��Observable/Observer��ֻ�ܷ���һ����Ϣ��

### MessageBus
MessageBus��MessageQueue�����Ͻ�һ����װ��rabbitMQ�õ�ͦ�ࡣ
�ȳ����ڵ�������MB���ٰ�����չ������ֲ�ʽ��
Bus��һ���н��ߣ�ע�������ģʽ/������ģʽ����
local��������Ϣ�ɿ���/�־û������⣬ֻ��Bus��Ϣ�����;�������չ�豸��������Ϣ����������

#### v2.2 Implementation
Note��Message Router��һ��������ʵ�֡���һ����װ��ϢΪEvent����ת��Ϊ�¼�����ģ�͡�

�ṹ��
[���߳�Bus]<----->[BusAgent<->���߳�Module]<--->[ExecutorService]

���̣�
- BusAgent��registerʱ�ṩһ��ServiceDescriptionָ�����ṩ�ķ���
- Bus��������BlockingQueueʵ�֣�ʵ������BusAgent����Post��Ϣ��Bus.start()�����Message�����Ͱ���Ϣ���͸�����������BusAgent�����䡣
- Module����⵽�����յ�һ��/�����Ϣ��������ύ���̳߳ش���

���ڶ�����4�ֻ���������Ϣ����չ��Message�����࣬�������㿪��ԭ��
1. RequestResponseMessage ����/��Ӧʽ
2. SubscribePublishMessage ����/����ʽ
3. BroadcastMessage �㲥ʽ
4. ProducerCustomerMessage ������/������ʽ

ÿ����Ϣ�в�ͬ��ɸѡĿ�ĵصķ�������������Ӧָ����Ӧ��Ϣ�����������߽��ܣ����ķ���ֻ��Ҫָ�������ߵķ������ͣ�Bus�����͵����ж��Ĵ˷���������Ϣ��BusAgent��

**���ԣ�Bus�ڲ�ͬ�߲�������������Ϣ���ӳ�**
VusualVM
MultithreadedTC

**preview��**
- �ֲ�ʽ�ܹ�������ǰ��Server�Ͷ���ļ�Server������������RMIͨ��
- ��Ϣ�־û�����֤�ɿ�Ͷ��
- ��������Ͷ��ʱ��

preview2�� a ��ESB��
- WebService
- ��JSON����һ��������������
- ��η��ַ���
- ��ͬ����֮���Э������Զ�ת��


## WebServer
### nginx��apache
- nginx��nodejs�������¼������C10K���⣬����C10M�����ںˣ���
- apache�������߳�

�첽���ܵ����⣺
- ��ͬ��Ϣ����Ҫ�ܺõĵ��ȣ���Ȼǰ�����߼��޷�˳�����ӵ�����Ϣû�����ܿ����ѵ�

Socket���⣺
�������time_wait��socket�ܶ���ô�죿

reference��[tengine.taobao.org/book/](http://tengine.taobao.org/book/ "tengine.taobao.org/book/")

JDK1.4��ͬ��������IO��ʹ��Selector��SocketChannel����nio.Buffer��FileChannel��һЩ�������ʵ�ָ����FileIO

JDK1.7���첽IO��AsynchronousChannel�ӿڣ�SocketIO��FileIO�����Ѳ����йܸ�Kernel����IOCP�Ƚϲ�֪��Σ�




