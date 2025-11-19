package dao;

import static dao.BaseDAO.closeCon;
import static dao.BaseDAO.getCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import model.User;

/**
 *
 * @author AERO
 */
public class UserDAO {

    private static PreparedStatement st;
    private static Connection con;

    public static User validate(String name, String passwd) {
        User u = null;
        try {
            con = getCon();
            String query = "select uid from users where uname = ? and pass = ?";
            st = con.prepareStatement(query);
            
            st.setString(1, name);
            st.setString(2, passwd);
            
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                u = new User(UUID.fromString(rs.getString("uid")), name, passwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return u;
    }

    public static User searchByUid(String uid) {
        User u = null;
        try {
            con = getCon();
            String query = "select * from users where uid = ?";

            st = con.prepareStatement(query);
            st.setString(1, uid);
            
            ResultSet rsUser = st.executeQuery();
            u = new User(UUID.fromString(rsUser.getString("uid")),
                    rsUser.getString("uname"), rsUser.getString("pass"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return u;
    }

    public static void registerUser(User u) {
        try {
            con = getCon();
            String query = "insert into users"
                    + " values (?,?,?) ";
            st = con.prepareStatement(query);
            
            st.setString(1, u.getUid().toString());
            st.setString(2, u.getUname());
            st.setString(3, u.getPass());
            
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }

    }
}
