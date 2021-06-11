package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClinicalAnalysisLaboratoryUI {

    @FXML
    private Button myReturnButtonLab;

    private SceneController sceneController = SceneController.getInstance();


    public void returnToAdminMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/AdministratorUI.fxml");
    }
}