package rs.ac.bg.fon.boardapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;
import rs.ac.bg.fon.boardapi.service.BoardService;


@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardDto> create(@Valid @RequestPart("board") BoardCreationDto boardCreationDto, @RequestPart(value = "files",required = false) MultipartFile[] files) {
        BoardDto createdBoard = boardService.create(boardCreationDto,files);
        return new ResponseEntity<>(createdBoard,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<BoardDto>> getAll(Pageable pageable){
        Page<BoardDto> page = boardService.getAll(pageable);
        return ResponseEntity.ok(page);
    }
}
