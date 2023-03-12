package net.gruppa.group;

import net.gruppa.entity.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonList {

    private final List<Person> personList;

    public PersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Person> searchesByName(String name) {
        List<Person> queryAnswers = new ArrayList<>();
        for(Person p : personList) {
            if(p.getName().equalsIgnoreCase(name)) {
                queryAnswers.add(p);
            }
        }
        return queryAnswers;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public boolean checkInformation(String username, String password) {
        for(Person p : personList) {
            if(p.getUsername().equals(username)) {
                if(p.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Person getPersonFromLogin(String username, String password) {
        for(Person p : personList) {
            if(p.getUsername().equals(username)) {
                if(p.getPassword().equals(password)) {
                    return p;
                }
            }
        }
        return null;
    }
}
