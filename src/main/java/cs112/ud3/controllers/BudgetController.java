package cs112.ud3.controllers;

import cs112.ud3.BudgetApplication;
import cs112.ud3.models.Deposit;
import cs112.ud3.models.Transaction;
import cs112.ud3.models.TransactionService;
import cs112.ud3.models.Withdrawal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class BudgetController {
    private final TransactionService transactionService = new TransactionService();


    @FXML
    private Label welcomeText;
    @FXML
    private Label explanationLabel;

    @FXML
    private TextField transactionNameTextField;

    @FXML
    private ComboBox<String> transactionTypeComboBox;

    @FXML
    private DatePicker transactionDateDatePicker;

    @FXML
    private TextField transactionAmountTextField;

    @FXML
    private Button enterTransactionButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button calculateBudgetButton;

    @FXML
    public void initialize() {
        // Add items to the ComboBox
        transactionTypeComboBox.getItems().addAll("Deposit", "Withdrawal");
    }

    @FXML
    protected void onEnterTransactionButtonClick() {
        try {
            // Retrieve values from text fields
            String name = transactionNameTextField.getText();
            String type = transactionTypeComboBox.getValue();
            String date = transactionDateDatePicker.getValue().toString();
            double amount = Double.parseDouble(transactionAmountTextField.getText());

            //validate amount based on transaction type, (withdrawal must be negative)
            if ("Withdrawal".equals(type) && amount >= 0) {
                showAlert("Error", "Withdrawal amounts must be negative. Please try again.");
                return;
            } else if ("Deposit".equals(type) && amount <= 0) {
                showAlert("Error", "Deposit amounts must be positive. Please try again.");
                return;
            }

            // Create the appropriate subclass based on the type
            Transaction transaction;
            if ("Deposit".equals(type)) {
                transaction = new Deposit(name, date, amount);
            } else if ("Withdrawal".equals(type)) {
                transaction = new Withdrawal(name, date, amount);
            } else {
                showAlert("Error", "Invalid transaction type. Please enter 'Deposit' or 'Withdrawal'.");
                return; // Stop further execution if the type is invalid
            }

            // Add the transaction to the service
            transactionService.addTransaction(transaction);

            // Clear the text fields
            transactionNameTextField.clear();
            transactionTypeComboBox.setValue(null);
            transactionDateDatePicker.setValue(null);
            transactionAmountTextField.clear();

            // Show success message
            showAlert("Success", "Transaction added successfully!");
        } catch (Exception e) {
            // Show error message if there's an issue
            showAlert("Error", "Invalid transaction data. Please check your input and try again.");
        }
    }

    @FXML
    protected void onQuitButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void onCalculateBudgetButtonClick(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(BudgetApplication.class.getResource("calculate-view.fxml"));
        Parent calculateView = loader.load();

        // Pass the TransactionService instance to the CalculateController
        CalculateController calculateController = loader.getController();
        calculateController.setTransactions(transactionService);

        // Switch the scene
        Scene calculateViewScene = new Scene(calculateView);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(calculateViewScene);
        window.show();

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}