package rs.ac.bg.fon.boardapi.exception.custom;

public class BoardFileNotFoundException extends RuntimeException{

    public BoardFileNotFoundException(Long id) {
        super("Board file with id " + id + " not found");
    }

}
