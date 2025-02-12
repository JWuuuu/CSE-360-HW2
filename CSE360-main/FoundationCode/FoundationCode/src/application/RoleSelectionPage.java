package application;

import databasePart1.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * RoleSelectionPage displays a list of roles assigned to the user
 * and lets the user choose one for the current session.
 */
public class RoleSelectionPage {

    private final DatabaseHelper databaseHelper;
    private final List<String> roles;  // List of roles assigned to the user

    /**
     * Constructor for RoleSelectionPage.
     * @param databaseHelper The DatabaseHelper instance.
     * @param roles The list of roles assigned to the user.
     */
    public RoleSelectionPage(DatabaseHelper databaseHelper, List<String> roles) {
        this.databaseHelper = databaseHelper;
        this.roles = roles;
    }

    /**
     * Displays the role selection page.
     * @param primaryStage The primary stage where the scene will be displayed.
     * @param user The user object whose role will be set.
     */
    public void show(Stage primaryStage, User user) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-alignment: center;");

        Label headerLabel = new Label("Select Your Role");
        headerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Automated Test: Verify Page Title Label
        System.out.println("Test: Verify Page Title Label - Expected: 'Select Your Role', Actual: " + headerLabel.getText());

        // Create an observable list of roles and add to a ListView for selection
        ObservableList<String> roleOptions = FXCollections.observableArrayList(roles);
        ListView<String> roleListView = new ListView<>(roleOptions);
        roleListView.setPrefSize(300, 150);

        // Automated Test: Verify Number of Available Roles
        System.out.println("Test: Verify Number of Available Roles - Expected: " + roles.size() + ", Actual: " + roleOptions.size());

        // Button to confirm the role selection
        Button selectButton = new Button("Select Role");
        selectButton.setOnAction(event -> {
            String selectedRole = roleListView.getSelectionModel().getSelectedItem();
            if (selectedRole != null) {
                System.out.println("Test: Role Selection - Selected Role: " + selectedRole);
                user.setRole(selectedRole);
                if (selectedRole.equals("admin")) {
                    new AdminHomePage().show(primaryStage);
                } else {
                    new UserHomePage().show(primaryStage);
                }
            } else {
                // Alert the user to make a selection if none is chosen
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Role Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a role to continue.");
                alert.showAndWait();
            }
        });

        // Optional: A Back button to return to the previous page (e.g., WelcomeLoginPage)
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            System.out.println("Test: Back Button Clicked - Navigating to WelcomeLoginPage");
            new WelcomeLoginPage(databaseHelper).show(primaryStage, user);
        });

        layout.getChildren().addAll(headerLabel, roleListView, selectButton, backButton);

        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Role Selection");

        // Automated Test: Scene Loaded
        System.out.println("Test: RoleSelectionPage Loaded - Scene Title: " + primaryStage.getTitle());

        primaryStage.show();
    }
}
