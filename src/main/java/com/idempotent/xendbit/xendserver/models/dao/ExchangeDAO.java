package com.idempotent.xendbit.xendserver.models.dao;

import com.idempotent.xendbit.xendserver.api.utils.WalletKitHelper;
import com.idempotent.xendbit.xendserver.models.Exchange;
import com.idempotent.xendbit.xendserver.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeDAO {
    private static final Logger LOGGER = Logger.getLogger(ExchangeDAO.class.getName());

    public static boolean updateTradeStatus(String id, String status) {
        String query = "UPDATE XB_EXCHANGE SET STATUS = ? WHERE TRX_ID = ?";
        LOGGER.info(query);
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setString(2, id);
            ps.execute();
            return true;
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return false;
    }

    public static boolean ownerCancelled(String id, String status) {
        String query = "UPDATE XB_EXCHANGE SET STATUS = ?, ACTIVE = ? WHERE TRX_ID = ?";
        LOGGER.info(query);
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setBoolean(2, false);
            ps.setString(3, id);
            ps.execute();
            return true;
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return false;
    }

    public enum STATUS {
        ORDER_PLACED, SUCCESS, OWNER_CANCELED, SELLER_SENT_COINS, BUYER_SENDING_ERROR, SELLER_INTERESTED, BUYER_PAID
    }

    ;

    private static Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        String conUrl = WalletKitHelper.p.getProperty("db.url");
        String username = WalletKitHelper.p.getProperty("db.username");
        String password = WalletKitHelper.p.getProperty("db.password");
        return DriverManager.getConnection(conUrl, username, password);
    }

    private static Exchange tradeFromResultSet(ResultSet rst) throws SQLException {
        Exchange exchange = new Exchange();
        exchange.setId(rst.getLong("ID"));
        exchange.setAmountToSell(rst.getDouble("AMOUNT_TO_SELL"));
        exchange.setToCoin(rst.getString("TO_COIN"));
        exchange.setFromCoin(rst.getString("FROM_COIN"));
        exchange.setRate(rst.getDouble("RATE"));
        exchange.setAmountToRecieve(rst.getDouble("AMOUNT_TO_RECIEVE"));
        exchange.setSellerId(rst.getLong("SELLER_ID"));
        exchange.setBuyerId(rst.getLong("BUYER_ID"));
        exchange.setStatus(rst.getString("STATUS"));
        exchange.setBuyerFromAddress(rst.getString("BUYER_FROM_ADDRESS"));
        exchange.setBuyerToAddress(rst.getString("BUYER_TO_ADDRESS"));
        exchange.setSellerFromAddress(rst.getString("SELLER_FROM_ADDRESS"));
        exchange.setSellerToAddress(rst.getString("SELLER_TO_ADDRESS"));
        exchange.setDatetime(rst.getDate("DATETIME"));
        exchange.setTrxDate(rst.getDate("TRX_DATE"));
        exchange.setActive(rst.getBoolean("ACTIVE"));
        exchange.setTrxId(rst.getString("TRX_ID"));

        return exchange;
    }

    private static List<Exchange> tradesFromDB(ResultSet rst) throws SQLException {
        List<Exchange> result = new ArrayList<>();
        while (rst.next()) {
            Exchange exchange = tradeFromResultSet(rst);
            result.add(exchange);
        }

        return result;
    }

    public static List<Exchange> getDebts(User user) {
        String query;
        if(user.getWalletCode() == null) {
            query = "SELECT * FROM XB_EXCHANGE WHERE (STATUS = ? || STATUS = ?) AND BUYER_ID = ?";
        } else {
            query = "SELECT * FROM XB_EXCHANGE WHERE (STATUS = ? || STATUS = ?) AND BUYER_ID = ? AND FROM_COIN = ?";
        }

        LOGGER.info(query);
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, STATUS.SELLER_SENT_COINS.toString());
            ps.setString(2, STATUS.BUYER_SENDING_ERROR.toString());
            ps.setLong(3, user.getId());
            if(user.getWalletCode() != null) {
                ps.setString(4, user.getWalletCode());
            }
            ResultSet rst = ps.executeQuery();

            return tradesFromDB(rst);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return null;
    }

    public static List<Exchange> getTrades(User user) {
        String query = "SELECT * FROM XB_EXCHANGE WHERE ACTIVE = ? AND FROM_COIN = ? AND STATUS = ?";
        LOGGER.info(query + " - " + user.getId() + " - " + user.getWalletCode());
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setBoolean(1, true);
            ps.setString(2, user.getWalletCode());
            ps.setString(3, STATUS.ORDER_PLACED.toString());
            ResultSet rst = ps.executeQuery();

            return tradesFromDB(rst);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return null;
    }

    public static List<Exchange> getUserTrades(User user) {
        String query = "SELECT * FROM XB_EXCHANGE WHERE ACTIVE = ? AND SELLER_ID = ? AND FROM_COIN = ?";
        LOGGER.info(query + " - " + user.getId() + " - " + user.getWalletCode());
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setBoolean(1, true);
            ps.setLong(2, user.getId());
            ps.setString(3, user.getWalletCode());
            ResultSet rst = ps.executeQuery();

            return tradesFromDB(rst);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return null;
    }

    public static List<Exchange> getEscrowUserTrades(User user) {
        String query = "SELECT * FROM XB_EXCHANGE WHERE ACTIVE = ? AND SELLER_ID = ? " +
                "AND FROM_COIN = ? AND STATUS IN (?)";
        LOGGER.info(query + " - " + user.getId() + " - " + user.getWalletCode());
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setBoolean(1, true);
            ps.setLong(2, user.getId());
            ps.setString(3, user.getWalletCode());
            ps.setString(4, STATUS.ORDER_PLACED.toString());
            ResultSet rst = ps.executeQuery();

            return tradesFromDB(rst);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return null;
    }

    public static List<Exchange> getPendingTrades(User user) {
        String query = "SELECT * FROM XB_EXCHANGE WHERE STATUS = ? AND SELLER_ID = ? AND TRX_DATE IS NOT NULL";

        LOGGER.info(query);
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, STATUS.SELLER_INTERESTED.toString());
            ps.setLong(2, user.getId());
            ResultSet rst = ps.executeQuery();

            return tradesFromDB(rst);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return null;
    }

    public static boolean addTrade(Exchange exchange) {
        String query = "INSERT INTO XB_EXCHANGE " +
                "(" +
                " AMOUNT_TO_SELL," +
                " TO_COIN," +
                " FROM_COIN," +
                " RATE," +
                " AMOUNT_TO_RECIEVE," +
                " SELLER_ID," +
                " STATUS," +
                " SELLER_FROM_ADDRESS," +
                " SELLER_TO_ADDRESS," +
                " ACTIVE," +
                " TRX_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        LOGGER.info(query);
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDouble(1, exchange.getAmountToSell());
            ps.setString(2, exchange.getToCoin());
            ps.setString(3, exchange.getFromCoin());
            ps.setDouble(4, exchange.getRate());
            ps.setDouble(5, exchange.getAmountToRecieve());
            ps.setLong(6, exchange.getSellerId());
            ps.setString(7, exchange.getStatus());
            ps.setString(8, exchange.getSellerFromAddress());
            ps.setString(9, exchange.getSellerToAddress());
            ps.setBoolean(10, exchange.isActive());
            ps.setString(11, exchange.getTrxId());
            return ps.execute();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return false;
    }

    public static void updateBuyerInfo(Exchange exchange, String status) {
        String query = "UPDATE XB_EXCHANGE SET BUYER_ID=?, STATUS=?, BUYER_FROM_ADDRESS=?, BUYER_TO_ADDRESS=?, TRX_DATE=? " +
                "WHERE TRX_ID = ?";
        LOGGER.info(query);

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, exchange.getBuyerId());
            ps.setString(2, status);
            ps.setString(3, exchange.getBuyerFromAddress());
            ps.setString(4, exchange.getBuyerToAddress());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.setString(6, exchange.getTrxId());

            ps.execute();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public static Exchange findByTransactionId(String trxId) {
        String query = "SELECT * FROM XB_EXCHANGE WHERE TRX_ID = ?";
        LOGGER.info(query);
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, trxId);
            ResultSet rst = ps.executeQuery();

            if (rst.next()) {
                Exchange exchange = tradeFromResultSet(rst);
                return exchange;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return null;
    }
}
