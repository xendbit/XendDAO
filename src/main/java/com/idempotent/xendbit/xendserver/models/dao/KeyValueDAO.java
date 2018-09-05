package com.idempotent.xendbit.xendserver.models.dao;

import com.idempotent.xendbit.xendserver.api.utils.WalletKitHelper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyValueDAO {

    private static final Logger LOGGER = Logger.getLogger(KeyValueDAO.class.getName());
    public static final String KEY_VALUE_TABLENAME = "XB_KEY_VALUE";
    public static final String KEY_VALUE_COLUMNS = "KEY, VALUE";

    private static Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        String conUrl = WalletKitHelper.p.getProperty("db.url");
        String username = WalletKitHelper.p.getProperty("db.username");
        String password = WalletKitHelper.p.getProperty("db.password");
        return DriverManager.getConnection(conUrl, username, password);
    }

    public static final void storeValue(String key, String value) {
        String query = "INSERT INTO "
                + KEY_VALUE_TABLENAME
                + " (" + KEY_VALUE_COLUMNS + ")"
                + " VALUE (?, ?)";
        LOGGER.info(query);
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, key);
            ps.setString(2, value);
            ps.execute();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public static final String getValue(String key) {
        String query = "SELECT "
                + KEY_VALUE_COLUMNS
                + " FROM "
                + KEY_VALUE_TABLENAME
                + " WHERE KEY = ?";
        LOGGER.info(query);
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, key);
            ResultSet rst = ps.executeQuery();
            if (rst.next()) {
                return rst.getString("VALUE");
            }
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return null;
    }
}
