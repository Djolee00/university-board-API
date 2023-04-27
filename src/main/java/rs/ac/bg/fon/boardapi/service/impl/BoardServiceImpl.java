package rs.ac.bg.fon.boardapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;
import rs.ac.bg.fon.boardapi.mapper.BoardMapper;
import rs.ac.bg.fon.boardapi.model.Board;
import rs.ac.bg.fon.boardapi.model.BoardFile;
import rs.ac.bg.fon.boardapi.repository.BoardRepository;
import rs.ac.bg.fon.boardapi.service.BoardService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, BoardRepository boardRepository) {
        this.boardMapper = boardMapper;
        this.boardRepository = boardRepository;
    }

    @Override
    public BoardDto create(BoardCreationDto boardCreationDto, MultipartFile[] files) {
        Board board = boardMapper.boardPostDtoToBoard(boardCreationDto);
        if (files != null && files.length > 0) {
            Arrays.stream(files).forEach(file -> {
                try {
                     board.addBoardFile(new BoardFile(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())), file.getContentType(), file.getBytes()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return boardMapper.boardToBoardDto(boardRepository.save(board));
    }

    @Override
    public Page<BoardDto> getAll(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);

        return boards.map(boardMapper::boardToBoardDto);
    }
}
