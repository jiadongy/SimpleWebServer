package busmode.socket.ver1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Feiyu on 2015/6/30 0030.
 **/
public class SyncUnblockingSocketManager extends SocketManager {

    private Selector selector;

    private Collection<ServerSocketChannel> servers = new LinkedList<>();
    private Collection<SocketChannel> sockets = new LinkedList<>();
    private ServerSocketChannel server;


    public SyncUnblockingSocketManager() throws IOException {
        super();
        this.selector = Selector.open();
        server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        server.bind(bindAddress);
    }

    public void startListening(int connections) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        server.bind(bindAddress);
    }

    public void processAccept(SelectionKey selectionKey) throws IOException {
        SocketChannel socket = server.accept();
        socket.configureBlocking(false);
        socket.register(selector, SelectionKey.OP_READ);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
    }

    private void processRead(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (channel.read(buffer) > 0) {
                buffer.flip();
                stringBuilder.append(charset.decode(buffer));
            }
        } catch (IOException e) {
            selectionKey.cancel();
            if (selectionKey.channel() != null)
                selectionKey.channel().close();

            e.printStackTrace();
        }
        String result = stringBuilder.toString();

    }

    public void start() {
        new Thread(processMain).start();
    }

    Runnable processMain = new Runnable() {
        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    for (SelectionKey selectionKey : selector.selectedKeys()) {
                        selector.selectedKeys().remove(selectionKey);

                        if (selectionKey.isAcceptable())
                            processAccept(selectionKey);
                        if (selectionKey.isReadable())
                            processRead(selectionKey);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}
