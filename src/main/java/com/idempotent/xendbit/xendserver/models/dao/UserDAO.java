/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idempotent.xendbit.xendserver.models.dao;

import com.idempotent.xendbit.xendserver.models.User;
import com.idempotent.xendbit.xendserver.api.utils.WalletKitHelper;
import static com.idempotent.xendbit.xendserver.api.utils.WalletKitHelper.p;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author aardvocate
 */
public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    private static Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        String conUrl = WalletKitHelper.p.getProperty("db.url");
        String username = WalletKitHelper.p.getProperty("db.username");
        String password = WalletKitHelper.p.getProperty("db.password");
        return DriverManager.getConnection(conUrl, username, password);
    }

    public static void setKycSynced(User u) {
        try (Connection sqlConnection = getConnection()) {
            sqlConnection.createStatement().execute("UPDATE XB_ACCOUNT_KYC SET SYNCED = true WHERE USER_ID = " + u.getId());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static User.AccountKYC getAccountKYC(User u) throws SQLException {
        String sql = "SELECT * FROM XB_ACCOUNT_KYC WHERE USER_ID = " + u.getId() + " AND SYNCED = false";
        try (Connection sqlConnection = getConnection(); ResultSet rst = sqlConnection.createStatement().executeQuery(sql)) {
            if(rst.next()) {
                User.AccountKYC accountKYC = new User.AccountKYC();
                accountKYC.setBankAccountName(rst.getString("BANK_ACCOUNT_NAME"));
                accountKYC.setBankAccountNumber(rst.getString("BANK_ACCOUNT_NUMBER"));
                accountKYC.setBankCode(rst.getString("BANK_CODE"));
                accountKYC.setBankName(rst.getString("BANK_NAME"));
                accountKYC.setCountryOfResidence(rst.getString("COUNTRY_OF_RESIDENCE"));
                accountKYC.setEmail(rst.getString("EMAIL"));
                accountKYC.setFirstName(rst.getString("FIRSTNAME"));
                accountKYC.setIdNumber(rst.getString("ID_NUMBER"));
                accountKYC.setIdType(rst.getString("ID_TYPE"));
                accountKYC.setPhoneNumber(rst.getString("PHONE_NUMBER"));
                accountKYC.setProofOfIdentity(rst.getString("PROOF_OF_IDENTITY"));
                accountKYC.setSurName(rst.getString("SURNAME"));
                accountKYC.setMiddleName(rst.getString("MIDDLENAME"));
                accountKYC.setDateRegistered(rst.getLong("DATE_REGISTERED"));                                
                
                return accountKYC;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static final void addAccountKYC(User u) throws IOException, SQLException {
        //first store the image somewhere the blockchain can access it.
        String filename = DigestUtils.md5Hex(u.getEmailAddress().getBytes());
        File f = new File(p.getProperty("blockchain.pictures.location"), filename);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(u.getIdImage().getBytes());
        fos.flush();

        String sql = "INSERT INTO XB_ACCOUNT_KYC "
                + "(SURNAME, FIRSTNAME, MIDDLENAME, EMAIL, PHONE_NUMBER, ID_TYPE, ID_NUMBER, "
                + "PROOF_OF_IDENTITY, BANK_CODE, BANK_NAME, BANK_ACCOUNT_NAME, BANK_ACCOUNT_NUMBER, "
                + "COUNTRY_OF_RESIDENCE, DATE_REGISTERED, NETWORK_ADDRESS, USER_ID) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection sqlConnection = getConnection(); PreparedStatement ps = sqlConnection.prepareStatement(sql)) {
            int i = 0;
            ps.setString(++i, u.getSurName());
            ps.setString(++i, u.getFirstName());
            ps.setString(++i, u.getMiddleName());
            ps.setString(++i, u.getEmailAddress());
            ps.setString(++i, u.getPhoneNumber());
            ps.setString(++i, u.getIdType());
            ps.setString(++i, u.getIdNumber());
            ps.setString(++i, f.getName());
            ps.setString(++i, u.getBankCode());
            ps.setString(++i, u.getBankName());
            ps.setString(++i, u.getAccountName());
            ps.setString(++i, u.getAccountNumber());
            ps.setString(++i, u.getCountry());
            ps.setLong(++i, u.getDateRegistered());
            ps.setString(++i, u.getNetworkAddress());
            ps.setLong(++i, u.getId());
            
            ps.execute();                        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final void upgradeUser(User u) throws SQLException {
        try (Connection sqlConnection = getConnection()) {

            u.setFullname(u.getFirstName() + " " + u.getSurName() + " " + u.getMiddleName());
            sqlConnection.createStatement().executeUpdate("DELETE FROM XB_USER WHERE EMAIL = '" + u.getEmailAddress() + "'");

            String query = "INSERT INTO XB_USER "
                    + "(EMAIL, PASSWORD, ACCOUNT_TYPE, IS_APPROVED, IS_ACTIVATED, WALLET_TYPE, "
                    + "DATE_REGISTERED, IS_BENEFICIARY, FULL_NAME, XEND_NETWORK_ADDRESS) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            LOGGER.info(query);
            int i = 0;
            PreparedStatement ps = sqlConnection.prepareStatement(query);
            ps.setString(++i, u.getEmailAddress());
            ps.setString(++i, u.getPassword());
            ps.setString(++i, u.getAccountType());
            ps.setBoolean(++i, true);
            ps.setBoolean(++i, true);
            ps.setString(++i, u.getWalletType());
            ps.setLong(++i, u.getDateRegistered());
            ps.setBoolean(++i, u.isBeneficiary());
            ps.setString(++i, u.getFullname());
            ps.setString(++i, u.getXendNetworkAddress());

            ps.execute();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final void addNewUser(User u) throws SQLException {
        String query = "INSERT INTO XB_USER "
                + "(EMAIL, PASSWORD, ACCOUNT_TYPE, IS_APPROVED, IS_ACTIVATED, WALLET_TYPE, "
                + "DATE_REGISTERED, IS_BENEFICIARY, FULL_NAME, XEND_NETWORK_ADDRESS) "
                + "VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        LOGGER.info(query);
        try (Connection sqlConnection = getConnection(); PreparedStatement ps = sqlConnection.prepareStatement(query)) {
            u.setFullname(u.getFirstName() + " " + u.getSurName() + " " + u.getMiddleName());
            int i = 0;
            ps.setString(++i, u.getEmailAddress());
            ps.setString(++i, u.getPassword());
            ps.setString(++i, u.getAccountType());
            ps.setBoolean(++i, true);
            ps.setBoolean(++i, true);
            ps.setString(++i, u.getWalletType());
            ps.setLong(++i, u.getDateRegistered());
            ps.setBoolean(++i, u.isBeneficiary());
            ps.setString(++i, u.getFullname());
            ps.setString(++i, u.getXendNetworkAddress());
            ps.execute();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final User login(User user) throws SQLException {
        try (Connection sqlConnection = getConnection()) {

            String query = "SELECT * FROM XB_USER WHERE EMAIL = ? AND PASSWORD = ?";
            PreparedStatement ps = sqlConnection.prepareStatement(query);
            ps.setString(1, user.getEmailAddress());
            ps.setString(2, DigestUtils.md5Hex(user.getPassword()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                populateUser(rs, u);
                return u;
            } else {
                return null;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static void populateUser(ResultSet rs, User u) throws SQLException {
        u.setId(rs.getLong("ID"));
        u.setEmailAddress(rs.getString("EMAIL"));
        u.setPassword(rs.getString("PASSWORD"));
        u.setApproved(rs.getBoolean("IS_APPROVED"));
        u.setActivated(rs.getBoolean("IS_ACTIVATED"));
        u.setWalletType(rs.getString("WALLET_TYPE"));
        u.setDateRegistered(rs.getLong("DATE_REGISTERED"));
        u.setBeneficiary(rs.getBoolean("IS_BENEFICIARY"));
        u.setFullname(rs.getString("FULL_NAME"));
        u.setAccountType(rs.getString("ACCOUNT_TYPE"));
        u.setXendNetworkAddress(rs.getString("XEND_NETWORK_ADDRESS"));
    }

    public static User findByColumn(String columnName, String columnValue) throws SQLException {
        String query = "SELECT * FROM XB_USER WHERE " + columnName + " = ?";
        try (Connection sqlConnection = getConnection(); PreparedStatement ps = sqlConnection.prepareStatement(query)) {
            LOGGER.info(query);
            ps.setString(1, columnValue);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                populateUser(rs, u);
                return u;
            } else {
                return null;
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static final List<User> getBeneficiaries(User user) {
        List<User> response = new ArrayList<>();
        String query = "SELECT * FROM XB_USER WHERE IS_BENEFICIARY = ? AND ID != ? AND IS_ACTIVATED = ? AND IS_APPROVED = ?";
        try (Connection sqlConnection = getConnection(); PreparedStatement ps = sqlConnection.prepareStatement(query)) {
            ps.setBoolean(1, true);
            ps.setLong(2, user.getId());
            ps.setBoolean(3, true);
            ps.setBoolean(4, true);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                User u = new User();
                populateUser(rst, u);
                response.add(u);
            }
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public static final List<User> findAll() {
        String query = "SELECT * FROM XB_USER";

        return getUsersFromQuery(query);
    }

    public static final List<User> findByPage(String page) {
        int start = Integer.parseInt(page) * 100;
        int limit = start + 100;
        String query = "SELECT * FROM XB_USER LIMIT " + start + ", " + limit;

        return getUsersFromQuery(query);
    }

    private static List<User> getUsersFromQuery(String query) {
        List<User> users = new ArrayList<>();
        try (Connection con = getConnection()) {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                User u = new User();
                populateUser(rs, u);
                users.add(u);
            }

            return users;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static User findByEmailMD5(String emailMD5) throws SQLException {
        String query = "SELECT * FROM XB_USER WHERE MD5(EMAIL) = ?";
        try (Connection sqlConnection = getConnection(); PreparedStatement ps = sqlConnection.prepareStatement(query)) {
            LOGGER.info(query);
            ps.setString(1, emailMD5);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                populateUser(rs, u);
                return u;
            } else {
                return null;
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static User findById(String columnName, String columnValue) throws SQLException {
        String query = "SELECT * FROM XB_USER WHERE " + columnName + " = ?";
        try (Connection sqlConnection = getConnection(); PreparedStatement ps = sqlConnection.prepareStatement(query)) {
            LOGGER.info(query);
            ps.setString(1, columnValue);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                populateUser(rs, u);
                return u;
            } else {
                return null;
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void activateAccount(User user) throws SQLException {
        String query = "UPDATE XB_USER SET IS_ACTIVATED = ? WHERE ID = ?";
        try (Connection sqlConnection = getConnection(); PreparedStatement ps = sqlConnection.prepareStatement(query)) {
            LOGGER.info(query);
            ps.setBoolean(1, true);
            ps.setLong(2, user.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void becomeBeneficiary(User dbUser) throws SQLException {
        String query = "UPDATE XB_USER SET IS_BENEFICIARY = ? WHERE ID = ?";
        try (Connection sqlConnection = getConnection(); PreparedStatement ps = sqlConnection.prepareStatement(query)) {
            LOGGER.info(query);
            ps.setBoolean(1, true);
            ps.setLong(2, dbUser.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
