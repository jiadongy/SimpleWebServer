package examples.message;

import busmode.messagebus.ver2.base.Message;
import busmode.messagebus.ver2.base.ServiceType;
import busmode.messagebus.ver2.base.SubscribePublishMessage;

/**
 * Created by Feiyu on 2015/6/29 0029.
 * Java��̬�������� �� ����ʱ ���� ����ʱ
 * ����� ����ʱָ��
 *
 * ??������಻override process��Message������ô�ͻ���ø����е�Process��Message��
 * Ϊʲô����ƥ�� Message����������������أ�
 * case3�����Ǹ��ݴ�������ı���ʱ����ȷ���˵������ĸ�Overload����
 * line23 ���� overload�Ǳ���ʱȷ����override������ʱȷ��������
 **/
public class PolymorphicMethodTest {

    Message message = new SubscribePublishMessage<>("sbm",
            new ServiceType("typeA"));

    private abstract class ProcessHanlder {
        //�����ע�͵��ͱ�����
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

        //���Ͳ���
        Message message = tester.message;

        //���ͷ���
        tester.handler.process(message);

        System.out.println("getData() :" + message.getData());

        System.out.println("RuntimeDataClass :" + message.getData().getClass());

        System.out.println("getDataClass() :" + message.getDataClass());

        //����ʧ�ܵ�����ת��
        /*Message<Integer> message1 = tester.message;

        System.out.println("getData() :" + message1.getData());
        //���е����ʱ�׳��쳣����.getClass()ʱ������
        System.out.println("RuntimeDataClass :" + message1.getData().getClass());

        System.out.println("getDataClass() :" + message1.getDataClass());*/

        //test case3:���Է��ͷ���Override����
        Handler handler1 = tester.handler;

        Message message1 = tester.message;
        handler1.process(message1);

        SubscribePublishMessage message2 = (SubscribePublishMessage) tester.message;
        handler1.process(message2);

    }
}
