package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitView implements ShowView{
    private TranslationsApplicationInterface translationsApplication;
    //Controller

    private static ExitView instance;

    private ExitView() {
    }

    public static ExitView getInstance() {
        if (instance == null) {
            instance = new ExitView();
        }
        return instance;
    }



    public void initialize(TranslationsApplicationInterface translationsApplication) {
        this.translationsApplication = translationsApplication;
    }

    @Override
    public void showView() {
        try {
            Stage confirmationStage = new Stage();

            confirmationStage.setTitle(translationsApplication.translate("label.confirmExit"));
            confirmationStage.initModality(Modality.APPLICATION_MODAL);
            confirmationStage.setResizable(false);

            VBox mainLayout = new VBox(20);
            mainLayout.setPadding(new Insets(20));
            mainLayout.setAlignment(Pos.CENTER);


            Label titleLabel = new Label(translationsApplication.translate("label.confirmExitQuestion"));
            titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label messageLabel = new Label(translationsApplication.translate("label.alertProgress"));
            messageLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");
            messageLabel.setWrapText(true);
            messageLabel.setAlignment(Pos.CENTER);

            HBox buttonBox = new HBox(15);
            buttonBox.setAlignment(Pos.CENTER);

            Button confirmButton = new Button(translationsApplication.translate("label.exit"));
            Button cancelButton = new Button(translationsApplication.translate("label.cancel"));

            confirmButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold;");
            cancelButton.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white;");

            confirmButton.setMinWidth(80);
            cancelButton.setMinWidth(80);

            buttonBox.getChildren().addAll(cancelButton, confirmButton);

            confirmButton.setOnAction(e -> {
                confirmationStage.close();
              //  performExit();
            });

            cancelButton.setOnAction(e -> {
                confirmationStage.close();
            });

            confirmationStage.setOnCloseRequest(e -> {
                confirmationStage.close();
            });

            mainLayout.getChildren().addAll(titleLabel, messageLabel, buttonBox);

            Scene scene = new Scene(mainLayout, 350, 180);
            confirmationStage.setScene(scene);

            confirmationStage.centerOnScreen();

            confirmationStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            //performExit();
        }
    }
}
