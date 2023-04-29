package rs.ac.bg.fon.boardapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;

public interface BoardService {
    BoardDto create(BoardCreationDto boardCreationDto, MultipartFile[] files);

    Page<BoardDto> getAll(Pageable pageable);

    BoardDto getById(Long id);
}
