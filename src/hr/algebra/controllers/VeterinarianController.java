/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.controllers;

import hr.algebra.dao.RepositoryFactory;
import hr.algebra.models.PetOwner;
import hr.algebra.models.Veterinarian;
import hr.algebra.utilities.FileUtils;
import hr.algebra.utilities.ImageUtils;
import hr.algebra.viewmodels.OwnerViewModel;
import hr.algebra.viewmodels.PetViewModel;
import hr.algebra.viewmodels.VetViewModel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author dbasi
 */
public class VeterinarianController implements Initializable {

    private Map<TextField, Label> tfValidationMapVet;
    private Map<TextField, Label> tfValidationMapOwner;
    private Map<TextField, Label> tfValidationMapPet; // does not include Vet and Owner cbs
    private Map<ComboBox, Label> cbValidationMapPet;

    private final ObservableList<VetViewModel> vets = FXCollections.observableArrayList();
    private final ObservableList<OwnerViewModel> owners = FXCollections.observableArrayList();
    private final ObservableList<PetViewModel> pets = FXCollections.observableArrayList();

    private ObservableList<Veterinarian> veterinarianCollection;
    private ObservableList<PetOwner> petOwnerCollection;

    private VetViewModel selectedVetViewModel;
    private OwnerViewModel selectedOwnerViewModel;
    private PetViewModel selectedPetViewModel;

    private Tab previousTab;

