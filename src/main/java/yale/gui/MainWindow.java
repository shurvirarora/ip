package yale.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import yale.Yale;
import yale.command.Ui;

/**
 * Controller for yale.gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Yale yale;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image yaleImage = new Image(this.getClass().getResourceAsStream("/images/Yale.png"));
    private Image sendImage = new Image(this.getClass().getResourceAsStream("/images/send.png"));
    private ImageView sendIcon = new ImageView(sendImage);

    /**
     * Initialise method
     */
    @FXML
    public void initialize() {
        sendIcon.setFitHeight(20);
        sendIcon.setFitWidth(20);
        sendButton.setGraphic(sendIcon);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getYaleDialog(Ui.welcomePrompt(), yaleImage));
    }

    public void setYale(Yale y) {
        yale = y;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = yale.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYaleDialog(response + Ui.getEnterCommand(), yaleImage)
        );
        if (input.equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }
}
