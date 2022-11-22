package com.example.mycoffee.repository;

import com.example.mycoffee.entities.Van;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VanDao extends JdbcDao {
    private static ResultSet rs;

    static {
        try {
            rs = select("van");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Van> selectVans() throws SQLException {
        List<Van> vans = new ArrayList<>();
        while (rs.next()) {
            vans.add(new Van()
                    .setVanId(rs.getInt("vanId"))
                    .setVanVolume(rs.getLong("vanVolume")));
        }
        return vans;
    }

    public static List<Integer> selectVanIds() throws SQLException {
        rs = select("van");
        List<Integer> vanIds = new ArrayList<>();
        while (rs.next()) {
            vanIds.add(rs.getInt("vanId"));
        }
        return vanIds;
    }

    public static void insertVans(long vanVolume) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement("INSERT INTO van(vanVolume) VALUES (?)");
        preparedStatement.setLong(1, vanVolume);
        preparedStatement.executeUpdate();
    }


    public static boolean isVanExist(int vanId) throws SQLException {
        return selectVanIds().contains(vanId);
    }


}
