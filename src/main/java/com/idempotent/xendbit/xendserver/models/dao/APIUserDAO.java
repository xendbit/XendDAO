package com.idempotent.xendbit.xendserver.models.dao;

import com.idempotent.xendbit.xendserver.models.APIUser;
import com.idempotent.xendbit.xendserver.api.utils.WalletKitHelper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIUserDAO {
    private static final Logger LOGGER = Logger.getLogger(APIUserDAO.class.getName());

    private static final Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        String conUrl = WalletKitHelper.p.getProperty("db.url");
        String username = WalletKitHelper.p.getProperty("db.username");
        String password = WalletKitHelper.p.getProperty("db.password");
        return DriverManager.getConnection(conUrl, username, password);
    }

    public static final APIUser getAPIUser(String apiKey) {
        String query = "SELECT * FROM API_USER WHERE API_KEY = ?";
        try(Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, apiKey);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                APIUser apiUser = new APIUser();
                apiUser.setApiKey(apiKey);
                apiUser.setId(rs.getInt("ID"));
                return apiUser;
            }
        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return null;
    }
}
