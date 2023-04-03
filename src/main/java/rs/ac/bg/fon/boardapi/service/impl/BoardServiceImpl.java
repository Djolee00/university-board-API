package rs.ac.bg.fon.boardapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.boardapi.dto.BoardPostDto;
import rs.ac.bg.fon.boardapi.mapper.BoardMapper;
import rs.ac.bg.fon.boardapi.model.Board;
import rs.ac.bg.fon.boardapi.model.BoardFile;
import rs.ac.bg.fon.boardapi.repository.BoardRepository;
import rs.ac.bg.fon.boardapi.service.BoardService;

import java.io.IOException;
import java.util.Arrays;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, BoardRepository boardRepository) {
        this.boardMapper = boardMapper;
        this.boardRepository = boardRepository;
    }

    @Override
    public Board create(BoardPostDto boardPostDto, MultipartFile[] files) {
        Board board = boardMapper.boardPostDtoToBoard(boardPostDto);

        Arrays.asList(files).stream().forEach(file -> {
            try {
                board.addBoardFile(new BoardFile(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e); //define custom exception
            }
        });

        return boardRepository.save(board);
    }
}
