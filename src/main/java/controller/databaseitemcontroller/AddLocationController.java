package controller.databaseitemcontroller;

import databasemanagement.objectrelationalmap.LocationMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Location;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author  Kyle Williams
 * @since   Version 2
 */
public class AddLocationController extends DatabaseItemModificationController implements Initializable{
    private LocationMapper locationMapper = new LocationMapper();
    @FXML private TextField newLocationName;
    @FXML private TextField newLocationAddress;
    @FXML private TextField newLocationCity;
    @FXML private TextField newLocationState;
    @FXML private TextField newLocationZipCode;
    @FXML private Label errorLabel;
    @FXML private Button addButton;
    @FXML private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnMouseClicked(event -> addLocation());
        cancelButton.setOnMouseClicked(event -> stage.close());
    }

    private void addLocation() {
        if (newLocationName.getText().isEmpty()) {
            errorLabel.setText("Please fill in fields marked with *");
            errorLabel.setVisible(true);
            return;
        }
        List<Location> locations = locationMapper.readAll();
        for (Location location : locations) {
            if (location.getLocationName().equals(newLocationName.getText())) {
                errorLabel.setText("Location name already exists");
                errorLabel.setVisible(true);
                return;
            }
        }
        Location newLocation = new Location(newLocationName.getText());
        newLocation.setLocationAddress(newLocationAddress.getText());
        newLocation.setLocationCity(newLocationCity.getText());
        newLocation.setLocationState(newLocationState.getText());
        newLocation.setLocationZipCode(newLocationZipCode.getText());
        locationMapper.create(newLocation);
        stage.close();
    }
}