package ru.sherievtest.DAO;

import org.springframework.stereotype.Component;
import ru.sherievtest.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    List<Person> people = new ArrayList<>();
    private static int ID = 0;
    {
        people.add(new Person("Astemir", ++ID));
        people.add(new Person("Bob", ++ID));
        people.add(new Person("Mike", ++ID));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPerson(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void add(Person person) {
        person.setId(++ID);
        people.add(person);
    }

    public void edit(Person person, int id) {
        Person personToBeUpdated = getPerson(id);
        personToBeUpdated.setName(person.getName());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
