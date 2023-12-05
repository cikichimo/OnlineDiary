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
            String query = "select uid from users where uname = '%s' and pass = '%s' ";
            query = String.format(query,
                    name,
                    passwd);
            st = con.prepareStatement(query);
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
            String query = "select * from users where uid = '%s'";
            query = String.format(query, uid);

            st = con.prepareStatement(query);
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
                    + " values ('%s', '%s', '%s') ";
            query = String.format(query,
                    u.getUid(),
                    u.getUname(),
                    u.getPass());
            st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }

    }
}
