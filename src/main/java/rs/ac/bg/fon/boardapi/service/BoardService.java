package rs.ac.bg.fon.boardapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;
import rs.ac.bg.fon.boardapi.model.Board;

public interface BoardService {
    BoardDto create(BoardCreationDto boardCreationDto, MultipartFile[] files);

    Page<BoardDto> getAll(Pageable pageable);

    BoardDto getById(Long id);

    Page<BoardDto> findBySearchCriteria(Specification<Board> spec, Pageable page);

    BoardDto update(Long id, BoardCreationDto updatedBoard, MultipartFile[] files);
}
