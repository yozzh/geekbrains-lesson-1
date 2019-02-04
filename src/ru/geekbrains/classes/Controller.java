package ru.geekbrains.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button sendButton;

    @FXML
    private TextField text;

    @FXML
    private ListView<ChatMessage> listView;
    private ObservableList<ChatMessage> chatMessageObservableList;

    public Controller() {
        chatMessageObservableList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(chatMessageObservableList);
        listView.setCellFactory(messageListView -> new MessageListViewCell());
    }

    @FXML
    private void stageKeyReleased(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) return;

        addMessage();
    }

    @FXML
    private void sendButtonAction(ActionEvent event) {
        addMessage();
    }

    @FXML
    private void textFieldKeyReleased(KeyEvent event) {
        checkButtonStatus();
    }

    private void addMessage() {
        chatMessageObservableList.add(new ChatMessage(text.getText()));
        text.clear();
        checkButtonStatus();
    }

    private void checkButtonStatus() {
        String message = text.getText();
        sendButton.setDisable(message.length() == 0);
    }
}
