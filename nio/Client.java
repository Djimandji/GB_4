package nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {

    ByteBuffer buffer;


    public static void main(String[] args) {

        new Client().start();

    }

        public void start () {
            System.out.println("New client started on thread " + Thread.currentThread().getName());
            Scanner sc = new Scanner(System.in);
            buffer = ByteBuffer.allocate(256);
            try {
                SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 9000));
                while (true) {
                    channel.write(buffer.wrap(String.format(
                            sc.next(),
                            LocalDateTime.now(),
                            Thread.currentThread().getName()
                    ).getBytes()));
                    buffer.clear();
                    channel.read(buffer);
                    System.out.println(new String(buffer.array()).trim());
                    buffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
