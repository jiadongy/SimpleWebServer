package examples.message;

import busmode.messagebus.ver2_2.Bus;
import busmode.messagebus.ver2_2.BusAgent;
import busmode.messagebus.ver2_2.base.ServiceRegisterOption;
import busmode.messagebus.ver2_2.base.ServiceType;
import busmode.messagebus.ver2_2.base.SubscribePublishMessage;
import util.Utils;

import java.io.IOException;

/**
 * Created by Feiyu on 2015/7/21.
 **/
public class ConcurrentMessageTest {

    private class Node {
        public String name;
        public BusAgent agent;

        public Node(String id) {
            this.name = "Node_" + id;
            this.agent = new BusAgent("BusAgent_" + id);
        }

        public Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //System.out.println(name+"\tStart");
                    while (true) {
                        agent.takeMultiple(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] a) throws InterruptedException, IOException {
        ConcurrentMessageTest env = new ConcurrentMessageTest();
        ServiceType type = new ServiceType("TYPE");
        final int NodeNumber = 5;
        final int MessageNumber = 60000 / NodeNumber;
        Node node = null;
        //Utils.log("Main\tStart Register");
        for (int i = 1; i <= NodeNumber; i++) {
            node = env.new Node(i + "");
            node.agent.register(new ServiceRegisterOption(type, type));
            node.mainThread.start();
        }
        Bus bus = Bus.getInstance();
        bus.setDrainNumber(1000);
        bus.setDispathThreads(7 - NodeNumber);
        bus.start();
        //Utils.log("Main\tStart Send Message");
        long time0 = System.currentTimeMillis();
        for (int i = 1; i <= MessageNumber; i++) {
            node.agent.submit(new SubscribePublishMessage<>(("Data" + i), type));
        }
        while (!bus.isQueueEmptyAndDispatchStop()) {
            Thread.sleep(100);
        }
        Utils.log("Main\tEnd in " + (System.currentTimeMillis() - time0) + " ms");
        Utils.log("Main\tEnd with messages submitted " + bus.getMessagesSubmited());
        Utils.log("Main\tEnd with total messages dispatched " + bus.getTotalDispatched());
        Utils.writer.flush();
        //Utils.writer.close();
    }
}
