package lt.eif.viko.m.danys.restful.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Holds information about Travel Agency Orders.
 *
 * @author Martynas Danys
 * @since 1.0
 */

@Entity
public class Order extends Trip{

    private @Id @GeneratedValue Long id;
    private String orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Traveler> travelers;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Trip> trips;

    public Order(){}

    public Order(Long id, String orderDate, List<Traveler> travelers, List<Trip> trips) {
        this.id = id;
        this.orderDate = orderDate;
        this.travelers = travelers;
        this.trips = trips;
    }

    public Order(String orderDate, List<Traveler> travelers, List<Trip> trips) {
        this.orderDate = orderDate;
        this.travelers = travelers;
        this.trips = trips;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<Traveler> getTravelers() {
        return travelers;
    }

    public void setTravelers(List<Traveler> travelers) {
        this.travelers = travelers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }

        if(!(o instanceof Order)){
            return false;
        }

        Order order = (Order) o;
        return Objects.equals(this.id, order.id)
                && Objects.equals(this.orderDate, order.orderDate)
                && Objects.equals(this.trips, order.trips)
                && Objects.equals(this.travelers, order.travelers);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.orderDate, this.trips, this.travelers);
    }

    @Override
    public String toString() {
        return String.format("Order:\n\t Date = %s\n\t" +
                        "Travelers: \n\t%s" +
                        "Trips:\n\t\t%s",
                this.orderDate,
                this.travelers,
                this.trips,
                constructTravelerString(),
                constructTripString());
    }

   private String constructTravelerString(){
        String resultTraveler = "";
        for(Traveler traveler : this.travelers){
            resultTraveler += traveler.toString();
        }
        return resultTraveler;
    }
    private String constructTripString(){
        String resultTrip = "";
        for(Trip trip : this.trips){
            resultTrip += trip.toString();
        }
        return resultTrip;
    }
}
