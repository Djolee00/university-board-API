package rs.ac.bg.fon.boardapi.mapper;

import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;
import rs.ac.bg.fon.boardapi.model.Board;

public interface BoardMapper {

     Board boardPostDtoToBoard(BoardCreationDto boardCreationDto);
     BoardDto boardToBoardDto(Board board);
}
