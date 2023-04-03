package rs.ac.bg.fon.boardapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardPostDto;
import rs.ac.bg.fon.boardapi.model.Board;
import rs.ac.bg.fon.boardapi.service.BoardService;

@RestController("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Board> create(@RequestPart("board") BoardPostDto boardPostDto, @RequestPart("files") MultipartFile[] files){
        Board createdBoard = boardService.create(boardPostDto,files);
        return ResponseEntity.ok(createdBoard);
    }
}
