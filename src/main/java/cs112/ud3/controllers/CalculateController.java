package cs112.ud3.controllers;

import cs112.ud3.models.Transaction;
import cs112.ud3.models.TransactionService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CalculateController {

    @FXML
    private Label calculateTitleLabel;

    @FXML
    private TableView<Transaction> transactionTableView;
    @FXML
    private TableColumn<Transaction, String> nameColumn;
    @FXML
    private TableColumn<Transaction, String> typeColumn;
    @FXML
    private TableColumn<Transaction, String> dateColumn;
    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private Label incomeLabel;

    @FXML
    private Label expenseLabel;

    @FXML
    private Label balanceLabel;

    @FXML
    private Button quitButton;

    private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        nameColumn.setPrefWidth(100);
        typeColumn.setPrefWidth(100);
        dateColumn.setPrefWidth(100);
        amountColumn.setPrefWidth(100);

        transactionTableView.setItems(transactions);
    }

    public void setTransactions(TransactionService service) {

        transactions.setAll(service.getTransactions());

        double income = service.calculateIncome(transactions);
        double expenses = service.calculateExpenses(transactions);
        double balance = income + expenses;

        incomeLabel.setText("Total Income: $" + String.format("%.2f", income));
        expenseLabel.setText("Total Expenses: $" + String.format("%.2f", expenses));
        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
    }

    @FXML
    protected void onQuitButtonClick(){
        Platform.exit();
    }


}