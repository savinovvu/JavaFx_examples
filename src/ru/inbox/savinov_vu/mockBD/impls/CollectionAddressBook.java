package ru.inbox.savinov_vu.mockBD.impls;

import ru.inbox.savinov_vu.mockBD.AddressBook;
import ru.inbox.savinov_vu.model.Person;

import java.util.ArrayList;
import java.util.List;

public class CollectionAddressBook implements AddressBook {

    private List<Person> personList = new ArrayList<>();

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

    public List<Person> getPersonList() {
        return personList;
    }
}
