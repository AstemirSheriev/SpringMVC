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

}
