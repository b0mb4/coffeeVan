package com.example.mycoffee.repository;

import com.example.mycoffee.entities.Coffee;
import com.example.mycoffee.entities.Van;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeDao extends JdbcDao {

    public static List<Coffee> selectCoffee() throws SQLException {
        ResultSet rs = select("coffee");
        List<Coffee> coffees = new ArrayList<>();
        while (rs.next()) {
            coffees.add(new Coffee()
                    .setVanId(rs.getInt("vanId"))
                    .setCoffeeId(rs.getInt("coffeeId"))
                    .setCoffeeVolume(rs.getInt("coffeeVolume"))
                    .setCoffeeCost(rs.getInt("coffeeCost"))
                    .setPhysicCondition(rs.getInt("physicCondition")));
        }
        return coffees;
    }

    public static void insertCoffees(int physicCondition, int coffeeVolume, int coffeeCost, int vanId) throws SQLException {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement("INSERT INTO coffee(physicCondition,coffeeVolume,coffeeCost,vanId) VALUES (?, ?, ? ,?)");
        preparedStatement.setInt(1, physicCondition);
        preparedStatement.setInt(2, coffeeVolume);
        preparedStatement.setInt(3, coffeeCost);
        preparedStatement.setInt(4, vanId);
        preparedStatement.executeUpdate();
    }

    public static List<Coffee> selectSortedCoffee() throws SQLException {
        return selectCoffee().stream()
                .sorted((coffee1, coffee2) -> (coffee1.getCoffeeCost() / coffee1.getCoffeeVolume()) > (coffee2.getCoffeeCost() / coffee2.getCoffeeVolume()) ? 1 : -1)
                .toList();
    }

    public static List<Coffee> selectSpecifiedCoffee(int vanId, int firstCost, int secondCost) throws SQLException {
        return selectCoffee().stream()
                .filter(coffee -> coffee.getVanId() == vanId
                        && coffee.getCoffeeCost() < firstCost
                        && coffee.getCoffeeCost() > secondCost)
                .toList();
    }
}
