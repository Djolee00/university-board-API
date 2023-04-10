package rs.ac.bg.fon.boardapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;
import rs.ac.bg.fon.boardapi.model.*;
import rs.ac.bg.fon.boardapi.service.BoardService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardDto> create(@Valid @RequestPart("board") BoardCreationDto boardCreationDto, @RequestPart(value = "files",required = false) MultipartFile[] files) throws IOException {
        BoardDto createdBoard = boardService.create(boardCreationDto,files);
        return new ResponseEntity<>(createdBoard,HttpStatus.CREATED);
    }
}
