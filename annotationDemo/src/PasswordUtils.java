import java.util.List;

public class PasswordUtils {

    @UserCase(id=47,description ="password must contain at least one numeric ")
    public boolean validatePassword(String password){
        return (password.matches("\\w*\\d\\w*"));
    }

    @UserCase(id=48)
    public String encryptionPassword(String password){
        return  new StringBuilder(password).reverse().toString();
    }

    @UserCase(id=49,description="new password cannt equal previously used ones")
    public boolean checkForNewPassword(List<String> prePasswordList,String password){
        return !prePasswordList.contains(password);
    }

}
