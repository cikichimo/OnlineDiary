package model;

import java.util.UUID;

/**
 *
 * @author AERO
 */
public class User {
    private UUID uid ;
    private String uname;
    private String pass;

    public User(String uname, String pass) {
        this.setPass(pass);
        this.setUname(uname);
        UUID uuid = UUID.randomUUID();
        this.setUid(uuid);
    }
    
    public User(UUID uid, String uname, String pass) {
        this.setPass(pass);
        this.setUname(uname);
        this.setUid(uid);
    }

    
    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
     
}
