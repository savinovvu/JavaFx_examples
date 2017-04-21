package ru.inbox.savinov_vu.mockBD.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.inbox.savinov_vu.mockBD.AddressBook;
import ru.inbox.savinov_vu.model.Person;

public class CollectionAddressBook implements AddressBook {

    private static ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }


    public void fillTestData() {
        personList.add(new Person("Иванов иван", "203948"));
        personList.add(new Person("Петров Петр", "65464654"));
        personList.add(new Person("Бегемотов бегемот", "123234234"));
        personList.add(new Person("Хороший хорош", "234234234"));

        personList.add(new Person("Моржов морж", "65432"));
    }
}
