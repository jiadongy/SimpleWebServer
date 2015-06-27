package examples.message;

import busmode.message.ver1.BusAgent;
import busmode.message.ver1.IService;
import busmode.message.ver1.base.Message;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public class NodesOnBus  {

    private class NodeA implements IService{
        private BusAgent agent = new BusAgent(this);

        @Override
        public void getMessage(Message message) {
            System.out.println("A receive: "+message.getData());
        }

    }
    private class NodeB implements IService{
        private BusAgent agent = new BusAgent(this);

        @Override
        public void getMessage(Message message) {
            System.out.println("B receive: "+message.getData());
        }

    }
    private class NodeC implements IService{
        private BusAgent agent = new BusAgent(this);

        @Override
        public void getMessage(Message message) {
            System.out.println("C receive: "+message.getData());
        }

    }
    static public void main(String... args){
        NodesOnBus test = new NodesOnBus();
        NodeA a = test.new NodeA();
        NodeB b = test.new NodeB();
        NodeC c = test.new NodeC();

        a.agent.register("A","B","C");
        b.agent.register("B","C");
        c.agent.register("C","A");

        a.agent.send("A dada", "B");
        b.agent.send("B dada","A");
        c.agent.send("C dada","B","C");

    }

}
