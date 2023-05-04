package rs.ac.bg.fon.boardapi.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.boardapi.model.BoardFile;
import rs.ac.bg.fon.boardapi.repository.BoardFileRepository;
import rs.ac.bg.fon.boardapi.service.BoardFileService;

@Service
public class BoardFileServiceImpl implements BoardFileService {

    private final BoardFileRepository boardFileRepository;

    @Autowired
    public BoardFileServiceImpl(BoardFileRepository boardFileRepository) {
        this.boardFileRepository = boardFileRepository;
    }


    @Override
    public BoardFile getFile(Long id) {
        return boardFileRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
