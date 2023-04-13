package Persistence;

public class Administrator {
    private String admin_id;
    private String password;

    public Administrator(String admin_id, String password){
        this.admin_id = admin_id;
        this.password = password;
    }

    public String getAdminID() {
        return admin_id;
    }

    public String getPassword() {
        return password;
    }
}
