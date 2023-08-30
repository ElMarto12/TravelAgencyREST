package lt.eif.viko.m.danys.restful.exception;

public class TripNotFoundException extends RuntimeException{

    public TripNotFoundException(Long id){
        super("Trip Could not be found " + id);
    }
}
