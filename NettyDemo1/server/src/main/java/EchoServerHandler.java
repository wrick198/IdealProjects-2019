import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //对每一个传入的消息都会调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in=(ByteBuf)msg;
        System.out.println("server received message from client: "+ in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
    }

    //通知channelInboundHandler最后一次对channelRead()的调用是当前批量读取中的最后一条消息
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);  //将消息冲刷到远程结点，并且关闭该channel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
