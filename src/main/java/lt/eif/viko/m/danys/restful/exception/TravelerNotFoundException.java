package lt.eif.viko.m.danys.restful.exception;

public class TravelerNotFoundException extends RuntimeException {

    public TravelerNotFoundException(Long id){
        super("Traveler Could not be found " + id);
    }

}
