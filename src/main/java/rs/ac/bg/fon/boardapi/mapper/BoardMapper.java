package rs.ac.bg.fon.boardapi.mapper;

import org.mapstruct.Mapper;
import rs.ac.bg.fon.boardapi.dto.BoardPostDto;
import rs.ac.bg.fon.boardapi.model.Board;

@Mapper(
       componentModel = "spring"
)
public interface BoardMapper {

     Board boardPostDtoToBoard(BoardPostDto boardPostDto);
}
