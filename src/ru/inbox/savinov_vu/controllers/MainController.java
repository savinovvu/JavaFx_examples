package ru.inbox.savinov_vu.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.inbox.savinov_vu.mockBD.impls.CollectionAddressBook;
import ru.inbox.savinov_vu.model.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    CollectionAddressBook addressBook = new CollectionAddressBook();

    private ObservableList<Person> backupList;

    private Stage mainStage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView tableAddressBook;

    @FXML
    private Label labelCount;

    @FXML
    private TableColumn<Person, String> columnFIO;
    @FXML
    private TableColumn<Person, String> columnPhone;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;
    private ResourceBundle resourceBundle;


    private void updateCountLabel() {
        labelCount.setText(resourceBundle.getString("count")+": " + addressBook.getPersonList().size());
    }


    public void actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }
        Button clickedButton = (Button) source;
        switch (clickedButton.getId()) {
            case "btnAdd":
                editDialogController.setPerson(new Person());
                showDialog();
                addressBook.add(editDialogController.getPerson());
                break;
            case "btnEdit":
                editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                showDialog();
                break;
            case "btnDelete":
                addressBook.delete((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                return;
        }


    }

    private void showDialog() {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle(resourceBundle.getString("edit"));
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        tableAddressBook.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));

        initListeners();

        fillData();
        initLoader();
    }

    private void fillData() {
        addressBook.fillTestData();
        backupList = FXCollections.observableArrayList();
        backupList.addAll(addressBook.getPersonList());
        tableAddressBook.setItems(addressBook.getPersonList());
    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        addressBook.getPersonList().addListener((ListChangeListener.Change<? extends Person> c) -> updateCountLabel());
        tableAddressBook.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                showDialog();
            }
        });
    }


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;

    }

    public void actionSearch(ActionEvent actionEvent) {
        addressBook.getPersonList().clear();
        for (Person person : backupList) {
            if (person.getPhone().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                    person.getFio().toLowerCase().contains(txtSearch.getText().toLowerCase())     ) {
                addressBook.getPersonList().add(person);
            }
        }

    }
}
