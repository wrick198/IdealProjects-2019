import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserCaseTracker {

    public static void  trackUser(List<Integer> userlist,Class<?> c1){
        for(Method method:c1.getDeclaredMethods()){
            UserCase user=method.getAnnotation(UserCase.class);
            if(user!=null){
                System.out.println("find user:"+user.id()+", description:"+user.description());
                userlist.remove( Integer.valueOf(user.id()));
            }
        }

        for(int i:userlist)
            System.out.println("Warning:Missing user："+i);
    }

    public static void main(String[] args) {
        List<Integer> userlist=new ArrayList<>();
        Collections.addAll(userlist,47,48,49,50);
        trackUser(userlist,PasswordUtils.class);
    }
}
