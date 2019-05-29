package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port){
        this.port=port;
    }

    public void start() throws Exception{
        final EchoServerHandler serverHandler=new EchoServerHandler();
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).
                    localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                    socketChannel.pipeline().addLast(serverHandler);
                }
            });
            ChannelFuture channelFuture=serverBootstrap.bind().sync(); //异步地绑定服务器，调用sync()方法阻塞等待直到绑定完成
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws Exception{
        if(args.length!=1){
            System.out.println("Usage: "+EchoServer.class.getSimpleName()+ "<port>");
        }
        new EchoServer(Integer.parseInt(args[0])).start();
    }
}
