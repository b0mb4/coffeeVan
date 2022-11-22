package com.example.mycoffee;

import com.example.mycoffee.entities.Coffee;
import com.example.mycoffee.entities.Van;
import com.example.mycoffee.repository.CoffeeDao;
import com.example.mycoffee.repository.JdbcDao;
import com.example.mycoffee.repository.VanDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class MainController {

    @FXML
    private Label resultLabel;
    @FXML
    private Label logLabel;
    @FXML
    private VBox layoutVBox;

    @FXML
    protected void viewVansInStorageMenuItem() throws SQLException {
        clearLayoutBox();
        List<Van> vanList = VanDao.selectVans();
        addListToResult(vanList, "фургонів");
    }

    @FXML
    protected void viewCoffeeInVanMenuItem() throws SQLException {
        clearLayoutBox();
        List<Coffee> coffeeList = CoffeeDao.selectCoffee();
        addListToResult(coffeeList, "кави");
    }

    @FXML
    protected void addVanToStorageMenuItem() {
        clearLayoutBox();
        resultLabel.setText("Введіть дані: ");
        Label volumeLabel = new Label();
        volumeLabel.setText("Введіть об'єм:");
        TextField volumeTextField = new TextField();
        Button createButton = new Button();
        createButton.setText("Створити новий фургон");

        createButton.setOnAction(e -> {
            try {
                Integer vanVolume = Integer.parseInt(volumeTextField.getText());
                if (vanVolume > 0) {
                    clearLayoutBox();
                    VanDao.insertVans(vanVolume);
                    logLabel.setText("Успішно додано новий фургон");
                } else {
                    resultLabel.setText("Помилка! неправильні дані");
                }
            } catch (NumberFormatException | SQLException nfe) {
                resultLabel.setText("Помилка! неправильні дані");
            }
        });
        layoutVBox.getChildren().addAll(volumeLabel, volumeTextField, createButton);
    }

    @FXML
    protected void addCoffeeToStorageMenuItem() {
        clearLayoutBox();
        resultLabel.setText("Введіть дані: ");
        Label conditionLabel = new Label();
        conditionLabel.setText("Введіть стан:\n 1-Зернова;2-мелена;3-розчинна;4-банках;5-пакетиках");
        TextField conditionTextField = new TextField();
        Label volumeLabel = new Label();
        volumeLabel.setText("Введіть об'єм");
        TextField volumeTextField = new TextField();
        Label costLabel = new Label();
        costLabel.setText("Введіть ціну");
        TextField costTextField = new TextField();
        Label vanIdTestLabel = new Label();
        vanIdTestLabel.setText("Введіть айді грузовика");
        TextField vanIdTextField = new TextField();
        Button createButton = new Button();
        createButton.setText("Створити каву");

        createButton.setOnAction(e -> {
            try {
                Integer condition = Integer.parseInt(conditionTextField.getText());
                Integer volume = Integer.parseInt(volumeTextField.getText());
                Integer cost = Integer.parseInt(costTextField.getText());
                Integer vanId = Integer.parseInt(vanIdTextField.getText());
                if (condition > 0 && condition < 6 && cost > 0
                        && VanDao.isVanExist(vanId) && volume > 0) {
                    clearLayoutBox();
                    CoffeeDao.insertCoffees(condition, volume, cost, vanId);
                    logLabel.setText("Успішно додано каву");
                } else {
                    resultLabel.setText("Помилка! неправильні дані");
                }
            } catch (NumberFormatException | SQLException nfe) {
                resultLabel.setText("Помилка! неправильні дані");
            }
        });
        layoutVBox.getChildren().addAll(volumeLabel, volumeTextField, vanIdTestLabel, vanIdTextField, costLabel, costTextField, conditionLabel, conditionTextField, createButton);
    }

    @FXML
    protected void deleteVanFromStorageMenuItem() {
        deleteRow("грузовика", "van", "vanId");
    }

    @FXML
    protected void deleteCoffeeFromStorageMenuItem() {
        deleteRow("кави", "coffee", "coffeeId");
    }

    @FXML
    protected void sortCoffeeInVanMenuItem() throws SQLException {
        clearLayoutBox();
        List<Coffee> coffeeList = CoffeeDao.selectSortedCoffee();
        addListToResult(coffeeList, "кави");
    }

    @FXML
    protected void findCoffeeInVanMenuItem() throws SQLException {
        clearLayoutBox();
        resultLabel.setText("Введіть дані: ");
        Label vanIdTestLabel = new Label();
        vanIdTestLabel.setText("Введіть айді грузовика");
        TextField vanIdTextField = new TextField();

        Label firstCostLabel = new Label();
        firstCostLabel.setText("Введіть перший діапазон ціни");
        TextField firstCostTextField = new TextField();

        Label secondCostLabel = new Label();
        secondCostLabel.setText("Введіть перший діапазон ціни");
        TextField secondCostTextField = new TextField();
        Button createButton = new Button();
        createButton.setText("Знайти товар");
        createButton.setOnAction(e -> {
            try {
                clearLayoutBox();
                Integer vanId = Integer.parseInt(vanIdTextField.getText());
                Integer firstCost = Integer.parseInt(firstCostTextField.getText());
                Integer secondCost = Integer.parseInt(secondCostTextField.getText());
                List<Coffee> coffees = CoffeeDao.selectSpecifiedCoffee(vanId, firstCost, secondCost);
                addListToResult(coffees, "кави");
            } catch (NumberFormatException nfe) {
                resultLabel.setText("Помилка! неправильні дані");
            } catch (SQLException ex) {
                resultLabel.setText("Помилка! неправильні дані");
            }
        });
        layoutVBox.getChildren().addAll(vanIdTestLabel, vanIdTextField,
                firstCostLabel, firstCostTextField,
                secondCostLabel, secondCostTextField, createButton);
    }

    protected void deleteRow(String tableValue, String tableName, String columnName) {
        clearLayoutBox();
        resultLabel.setText("Введіть дані: ");
        Label vanIdTestLabel = new Label();
        vanIdTestLabel.setText("Введіть айді " + tableValue);
        TextField vanIdTextField = new TextField();
        Button createButton = new Button();
        createButton.setText("Видалити " + tableValue);
        createButton.setOnAction(e -> {
            try {
                clearLayoutBox();
                Integer vanId = Integer.parseInt(vanIdTextField.getText());
                JdbcDao.deleteById(tableName, columnName, vanId.toString());
                logLabel.setText("Успішно видалено " + tableValue);
            } catch (NumberFormatException nfe) {
                resultLabel.setText("Помилка! неправильні дані");
            } catch (SQLException ex) {
                resultLabel.setText("Помилка! неправильні дані");
            }
        });
        layoutVBox.getChildren().addAll(vanIdTestLabel, vanIdTextField, createButton);
    }

    protected <T> void addListToResult(List<T> list, String string) {

        if (!list.isEmpty()) {
            resultLabel.setText("Список " + string);
            ListView listView = new ListView();
            layoutVBox.getChildren().add(listView);
            listView.getItems().addAll(list);
        } else {
            resultLabel.setText("Список пустий");
        }
    }

    private void clearLayoutBox() {
        if (!layoutVBox.getChildren().isEmpty()) {
            layoutVBox.getChildren().clear();
        }
    }
}