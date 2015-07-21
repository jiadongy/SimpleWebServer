package examples.message;

import busmode.messagebus.ver2_1.Bus;
import busmode.messagebus.ver2_1.BusAgent;
import busmode.messagebus.ver2_1.base.MessageData;
import busmode.messagebus.ver2_1.base.ServiceRegisterOption;
import busmode.messagebus.ver2_1.base.ServiceType;
import busmode.messagebus.ver2_1.base.SubscribePublishMessage;
import util.Pair;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Feiyu on 2015/6/30 0029.
 *
 **/
public class NodesOnBusVer2_1 {

    private class NodeA {

        private BusAgent agent = new BusAgent("BusAgentA");

        private ExecutorService pool;

        public NodeA(ExecutorService pool) {
            this.pool = pool;
        }

        private class ATask implements Runnable{
            private Collection<Pair<ServiceType,MessageData>> collection;

            private ATask(Collection<Pair<ServiceType, MessageData>> collection) {
                this.collection = collection;
            }

            @Override
            public void run() {
                System.out.println("Node:Process " + collection.size() + " Pairs");
                for(Pair<ServiceType,MessageData> pair : collection){
                    System.out.println(
                            "Node:Process " + pair._1() + " : " +
                      pair._2().getData()+" : "+pair._2().getData().getClass()
                    );
                }
            }
        }

        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("Node:Start");
                    while(true) {
                        Collection<Pair<ServiceType, MessageData>> collection =
                                agent.takeMultiple(10);
                        pool.submit(new ATask(collection));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private class NodeB {

        private BusAgent agent = new BusAgent("BusAgentB");

        private ExecutorService pool;

        public NodeB(ExecutorService pool) {
            this.pool = pool;
        }

        private class BTask implements Runnable{
            private Collection<Pair<ServiceType,MessageData>> collection;

            private BTask(Collection<Pair<ServiceType, MessageData>> collection) {
                this.collection = collection;
            }

            @Override
            public void run() {
                System.out.println("NodeB:Process "+collection.size()+" Pairs");
                for(Pair<ServiceType,MessageData> pair : collection){
                    System.out.println(
                            "NodeB:Process "+pair._1()+" : "+
                                    pair._2().getData()+" : "+pair._2().getData().getClass()
                    );
                }
            }
        }

        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("NodeB:Start");
                    while(true) {
                        Collection<Pair<ServiceType, MessageData>> collection =
                                agent.takeMultiple(10);
                        pool.submit(new BTask(collection));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private class NodeC {

        private BusAgent agent = new BusAgent("BusAgentC");

        private ExecutorService pool;

        public NodeC(ExecutorService pool) {
            this.pool = pool;
        }

        private class CTask implements Runnable{
            private Collection<Pair<ServiceType,MessageData>> collection;

            private CTask(Collection<Pair<ServiceType, MessageData>> collection) {
                this.collection = collection;
            }

            @Override
            public void run() {
                System.out.println("NodeC:Process "+collection.size()+" Pairs");
                for(Pair<ServiceType,MessageData> pair : collection){
                    System.out.println(
                            "NodeC:Process "+pair._1()+" : "+
                                    pair._2().getData()+" : "+pair._2().getData().getClass()
                    );
                }
            }
        }

        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("NodeC:Start");
                    while(true) {
                        Collection<Pair<ServiceType, MessageData>> collection =
                                agent.takeMultiple(10);
                        pool.submit(new CTask(collection));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static public void main(String... args){
        NodesOnBusVer2_1 test = new NodesOnBusVer2_1();
        ExecutorService pool = Executors.newCachedThreadPool();
        Bus bus = Bus.getInstance();

        NodeA a = test.new NodeA(pool);
        NodeB b = test.new NodeB(pool);
        NodeC c = test.new NodeC(pool);
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

        for(int i=0;i<10;i++)
            a.agent.submit(new SubscribePublishMessage<>("A dada", new ServiceType("B")));
        for(int i=0;i<20;i++)
            b.agent.submit(new SubscribePublishMessage<>("B dada",new ServiceType("A")));
        for(int i=0;i<5;i++)
            c.agent.submit(new SubscribePublishMessage<>("C dada",new ServiceType("B")));

        bus.start();
    }
}
