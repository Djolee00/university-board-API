package rs.ac.bg.fon.boardapi.service;

import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardPostDto;
import rs.ac.bg.fon.boardapi.model.Board;

import java.io.IOException;

public interface BoardService {
    Board create(BoardPostDto boardPostDto, MultipartFile[] files);
}
