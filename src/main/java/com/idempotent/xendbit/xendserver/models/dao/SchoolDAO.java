package com.idempotent.xendbit.xendserver.models.dao;

import com.idempotent.xendbit.xendserver.api.utils.WalletKitHelper;
import com.idempotent.xendbit.xendserver.models.School;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchoolDAO {
    private static final Logger LOGGER = Logger.getLogger(SchoolDAO.class.getName());

    private static final Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        String conUrl = WalletKitHelper.p.getProperty("db.url");
        String username = WalletKitHelper.p.getProperty("db.username");
        String password = WalletKitHelper.p.getProperty("db.password");
        return DriverManager.getConnection(conUrl, username, password);
    }

    public static List<School> getSchools() {
        List<School> schools = new ArrayList<>();

        String query = "SELECT * FROM XB_SCHOOL";
        try(Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                School school = new School();
                school.setId(rs.getLong("ID"));
                school.setName(rs.getString("NAME"));
                school.setDomain(rs.getString("DOMAIN"));
                schools.add(school);
            }
            return schools;
        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return null;
    }
}
