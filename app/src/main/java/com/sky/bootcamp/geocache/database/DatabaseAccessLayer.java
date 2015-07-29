package com.sky.bootcamp.geocache.database;

import com.sky.bootcamp.geocache.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 * Created by mgh01 on 27/07/2015.
 */
public class DatabaseAccessLayer {

    private static Connection conn = Database.GetConnection();

    public static User getUserFromEmail(String email) throws SQLException {
        User user = null;

        Connection con = Database.GetConnection();
        String queryString = "SELECT * FROM auth_user INNER JOIN profiles_person ON profiles_person.user_id = auth_user.id WHERE auth_user.email = ? LIMIT 1";
        PreparedStatement ps = con.prepareStatement(queryString);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String password = rs.getString("password");
            Timestamp lastLogin = rs.getTimestamp("last_login");
            String role = rs.getString("role");
            user = new User(email, password, lastLogin, role);
        }

        return user;
    }

}
