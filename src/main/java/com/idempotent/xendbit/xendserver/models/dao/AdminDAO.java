package com.idempotent.xendbit.xendserver.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;

import com.idempotent.xendbit.xendserver.models.Admin;
import com.idempotent.xendbit.xendserver.api.utils.WalletKitHelper;

public class AdminDAO {
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

    public static List<Admin> findAllAdmins() throws Exception {
        String query = "SELECT * FROM XB_ADMIN";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            

            List<Admin> admins = new ArrayList<>();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setPermissionId(rs.getLong("PERMISSIONS_ID"));
                admin.setUsername(rs.getString("USERNAME"));
                admin.setPermissions(PermissionsDAO.findByPermissionId(admin.getPermissionId()));
                admins.add(admin);
            }

            return admins;
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw e;
        }   
    }

    public static final Admin findByUsernameAndPassword(String username, String password) throws Exception {
        String query = "SELECT * FROM XB_ADMIN WHERE USERNAME = ? AND PASSWORD = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, DigestUtils.md5Hex(password));
            ResultSet rs = ps.executeQuery();            

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setPermissionId(rs.getLong("PERMISSIONS_ID"));
                admin.setPassword(rs.getString("PASSWORD"));
                admin.setPermissions(PermissionsDAO.findByPermissionId(admin.getPermissionId()));
                return admin;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw e;
        }

        return null;
    }

    public static final boolean createAdmin(Admin admin) throws Exception {
        String query = "INSERT INTO XB_ADMIN (USERNAME, PASSWORD) VALUES (?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, admin.getUsername());
            ps.setString(2, DigestUtils.md5Hex(admin.getPassword()));

            ps.execute();
            long permissionId = PermissionsDAO.addPermissions(admin.getPermissions());
            query = "UPDATE XB_ADMIN SET PERMISSIONS_ID = ? WHERE USERNAME = ? AND PASSWORD = ?";
            try (Connection con1 = getConnection(); PreparedStatement ps1 = con1.prepareStatement(query)) {
                ps1.setLong(1, permissionId);
                ps1.setString(2, admin.getUsername());
                ps1.setString(3, DigestUtils.md5Hex(admin.getPassword()));

                return ps1.execute();
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                LOGGER.log(Level.SEVERE, null, e);
                throw e;
            }

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw e;
        }
    }
}