package ru.geekbrains.classes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MessageListViewCell extends ListCell<ChatMessage> {
    @FXML
    private Label date;

    @FXML
    private Label message;

    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(ChatMessage chatMessage, boolean empty) {
        super.updateItem(chatMessage, empty);

        if(empty || chatMessage == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            message.setText(String.valueOf(chatMessage.text));
            date.setText(String.valueOf(chatMessage.getTime()));

            setText(null);
            setGraphic(gridPane);
        }
    }
}
