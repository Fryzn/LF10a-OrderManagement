package net.gruppa.main;

import net.gruppa.entity.Address;
import net.gruppa.entity.Person;
import net.gruppa.group.PersonList;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    private final PersonList savedPerson;
    private final List<Person> personList;
    private Person loginedPerson;

    public DataHandler() {
        personList = new ArrayList<>();
        this.savedPerson = new PersonList(personList);
        personList.add(new Person(0,"enoerenberg","Enrico", "N\u00f6renberg", "1234", "enno@fian.de", new Address("Baum Allee", 21, 15663, "Berlin")));
        personList.add(new Person(1,"jofoltin", "Jonas", "Foltin", "1414", "jonas@fian.de", new Address("See Stra\u00dfe", 14, 14612, "Falkensee")));
        personList.add(new Person(2,"leritter", "Leon", "Ritter", "1010", "leon@fian.de", new Address("Gestern Weg", 1, 18710, "Berlin")));
        personList.add(new Person(3,"mamustermann", "Max", "Mustermann", "mamu", "max@fian.de", new Address("Mustermann Stra\u00dfe", 12, 12345, "Berlin")));
    }

    public void setLoginedPerson(Person loginedPerson) {
        this.loginedPerson = loginedPerson;
    }

    public Person getLoginedPerson() {
        return loginedPerson;
    }

    public PersonList getSavedPerson() {
        return savedPerson;
    }

    public void createPerson(Person employee) { personList.add(employee); }

    public void deletePerson(Person employee) { personList.remove(employee); }
}
