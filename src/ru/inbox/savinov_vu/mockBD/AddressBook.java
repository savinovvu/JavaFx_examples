package ru.inbox.savinov_vu.mockBD;

import ru.inbox.savinov_vu.model.Person;

public interface AddressBook {
    void add(Person person);

    void update(Person person);

    void delete(Person person);

}
