package com.example.audiobookgenerator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class AudioBookGenerator extends Application {
    public static final int APP_WIDTH = 720;
    public static final int APP_HEIGHT = 720;

    private TextArea textArea;
    private ComboBox<String> voices, rates, volumes;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = createScene();

        // This adds style.css
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


        stage.setTitle("Audio Book Generator");
        stage.setScene(scene);
        stage.show();
    }

    private Scene createScene() {
        VBox box = new VBox();
        box.getStyleClass().add("body");

        Label abgLabel = new Label("Audio Book Generator");
        abgLabel.getStyleClass().add("abg-label");
        abgLabel.setMaxWidth(Double.MAX_VALUE); // Centered when resizing
        abgLabel.setAlignment(Pos.CENTER); // This centers the label

        // The text area where user can input text
        textArea = new TextArea();
        textArea.setWrapText(true);

        // This just makes it so the textArea doesn't stick to the sides
        StackPane textAreaPane = new StackPane();
        textAreaPane.setPadding(new Insets(0,15,0,15));
        textAreaPane.getChildren().add(textArea);

        // Add components here
        box.getChildren().add(abgLabel);
        box.getChildren().add(textAreaPane);

        GridPane settingsPane = createSettingComponents();
        box.getChildren().add(settingsPane);

        Button speakButton = createImageButton();
        speakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String msg = textArea.getText();
                String voice = voices.getValue();
                String rate = rates.getValue();
                String volume = volumes.getValue();

                TextToSpeechController.speak(msg,voice,rate,volume);
            }
        });
        StackPane speakButtonPane = new StackPane();
        speakButtonPane.setPadding(new Insets(40, 20, 0, 20));
        speakButtonPane.getChildren().add(speakButton);
        box.getChildren().add(speakButtonPane);

        // This returns the scene
        return new Scene(box, APP_WIDTH, APP_HEIGHT);
    }

    private Button createImageButton() {
        Button button = new Button("Speak");
        button.getStyleClass().add("speak-btn");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER);

        // Add image
        ImageView imageView = new ImageView(
                new Image(
                        getClass().getResourceAsStream("speak.png")
                )
        );
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        button.setGraphic(imageView);
        return button;
    }

    private GridPane createSettingComponents() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(40);
        gridPane.setPadding(new Insets(10,0,0,0));

        Label voiceLabel = new Label("Voice");
        voiceLabel.getStyleClass().add("setting-label");
        Label rateLabel = new Label("Rate");
        rateLabel.getStyleClass().add("setting-label");
        Label volumeLabel = new Label("Volume");
        volumeLabel.getStyleClass().add("setting-label");


        // Center text labels
        GridPane.setHalignment(voiceLabel, HPos.CENTER);
        GridPane.setHalignment(rateLabel, HPos.CENTER);
        GridPane.setHalignment(volumeLabel, HPos.CENTER);

        gridPane.add(voiceLabel,0,0);
        gridPane.add(rateLabel,1,0);
        gridPane.add(volumeLabel,2,0);

        voices = new ComboBox<>();
        voices.getItems().addAll(
                TextToSpeechController.getVoices()
        );
        voices.setValue(voices.getItems().get(0));
        voices.getStyleClass().add("setting-combo-box");

        voices.getStyleClass().add("setting-combo-box");
        rates = new ComboBox<>();
        rates.getItems().addAll(
                TextToSpeechController.getSpeedRates()
        );
        rates.setValue(rates.getItems().get(0));
        rates.getStyleClass().add("setting-combo-box");
        volumes = new ComboBox<>();
        volumes.getItems().addAll(
                TextToSpeechController.getVolumeLevels()
        );
        volumes.setValue(volumes.getItems().get(0));
        volumes.getStyleClass().add("setting-combo-box");

        gridPane.add(voices, 0, 1);
        gridPane.add(rates, 1, 1);
        gridPane.add(volumes, 2, 1);


        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    public static void main(String[] args) {
        launch();
    }
}