package rs.ac.bg.fon.boardapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;
import rs.ac.bg.fon.boardapi.exception.custom.BoardNotFoundException;
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
        addFiles(files, board);
        Board createdBoard = boardRepository.save(board);
        return boardMapper.boardToBoardDto(createdBoard);
    }

    @Override
    public Page<BoardDto> getAll(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);

        return boards.map(boardMapper::boardToBoardDto);
    }

    @Override
    public BoardDto getById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(id));
        return boardMapper.boardToBoardDto(board);
    }

    @Override
    public Page<BoardDto> findBySearchCriteria(Specification<Board> spec, Pageable page) {
        Page<Board> boards = boardRepository.findAll(spec,page);
        return boards.map(boardMapper::boardToBoardDto);
    }

    @Override
    public BoardDto update(Long id, BoardCreationDto updatedBoard, MultipartFile[] files) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(id));

        board.setBoardStatus(updatedBoard.boardStatus());
        board.setName(updatedBoard.name());
        board.setMemberships(updatedBoard.memberships());
        board.setStartDate(updatedBoard.startDate());
        board.setEndDate(updatedBoard.endDate());

        if(files != null){
            board.setBoardFiles(null);
            addFiles(files,board);
        }

        return boardMapper.boardToBoardDto(boardRepository.save(board));
    }


    private static void addFiles(MultipartFile[] files, Board board) {
        if (files != null && files.length > 0) {
            Arrays.stream(files).forEach(file -> {
                try {
                    board.addBoardFile(new BoardFile(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())), file.getContentType(), file.getBytes()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
