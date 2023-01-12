/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author dbasi
 */
public class VeterinarianController implements Initializable {

    @FXML
    private TabPane tpContent;
    @FXML
    private Tab tabVetList;
    @FXML
    private TableView<?> tvVeterinarians;
    @FXML
    private TableColumn<?, ?> tcFirstName;
    @FXML
    private TableColumn<?, ?> tcLastName;
    @FXML
    private TableColumn<?, ?> tcEmail;
    @FXML
    private TableColumn<?, ?> tcPicture;
    @FXML
    private Tab tabOwnerList;
    @FXML
    private TableView<?> tvOwners;
    @FXML
    private Tab tabAddPerson;
    @FXML
    private ImageView ivPersonImage;
    @FXML
    private TextField tfFirstName;
    @FXML
    private Label lblFirstNameError;
    @FXML
    private Label lblLastNameError;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblRoleError;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfEmail;
    @FXML
    private ComboBox<?> cbRole;
    @FXML
    private Label lblPictureError;
    @FXML
    private Tab tabPetList;
    @FXML
    private TableView<?> tvPets;
    @FXML
    private TableColumn<?, ?> tcPetName;
    @FXML
    private TableColumn<?, ?> tcSpecies;
    @FXML
    private TableColumn<?, ?> tcAge;
    @FXML
    private TableColumn<?, ?> tcVeterinarian;
    @FXML
    private TableColumn<?, ?> tcPetOwner;
    @FXML
    private Tab tabAddPet;
    @FXML
    private AnchorPane ivPetImage;
    @FXML
    private ImageView ivPersonImage1;
    @FXML
    private TextField tfPetName;
    @FXML
    private Label lblPetNameError;
    @FXML
    private TextField tfSpecies;
    @FXML
    private Label lblSpeciesError;
    @FXML
    private Label lblAgeError;
    @FXML
    private Label lblPetNameError111;
    @FXML
    private Spinner<?> spAge;
    @FXML
    private ComboBox<?> cbVeterinarian;
    @FXML
    private Label lblOwnerError;
    @FXML
    private ComboBox<?> cbOwner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void editVet(ActionEvent event) {
    }

    @FXML
    private void deleteVet(ActionEvent event) {
    }

    @FXML
    private void editOwner(ActionEvent event) {
    }

    @FXML
    private void deleteOwner(ActionEvent event) {
    }

    @FXML
    private void uploadPersonPicture(ActionEvent event) {
    }

    @FXML
    private void commitPerson(ActionEvent event) {
    }

    @FXML
    private void editPet(ActionEvent event) {
    }

    @FXML
    private void deletePet(ActionEvent event) {
    }

    @FXML
    private void uploadPetPicture(ActionEvent event) {
    }

    @FXML
    private void commitPet(ActionEvent event) {
    }
    }
