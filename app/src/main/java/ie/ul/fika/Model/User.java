package ie.ul.fika.Model;

public class User {
    private String username;
    private String fullName;
    private String email;


    public User(){
    }
    public User(String email, String fullName, String username) {
        this.email = email;
        this.fullName = fullName;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