    @FXML
    private TabPane tpContent;

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
    private Tab tabAddVet;
    @FXML
    private TextField tfVetFirstName;
    @FXML
    private TextField tfVetLastName;
    @FXML
    private TextField tfVetEmail;
    @FXML
    private ImageView ivVetImage;
    @FXML
    private Label lblVetFirstNameError;
    @FXML
    private Label lblVetLastNameError;
    @FXML
    private Label lblVetEmailError;
    @FXML
    private Label lblVetPictureError;

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
    private Tab tabAddOwner;
    @FXML
    private TextField tfOwnerFirstName;
    @FXML
    private TextField tfOwnerLastName;
    @FXML
    private TextField tfOwnerEmail;
    @FXML
    private ImageView ivOwnerImage;
    @FXML
    private Label lblOwnerFirstNameError;
    @FXML
    private Label lblOwnerLastNameError;
    @FXML
    private Label lblOwnerEmailError;
    @FXML
    private Label lblOwnerPictureError;

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
        initCbs();
        addIntegerMask(tfPetAge);
        setupListeners();
    }

    @FXML
    private void editVet(ActionEvent event) {
        if (tvVeterinarians.getSelectionModel().getSelectedItem() != null) {
            bindVet(tvVeterinarians.getSelectionModel().getSelectedItem());
            tpContent.getSelectionModel().select(tabAddVet);
            previousTab = tabAddVet;
        }
    }

    @FXML
    private void deleteVet(ActionEvent event) {
        if (tvVeterinarians.getSelectionModel().getSelectedItem() != null) {
            vets.remove(tvVeterinarians.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void editOwner(ActionEvent event) {
        if (tvOwners.getSelectionModel().getSelectedItem() != null) {
            bindOwner(tvOwners.getSelectionModel().getSelectedItem());
            tpContent.getSelectionModel().select(tabAddOwner);
            previousTab = tabAddOwner;
        }
    }

    @FXML
    private void deleteOwner(ActionEvent event) {
        if (tvOwners.getSelectionModel().getSelectedItem() != null) {
            owners.remove(tvOwners.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void editPet(ActionEvent event) {
        if (tvPets.getSelectionModel().getSelectedItem() != null) {
            bindPet(tvPets.getSelectionModel().getSelectedItem());
            tpContent.getSelectionModel().select(tabAddPet);
            previousTab = tabAddPet;
        }
    }

    @FXML
    private void deletePet(ActionEvent event) {
        if (tvPets.getSelectionModel().getSelectedItem() != null) {
            pets.remove(tvPets.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void uploadPetPicture(ActionEvent event) {
        File file = FileUtils.uploadFileDialog(tfPetName.getScene().getWindow(), "jpg", "jpeg", "png");
        
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            ivPetImage.setImage(image);
            
            String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            try {
                selectedPetViewModel.getPet().setPicture(ImageUtils.imageToByteArray(image, ext));
            } catch (IOException ex) {
                Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void commitPet(ActionEvent event) {

    }

    @FXML
    private void uploadVetPicture(ActionEvent event) {
        File file = FileUtils.uploadFileDialog(tfVetFirstName.getScene().getWindow(), "jpg", "jpeg", "png");
        
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            ivVetImage.setImage(image);
            
            String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            try {
                selectedVetViewModel.getVeterinarian().setPicture(ImageUtils.imageToByteArray(image, ext));
            } catch (IOException ex) {
                Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void commitVet(ActionEvent event) {

    }

    @FXML
    private void uploadOwnerPicture(ActionEvent event) {
        File file = FileUtils.uploadFileDialog(tfOwnerFirstName.getScene().getWindow(), "jpg", "jpeg", "png");
        
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            ivOwnerImage.setImage(image);
            
            String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            try {
                selectedOwnerViewModel.getPetOwner().setPicture(ImageUtils.imageToByteArray(image, ext));
            } catch (IOException ex) {
                Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void commitOwner(ActionEvent event) {

    }

    private void initValidation() {
        tfValidationMapVet = Stream.of(
                new AbstractMap.SimpleImmutableEntry<>(tfVetFirstName, lblVetFirstNameError),
                new AbstractMap.SimpleImmutableEntry<>(tfVetLastName, lblVetLastNameError),
                new AbstractMap.SimpleImmutableEntry<>(tfVetEmail, lblVetEmailError)
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        tfValidationMapOwner = Stream.of(
                new AbstractMap.SimpleImmutableEntry<>(tfOwnerFirstName, lblOwnerFirstNameError),
                new AbstractMap.SimpleImmutableEntry<>(tfOwnerLastName, lblOwnerLastNameError),
                new AbstractMap.SimpleImmutableEntry<>(tfOwnerEmail, lblOwnerEmailError)
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

    private void initCbs() {
        owners.forEach(o -> petOwnerCollection.add(o.getPetOwner()));
        vets.forEach(v -> veterinarianCollection.add(v.getVeterinarian()));

        cbOwner.setItems(petOwnerCollection);
        cbVeterinarian.setItems(veterinarianCollection);
    }

    private void addIntegerMask(TextField tf) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (change.getText().matches("\\d*")) {
                return change;
            }
            return null;
        };

        tf.setTextFormatter(new TextFormatter<>(
                new IntegerStringConverter(), 0, filter
        ));
    }

    private void setupListeners() {
        tpContent.setOnMouseClicked(event -> {
            if (tpContent.getSelectionModel().getSelectedItem().equals(tabAddVet)
                    && !tabAddVet.equals(previousTab)) {
                bindVet(null);
            } else if (tpContent.getSelectionModel().getSelectedItem().equals(tabAddPet)
                    && !tabAddPet.equals(previousTab)) {
                bindPet(null);
            } else if (tpContent.getSelectionModel().getSelectedItem().equals(tabAddOwner)
                    && !tabAddOwner.equals(previousTab)) {
                bindOwner(null);
            }

            previousTab = tpContent.getSelectionModel().getSelectedItem();
        });

        vets.addListener(new ListChangeListener<VetViewModel>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends VetViewModel> change) {
                if (change.next()) {
                    if (change.wasRemoved()) {
                        change.getRemoved().forEach(vvm -> {
                            try {
                                RepositoryFactory.getRepository().deleteVeterinarian(vvm.getVeterinarian());
                            } catch (Exception ex) {
                                Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                    } else if (change.wasAdded()) {
                        change.getAddedSubList().forEach(vvm -> {
                            try {
                                int idVet = RepositoryFactory.getRepository().addVeterinarian(vvm.getVeterinarian());
                                vvm.getVeterinarian().setIDVeterinarian(idVet);
                            } catch (Exception ex) {
                                Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                }

            }
        });

        owners.addListener(new ListChangeListener<OwnerViewModel>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends OwnerViewModel> change) {
                if (change.next()) {
                    if (change.wasRemoved()) {
                        change.getRemoved().forEach(ovm -> {
                            try {
                                RepositoryFactory.getRepository().deletePetOwner(ovm.getPetOwner());
                            } catch (Exception ex) {
                                Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                    } else if (change.wasAdded()) {
                        change.getAddedSubList().forEach(ovm -> {
                            try {
                                int idOwner = RepositoryFactory.getRepository().addPetOwner(ovm.getPetOwner());
                                ovm.getPetOwner().setIDPetOwner(idOwner);
                            } catch (Exception ex) {
                                Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                }

            }
        });

        pets.addListener(new ListChangeListener<PetViewModel>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends PetViewModel> change) {
                if (change.next()) {
                    if (change.wasRemoved()) {
                        change.getRemoved().forEach(pvm -> {
                            try {
                                RepositoryFactory.getRepository().deletePet(pvm.getPet());
                            } catch (Exception ex) {
                                Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    } else if (change.wasAdded()) {
                        change.getAddedSubList().forEach(pvm -> {
                            try {
                                int idPet = RepositoryFactory.getRepository().addPet(pvm.getPet());
                                pvm.getPet().setIDPet(idPet);
                            } catch (Exception ex) {
                                Logger.getLogger(VeterinarianController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                }

            }
        });
    }

    private void bindVet(VetViewModel vetViewModel) {
        resetForm();

        selectedVetViewModel = vetViewModel != null ? vetViewModel : new VetViewModel(null);

        tfVetFirstName.setText(selectedVetViewModel.getFirstNameProperty().get());
        tfVetLastName.setText(selectedVetViewModel.getLastNameProperty().get());
        tfVetEmail.setText(selectedVetViewModel.getEmailProperty().get());

        ivVetImage.setImage(
                selectedVetViewModel.getPictureProperty().get() != null
                ? new Image(new ByteArrayInputStream(selectedVetViewModel.getPictureProperty().get()))
                : new Image(new File("src/assets/no_image.png").toURI().toString())
        );
    }

    private void bindOwner(OwnerViewModel ownerViewModel) {
        resetForm();

        selectedOwnerViewModel = ownerViewModel != null ? ownerViewModel : new OwnerViewModel(null);

        tfOwnerFirstName.setText(selectedOwnerViewModel.getFirstNameProperty().get());
        tfOwnerLastName.setText(selectedOwnerViewModel.getLastNameProperty().get());
        tfOwnerEmail.setText(selectedOwnerViewModel.getEmailProperty().get());

        ivOwnerImage.setImage(
                selectedOwnerViewModel.getPictureProperty().get() != null
                ? new Image(new ByteArrayInputStream(selectedOwnerViewModel.getPictureProperty().get()))
                : new Image(new File("src/assets/no_image.png").toURI().toString())
        );
    }

    private void bindPet(PetViewModel petViewModel) {
        resetForm();

        selectedPetViewModel = petViewModel != null ? petViewModel : new PetViewModel(null);

        tfPetName.setText(selectedPetViewModel.getPetNameProperty().get());
        tfSpecies.setText(selectedPetViewModel.getSpeciesProperty().get());
        tfPetAge.setText(selectedPetViewModel.getAgeProperty().get() + "");
        cbOwner.setValue(selectedPetViewModel.getPetOwnerProperty().get());
        cbVeterinarian.setValue(selectedPetViewModel.getVeterinarianProperty().get());

        ivPetImage.setImage(
                selectedPetViewModel.getPictureProperty().get() != null
                ? new Image(new ByteArrayInputStream(selectedPetViewModel.getPictureProperty().get()))
                : new Image(new File("src/assets/no_image.png").toURI().toString())
        );
    }

    private void resetForm() {
        tfValidationMapVet.values().forEach(lb -> lb.setVisible(false));
        lblVetPictureError.setVisible(false);
        tfValidationMapOwner.values().forEach(lb -> lb.setVisible(false));
        lblOwnerPictureError.setVisible(false);
        tfValidationMapPet.values().forEach(lb -> lb.setVisible(false));
        cbValidationMapPet.values().forEach(lb -> lb.setVisible(false));
        lblPetPictureError.setVisible(false);
    }
}
