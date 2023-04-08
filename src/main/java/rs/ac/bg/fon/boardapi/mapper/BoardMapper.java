package rs.ac.bg.fon.boardapi.mapper;

import rs.ac.bg.fon.boardapi.dto.BoardPostDto;
import rs.ac.bg.fon.boardapi.model.Board;

public interface BoardMapper {

     Board boardPostDtoToBoard(BoardPostDto boardPostDto);
}
