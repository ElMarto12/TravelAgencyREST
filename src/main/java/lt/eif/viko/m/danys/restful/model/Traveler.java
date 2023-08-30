package lt.eif.viko.m.danys.restful.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Holds information about Travelers.
 *
 * @author Martynas Danys
 * @since 1.0
 */

@Entity
public class Traveler {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;


    public Traveler(){}

    public Traveler(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Traveler(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Traveler)){
            return false;
        }
        Traveler traveler = (Traveler) o;
        return Objects.equals(this.id, traveler.id)
                && Objects.equals(this.firstName, traveler.firstName)
                && Objects.equals(this.lastName, traveler.lastName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.firstName, this.lastName);
    }

    @Override
    public String toString(){
        return String.format("\tName = %s\n\t\t" + "Last Name = %s\n\t\t",
                this.firstName,
                this.lastName);
    }


}
