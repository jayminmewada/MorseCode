package com.morse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MorseCodeApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        //primaryStage.getIcons().add(new Image("resources/morse.png"));
        TextArea inputText = new TextArea();
        TextArea outputText = new TextArea();
        Button encodeButton = new Button("Encode");
        Button decodeButton = new Button("Decode");
        Button clear = new Button("Clear");
        MorseCodeEncoder mce = new MorseCodeEncoder();
        MorseCodeDecoder mcd = new MorseCodeDecoder();
        clear.setOnAction(e->inputText.clear());
        encodeButton.setOnAction(e -> outputText.setText(mce.encode(inputText.getText())));
        decodeButton.setOnAction(e -> outputText.setText(mcd.decode(inputText.getText())));
        HBox hlayout = new HBox(10,encodeButton,decodeButton,clear);
        VBox layout = new VBox(10, inputText,hlayout, outputText);
        layout.setPadding(new javafx.geometry.Insets(10));

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setTitle("Morse Code Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
