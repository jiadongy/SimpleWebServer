package examples.message;

import busmode.messagebus.ver2.base.Message;
import busmode.messagebus.ver2.base.ServiceType;
import busmode.messagebus.ver2.base.SubscribePublishMessage;

/**
 * Created by Feiyu on 2015/6/29 0029.
 * Java多态方法调用 是 运行时 还是 编译时
 * 结果： 运行时指定
 *
 * ??如果子类不override process（Message），那么就会调用父类中的Process（Message）
 * 为什么会先匹配 Message父类而不是其子类呢？
 * case3表明是根据传入参数的编译时类型确定了调用了哪个Overload方法
 * line23 表明 overload是编译时确定，override是运行时确定！！！
 **/
public class PolymorphicMethodTest {

    Message message = new SubscribePublishMessage<>("sbm",
            new ServiceType("typeA"));

    private abstract class ProcessHanlder {
        //把这个注释掉就报错了
        <U> U process(Message message){
            System.out.println("Message Father:" + message);
            return null;
        }

        <K> K process(SubscribePublishMessage message){
            System.out.println("SubscribePublishMessage Father:" + message);
            return null;
        }
    }
    private class Handler extends ProcessHanlder{

        @Override
        Object process(SubscribePublishMessage message){
            System.out.println("SubscribePublishMessage Son:" + message);
            return null;
        }

    }

    public Handler handler = new Handler();

    static public void main(String... args){
        PolymorphicMethodTest tester = new PolymorphicMethodTest();

        //泛型擦除
        Message message = tester.message;

        //泛型方法
        tester.handler.process(message);

        System.out.println("getData() :" + message.getData());

        System.out.println("RuntimeDataClass :" + message.getData().getClass());

        System.out.println("getDataClass() :" + message.getDataClass());

        //泛型失败的类型转换
        /*Message<Integer> message1 = tester.message;

        System.out.println("getData() :" + message1.getData());
        //运行到这句时抛出异常，在.getClass()时出错？？
        System.out.println("RuntimeDataClass :" + message1.getData().getClass());

        System.out.println("getDataClass() :" + message1.getDataClass());*/

        //test case3:测试泛型方法Override调用
        Handler handler1 = tester.handler;

        Message message1 = tester.message;
        handler1.process(message1);

        SubscribePublishMessage message2 = (SubscribePublishMessage) tester.message;
        handler1.process(message2);

    }
}
