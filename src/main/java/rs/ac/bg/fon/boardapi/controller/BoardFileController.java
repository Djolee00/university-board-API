package rs.ac.bg.fon.boardapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.boardapi.model.BoardFile;
import rs.ac.bg.fon.boardapi.service.BoardFileService;

@RestController
@RequestMapping("/api/v1/board-files")
public class BoardFileController {

    private final BoardFileService boardFileService;

    @Autowired
    public BoardFileController(BoardFileService boardFileService) {
        this.boardFileService = boardFileService;
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getById(@PathVariable Long id){
        BoardFile boardFile = boardFileService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + boardFile.getName() + "\"")
                .body(boardFile.getData());
    }
}
