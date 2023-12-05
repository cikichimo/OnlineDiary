package model;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author AERO
 */

public class Story {
    private UUID sid;
    private Date sdate;
    private String descrip;
    private User owner;

    public Story(Date date, String descrip, User owner) {
        this.setDescrip(descrip);
        this.setOwner(owner);
        UUID uuid = UUID.randomUUID();
        this.setSid(uuid);
        this.setSdate(date); 
    }
    
    public Story(UUID sid, Date sdate, String descrip, User owner) {
        this.setDescrip(descrip);
        this.setOwner(owner);
        this.setSid(sid);
        this.setSdate(sdate);
    }

    public Story() {
    }
    
    
    public UUID getSid() {
        return sid;
    }

    public void setSid(UUID sid) {
        this.sid = sid;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    
}
