package rs.ac.bg.fon.boardapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;
import rs.ac.bg.fon.boardapi.dto.BoardSearchDto;
import rs.ac.bg.fon.boardapi.search.SearchCriteria;
import rs.ac.bg.fon.boardapi.search.builder.BoardSpecificationBuilder;
import rs.ac.bg.fon.boardapi.service.BoardService;

import java.util.List;


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

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getById(@PathVariable Long id){
        BoardDto boardDto = boardService.getById(id);
        return ResponseEntity.ok(boardDto);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<BoardDto>> searchBoards(
            @RequestParam(name = "pageNum",defaultValue = "0") int pageNum,
            @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
            @RequestBody BoardSearchDto  boardSearchDto
            ){

        BoardSpecificationBuilder builder = new BoardSpecificationBuilder();
        List<SearchCriteria> criteriaList = boardSearchDto.searchCriteriaList();

        if(criteriaList!=null){
            criteriaList.forEach(x->
            {x.setDataOption(boardSearchDto.dataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize,
                Sort.by("name")
                        .ascending());

        Page<BoardDto> boardPage = boardService.findBySearchCriteria
                (builder.build(),page);

        return ResponseEntity.ok(boardPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardDto> update(@PathVariable Long id,
                                           @Valid @RequestPart("updatedBoard") BoardCreationDto updatedBoard,
                                           @RequestPart(value = "files",required = false) MultipartFile[] files){
        BoardDto updatedBoardDto = boardService.update(id,updatedBoard,files);
        return ResponseEntity.ok(updatedBoardDto);
    }
}
