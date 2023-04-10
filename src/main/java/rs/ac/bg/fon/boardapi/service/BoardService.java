package rs.ac.bg.fon.boardapi.service;

import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;
import rs.ac.bg.fon.boardapi.model.Board;

public interface BoardService {
    BoardDto create(BoardCreationDto boardCreationDto, MultipartFile[] files);
}
