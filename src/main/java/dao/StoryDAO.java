package dao;
import model.Story;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.UUID;
import model.User;

/**
 *
 * @author AERO
 */

public class StoryDAO extends BaseDAO {

    private static PreparedStatement st;
    private static Connection con;
    
    public static void insertEntry(Story s) {
        
        try {
            
            String query = "insert into stories ( sid, uid , sdate , descrip ) "
                    + " values (?,?,?,?)";
            st = con.prepareStatement(query);
            
            st.setString(1, s.getSid().toString());
            st.setString(2, s.getOwner().getUid().toString());
            st.setDate(3, (Date) s.getSdate());
            st.setString(4, s.getDescrip());
            
            con = getCon();
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
    }

    public static void updateEntry(Story s, Story old) {
        try {
            con = getCon();
            String query = " update stories set sdate = ?, descrip = ?"
                    + " where uid like ? and sdate = ? and descrip like ? ";
            st = con.prepareStatement(query);
            
            st.setDate(1, (Date) s.getSdate());   // Angka 1: untuk sdate yang baru
            st.setString(2, s.getDescrip()); // Angka 2: untuk descrip yang baru

            // WHERE values (kondisi data yang lama)
            st.setString(3, old.getOwner().getUid().toString()); // Angka 3: untuk uid lama
            st.setDate(4, (Date) old.getSdate());  // Angka 4: untuk sdate lama
            st.setString(5, old.getDescrip()); // Angka 5: untuk descrip lama
            
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
    }

    public static LinkedList<Story> getAll(User u) {
        LinkedList<Story> res = new LinkedList<>();
        try {
            con = getCon();
            String query = "select * from stories where uid = ?";

            st = con.prepareStatement(query);
            st.setString(1, u.getUid().toString());
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Story s = new Story(UUID.fromString(rs.getString("sid")),
                            rs.getDate("sdate"),
                            rs.getString("descrip"),
                            u);
                res.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return res;
    }


    public static void removeEntry(Story s) {
        try {
            con = getCon(); 
            String query = "delete from stories"
                    + " where uid like ? and descrip like ? ";
            st = con.prepareStatement(query);
            
            st.setString(1, s.getOwner().getUid().toString());
            st.setString(2,  s.getDescrip());
            
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        
    }

    public static LocalDate getDate(UUID uid, String desc) {
        LocalDate res = null;
        try {
            con = getCon(); 
            String query = "select sdate from stories "
                + " where uid like ? and descrip like ?";
            st = con.prepareStatement(query);
            st.setString(1, uid.toString());
            st.setString(2,  desc);
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                res = rs.getDate("sdate").toLocalDate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return res;
    }

}
