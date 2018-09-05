package com.idempotent.xendbit.xendserver.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.idempotent.xendbit.xendserver.api.utils.WalletKitHelper;

public class PermissionsDAO {
    private static final Logger LOGGER = Logger.getLogger(APIUserDAO.class.getName());

    private static final Connection getConnection()
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        String conUrl = WalletKitHelper.p.getProperty("db.url");
        String username = WalletKitHelper.p.getProperty("db.username");
        String password = WalletKitHelper.p.getProperty("db.password");
        return DriverManager.getConnection(conUrl, username, password);
    }

    public static final List<String> findByPermissionId(long permissionId) {
        List<String> permissions = null;
        String query = "SELECT * FROM XB_PERMISSIONS WHERE PERMISSION_ID = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, permissionId);
            ResultSet rs = ps.executeQuery();

            permissions = new ArrayList<>();
            while (rs.next()) {
                permissions.add(rs.getString("PERMISSION_NAME"));
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return permissions;
    }

    public static final long addPermissions(List<String> permissions) {
        String query = "INSERT INTO XB_PERMISSIONS(PERMISSION_ID, PERMISSION_NAME) VALUES (?, ?) ";
        long permissionId = new Random(System.currentTimeMillis()).nextLong();

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            for (String permission : permissions) {
                ps.setLong(1, permissionId);
                ps.setString(2, permission);
                ps.execute();
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return permissionId;
    }
}