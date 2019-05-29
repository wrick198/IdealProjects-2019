import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/web/chat")
public class CharEntpoint {
    private static final String GUEST_PREFIX = "访客";
    private static final AtomicInteger connectionids = new AtomicInteger(0);
    private static final Set<CharEntpoint> clientset = new CopyOnWriteArraySet<CharEntpoint>();
    private String nickname;
    private Session session;

    public CharEntpoint() {
        nickname = GUEST_PREFIX + connectionids.getAndIncrement();
    }

    @OnOpen
    public void start(Session session) {
        this.session = session;
        clientset.add(this);
        String message = String.format("[%s,%s]", nickname, "加入聊天室！");
        broadCast(message);
    }

    @OnClose
    public void end() {
        clientset.remove(this);
        String message = String.format("[%s,%s]", nickname, "离开聊天室！");
        broadCast(message);
    }

    @OnMessage
    public void incoming(String message) {
        String filterMessage = String.format("[%s,%s]", nickname, filter(message));
        broadCast(filterMessage);
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        System.out.println("websocket 服务端错误" + t);
    }

    private static void broadCast(String msg) {
        for (CharEntpoint client : clientset) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                System.out.println("聊天错误，向客户端" + client + "发送消息出现错误！");
                clientset.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String message = String.format("[%s,%s]", client.nickname, "因为出现错误，离开聊天室！");
                broadCast(message);
            }
        }
    }

    private static String filter(String message) {
        if (message == null)
            return null;
        char[] content = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt");
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return (result.toString());
    }
}
