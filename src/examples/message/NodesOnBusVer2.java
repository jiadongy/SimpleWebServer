package examples.message;


import busmode.messagebus.ver2.Bus;
import busmode.messagebus.ver2.BusAgent;
import busmode.messagebus.ver2.IService;
import busmode.messagebus.ver2.base.MessageReceiveHandler;
import busmode.messagebus.ver2.base.ServiceRegisterOption;
import busmode.messagebus.ver2.base.ServiceType;
import busmode.messagebus.ver2.base.SubscribePublishMessage;

/**
 * Created by Feiyu on 2015/6/29 0029.
 * ���ڵ��ò���NodeAHandler��process(SubscribePublishMessage)��Bug��
 * ��Ϊoverload�����ڱ���ʱȷ���ģ�������override
 * ���������1.���ýӿڣ���ʵ�ִ����պ�����
 *          2.ɾ��process(Message)������MQ�ַ�ʱ���밴������ʱʵ����������ת��
 *          3.BusAgent�������Message��<ServiceType,data>,ʹ�ͻ���רע�ڴ�������
 *          4.
 **/
public class NodesOnBusVer2 {

    private class NodeA implements IService {

        private class NodeAHandler extends MessageReceiveHandler{
            @Override
            public Object process(SubscribePublishMessage message) {
                System.out.println("NodeA Process: type "+message.getPublishType()
                        +", data "+message.getData());
                return null;
            }
        }
        private BusAgent agent = new BusAgent(this,new NodeAHandler());

        public Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("NodeA start receiving");
                    while (true){
                        System.out.println("NodeA prepare to take message");
                        agent.takeMultipleAndProcess(10);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    private class NodeB implements IService{
        private class NodeBHandler extends MessageReceiveHandler{
            @Override
            public Object process(SubscribePublishMessage message) {
                System.out.println("NodeB Process: type "+message.getPublishType()
                        +", data "+message.getData());
                return null;
            }
        }
        private BusAgent agent = new BusAgent(this,new NodeBHandler());

        public Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("NodeB start receiving");
                    while (true){
                        System.out.println("NodeB prepare to take message");
                        agent.takeMultipleAndProcess(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private class NodeC implements IService{
        private String string;
        private class NodeCHandler extends MessageReceiveHandler{
            @Override
            public Object process(SubscribePublishMessage message) {
                System.out.println("NodeC Process: type "+message.getPublishType()
                        +", data "+message.getData());
                string = "aaa";
                return null;
            }
        }
        private BusAgent agent = new BusAgent(this,new NodeCHandler());

        public Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("NodeC start receiving");
                    while (true){
                        System.out.println("NodeC prepare to take message");
                        agent.takeMultipleAndProcess(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static public void main(String... args){
        NodesOnBusVer2 test = new NodesOnBusVer2();
        Bus bus = Bus.getInstance();
        bus.start();
        NodeA a = test.new NodeA();
        NodeB b = test.new NodeB();
        NodeC c = test.new NodeC();
        a.mainThread.start();
        b.mainThread.start();
        c.mainThread.start();

        a.agent.register(new ServiceRegisterOption(
                new ServiceType("A"),
                new ServiceType("B"),
                new ServiceType("C")));
        b.agent.register(new ServiceRegisterOption(
                new ServiceType("B"),
                new ServiceType("C")));
        c.agent.register(new ServiceRegisterOption(
                new ServiceType("C"),
                new ServiceType("B"),
                new ServiceType("A")));

        a.agent.submit(new SubscribePublishMessage<>("A dada", new ServiceType("B")));
        b.agent.submit(new SubscribePublishMessage<>("B dada",new ServiceType("A")));
        c.agent.submit(new SubscribePublishMessage<>("C dada",new ServiceType("B")));

    }
}
