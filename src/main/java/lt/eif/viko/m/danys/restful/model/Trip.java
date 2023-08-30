package lt.eif.viko.m.danys.restful.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Holds information about Trips.
 *
 * @author Martynas Danys
 * @since 1.0
 */

@Entity
public class Trip {

    private @Id @GeneratedValue Long id;
    private String organizationName;
    private String name;
    private String location;
    private String tripDuration;
    private Integer cost;

    public Trip(){}

    public Trip(Long id, String organizationName, String name, String location, String tripDuration, Integer cost) {
        this.id = id;
        this.organizationName = organizationName;
        this.name = name;
        this.location = location;
        this.tripDuration = tripDuration;
        this.cost = cost;
    }

    public Trip(String organizationName, String name, String location, String tripDuration, Integer cost) {
        this.organizationName = organizationName;
        this.name = name;
        this.location = location;
        this.tripDuration = tripDuration;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(String tripDuration) {
        this.tripDuration = tripDuration;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (!(o instanceof Trip)){
            return false;
        }
        Trip trip = (Trip) o;
        return Objects.equals(this.id, trip.id)
                && Objects.equals(this.name, trip.name)
                && Objects.equals(this.organizationName, trip.organizationName)
                && Objects.equals(this.cost, trip.cost)
                && Objects.equals(this.location, trip.location)
                && Objects.equals(this.tripDuration, trip.tripDuration);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.organizationName, this.cost, this.location, this.tripDuration);
    }

    @Override
    public String toString() {
        return String.format("\tTrip:\n\t\t\t\t" + "Organization Name = %s\n\t\t\t\t" + "Name = %s\n\t\t\t\t" +
                "Location = %s\n\t\t\t\t" + "Trip Duration = %s\n\t\t\t\t" + "Cost = %s\n\t\t\t\t",
                this.organizationName,
                this.name,
                this.location,
                this.tripDuration,
                this.cost);
    }

}
