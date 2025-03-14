package src;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainPanel extends Application {
    // Create an instance of PasswordGenerator to handle password generation
    private final PasswordGenerator passwordGenerator = new PasswordGenerator();

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the application window
        primaryStage.setTitle("Password Generator");

        // Create UI components
        Label lengthLabel = new Label("Password Length:");
        TextField lengthField = new TextField("12"); // Default password length

        // Checkboxes for character type selection
        CheckBox uppercaseCheckBox = new CheckBox("Include Uppercase Letters");
        CheckBox lowercaseCheckBox = new CheckBox("Include Lowercase Letters");
        CheckBox numbersCheckBox = new CheckBox("Include Numbers");
        CheckBox symbolsCheckBox = new CheckBox("Include Symbols");

        // Button to trigger password generation
        Button generateButton = new Button("Generate Password");

        // Text field to display the generated password
        TextField passwordField = new TextField();
        passwordField.setEditable(false); // Prevent user editing
        passwordField.setId("passwordField"); // Set ID for CSS styling

        // Layout setup using GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20)); // Add padding around the grid
        grid.setVgap(10); // Vertical spacing between components
        grid.setHgap(10); // Horizontal spacing between components

        // Add components to the grid
        grid.add(lengthLabel, 0, 0);
        grid.add(lengthField, 1, 0);
        grid.add(uppercaseCheckBox, 0, 1);
        grid.add(lowercaseCheckBox, 1, 1);
        grid.add(numbersCheckBox, 0, 2);
        grid.add(symbolsCheckBox, 1, 2);
        grid.add(generateButton, 0, 3, 2, 1); // Span across 2 columns
        grid.add(passwordField, 0, 4, 2, 1); // Span across 2 columns

        // Event handling for the "Generate Password" button
        generateButton.setOnAction(event -> {
            try {
                // Parse the password length from the text field
                int length = Integer.parseInt(lengthField.getText());

                // Get user preferences for character types
                boolean includeUppercase = uppercaseCheckBox.isSelected();
                boolean includeLowercase = lowercaseCheckBox.isSelected();
                boolean includeNumbers = numbersCheckBox.isSelected();
                boolean includeSymbols = symbolsCheckBox.isSelected();

                // Generate the password using the PasswordGenerator class
                String password = passwordGenerator.generatePassword(length, includeUppercase, includeLowercase, includeNumbers, includeSymbols);

                // Display the generated password in the text field
                passwordField.setText(password);
            } catch (NumberFormatException e) {
                // Handle invalid input for password length
                passwordField.setText("Invalid length!");
            } catch (IllegalArgumentException e) {
                // Handle errors from the PasswordGenerator class
                passwordField.setText(e.getMessage());
            }
        });

        // Create the scene and apply CSS styling
        Scene scene = new Scene(grid, 400, 250);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Set the scene and display the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}