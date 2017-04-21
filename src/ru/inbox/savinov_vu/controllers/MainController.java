package ru.inbox.savinov_vu.controllers;


import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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



    private void updateCountLabel() {
     labelCount.setText("Количество записей: " + addressBook.getPersonList().size());
    }


    public void showDialog(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }
        Button clickedButton = (Button) source;
        Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();
        switch (clickedButton.getId()) {
            case "btnAdd":
                System.out.println("add " + selectedPerson);
                break;
            case "btnEdit":
                System.out.println("edit " + selectedPerson);
                break;
            case "btnDelete":
                System.out.println("delete " + selectedPerson);
                return;
        }

        try {
            btnAdd.setText("clicked");
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/edit.fxml"));
            stage.setTitle("Редактирование записи");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        tableAddressBook.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));

        addressBook.getPersonList().addListener((ListChangeListener.Change<? extends Person> c)-> updateCountLabel());
        addressBook.fillTestData();
        tableAddressBook.setItems(addressBook.getPersonList());
        updateCountLabel();
    }
}
