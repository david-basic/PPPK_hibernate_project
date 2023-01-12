/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.controllers;

import hr.algebra.dao.RepositoryFactory;
import hr.algebra.models.PetOwner;
import hr.algebra.models.Veterinarian;
import hr.algebra.viewmodels.OwnerViewModel;
import hr.algebra.viewmodels.PetViewModel;
import hr.algebra.viewmodels.VetViewModel;
import java.net.URL;
import java.util.AbstractMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

    private Map<TextField, Label> tfValidationMapPerson; // does not include Role cb
    private Map<TextField, Label> tfValidationMapPet; // does not include Vet and Owner cbs
    private Map<ComboBox, Label> cbValidationMapPet;

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
    private ComboBox<String> cbRole;
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
    private TextField tfPetAge;
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
        initValidation();
        initVets();
        initOwners();
        initPets();
        initTables();
        addIntegerMask(tfPetAge);
        setupListeners();
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

    private void initValidation() {
        tfValidationMapPerson = Stream.of(
                new AbstractMap.SimpleImmutableEntry<>(tfFirstName, lblFirstNameError),
                new AbstractMap.SimpleImmutableEntry<>(tfLastName, lblLastNameError),
                new AbstractMap.SimpleImmutableEntry<>(tfEmail, lblEmailError)
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        tfValidationMapPet = Stream.of(
                new AbstractMap.SimpleImmutableEntry<>(tfPetName, lblPetNameError),
                new AbstractMap.SimpleImmutableEntry<>(tfSpecies, lblSpeciesError),
                new AbstractMap.SimpleImmutableEntry<>(tfPetAge, lblAgeError)
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        cbValidationMapPet = Stream.of(
                new AbstractMap.SimpleImmutableEntry<>(cbOwner, lblPetOwnerChoiceError),
                new AbstractMap.SimpleImmutableEntry<>(cbVeterinarian, lblVeterinarianChoiceError)
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void initVets() {
        try {
            RepositoryFactory.getRepository().getVeterinarians().forEach(p -> vets.add(new VetViewModel(p)));
        } catch (Exception ex) {
            Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initOwners() {
        try {
            RepositoryFactory.getRepository().getPetOwners().forEach(po -> owners.add(new OwnerViewModel(po)));
        } catch (Exception ex) {
            Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initPets() {
        try {
            RepositoryFactory.getRepository().getPets().forEach(pet -> pets.add(new PetViewModel(pet)));
        } catch (Exception ex) {
            Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTables() {
        tcVetFirstName.setCellValueFactory(vet -> vet.getValue().getFirstNameProperty());
        tcVetLastName.setCellValueFactory(vet -> vet.getValue().getLastNameProperty());
        tcVetEmail.setCellValueFactory(vet -> vet.getValue().getEmailProperty());
        tvVeterinarians.setItems(vets);

        tcOwnerFirstName.setCellValueFactory(o -> o.getValue().getFirstNameProperty());
        tcOwnerLastName.setCellValueFactory(o -> o.getValue().getLastNameProperty());
        tcOwnerEmail.setCellValueFactory(o -> o.getValue().getEmailProperty());
        tvOwners.setItems(owners);

        tcPetName.setCellValueFactory(pet -> pet.getValue().getPetNameProperty());
        tcPetSpecies.setCellValueFactory(pet -> pet.getValue().getSpeciesProperty());
        tcPetAge.setCellValueFactory(pet -> pet.getValue().getAgeProperty().asObject());
        tcPetOwner.setCellValueFactory(pet -> pet.getValue().getPetOwnerProperty());
        tcPetVeterinarian.setCellValueFactory(pet -> pet.getValue().getVeterinarianProperty());
        tvPets.setItems(pets);

    }

    private void addIntegerMask(TextField tfAge) {
        
    }

    private void setupListeners() {
        
    }
}
