/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.controllers;

import hr.algebra.models.PetOwner;
import hr.algebra.models.Role;
import hr.algebra.models.Veterinarian;
import hr.algebra.viewmodels.OwnerViewModel;
import hr.algebra.viewmodels.PetViewModel;
import hr.algebra.viewmodels.VetViewModel;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * FXML Controller class
 *
 * @author dbasi
 */
public class VeterinarianController implements Initializable {

    private Map<TextField, Label> validationMap;
    private final ObservableList<VetViewModel> vets = FXCollections.observableArrayList();
    private final ObservableList<OwnerViewModel> owners = FXCollections.observableArrayList();
    private final ObservableList<PetViewModel> pets = FXCollections.observableArrayList();

    private VetViewModel selectedVetViewModel;
    private OwnerViewModel selectedOwnerViewModel;
    private PetViewModel selectedPetViewModel;

    private Tab previousTab;

    @FXML
    private TabPane tpContent;

    @FXML
    private Tab tabAddPerson;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfEmail;
    @FXML
    private ComboBox<Role> cbRole;
    @FXML
    private ImageView ivPersonImage;
    @FXML
    private Label lblFirstNameError;
    @FXML
    private Label lblLastNameError;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblRoleError;
    @FXML
    private Label lblPersonPictureError;

    @FXML
    private Tab tabAddPet;
    @FXML
    private TextField tfPetName;
    @FXML
    private TextField tfSpecies;
    @FXML
    private Spinner<Integer> spAge;
    @FXML
    private ComboBox<Veterinarian> cbVeterinarian;
    @FXML
    private ComboBox<PetOwner> cbOwner;
    @FXML
    private ImageView ivPetImage;
    @FXML
    private Label lblPetNameError;
    @FXML
    private Label lblSpeciesError;
    @FXML
    private Label lblAgeError;
    @FXML
    private Label lblPetPictureError;
    @FXML
    private Label lblVeterinarianChoiceError;
    @FXML
    private Label lblPetOwnerChoiceError;

    @FXML
    private Tab tabVetList;
    @FXML
    private TableView<VetViewModel> tvVeterinarians;
    @FXML
    private TableColumn<VetViewModel, String> tcVetFirstName;
    @FXML
    private TableColumn<VetViewModel, String> tcVetLastName;
    @FXML
    private TableColumn<VetViewModel, String> tcVetEmail;

    @FXML
    private Tab tabOwnerList;
    @FXML
    private TableView<OwnerViewModel> tvOwners;
    @FXML
    private TableColumn<OwnerViewModel, String> tcOwnerFirstName;
    @FXML
    private TableColumn<OwnerViewModel, String> tcOwnerLastName;
    @FXML
    private TableColumn<OwnerViewModel, String> tcOwnerEmail;

    @FXML
    private Tab tabPetList;
    @FXML
    private TableView<PetViewModel> tvPets;
    @FXML
    private TableColumn<PetViewModel, String> tcPetName;
    @FXML
    private TableColumn<PetViewModel, String> tcPetSpecies;
    @FXML
    private TableColumn<PetViewModel, Integer> tcPetAge;
    @FXML
    private TableColumn<PetViewModel, Veterinarian> tcPetVeterinarian;
    @FXML
    private TableColumn<PetViewModel, PetOwner> tcPetOwner;

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
