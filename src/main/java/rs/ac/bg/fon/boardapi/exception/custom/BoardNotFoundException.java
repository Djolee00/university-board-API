package rs.ac.bg.fon.boardapi.exception.custom;

public class BoardNotFoundException extends RuntimeException{

    public BoardNotFoundException(Long id) {
        super("Board with id " + id + " not found");
    }
}
