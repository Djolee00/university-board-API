package rs.ac.bg.fon.boardapi.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.dto.BoardDto;
import rs.ac.bg.fon.boardapi.mapper.BoardMapper;
import rs.ac.bg.fon.boardapi.model.Board;
import rs.ac.bg.fon.boardapi.service.EmployeeService;

@Component
public class BoardMapperImpl implements BoardMapper {

    private final EmployeeService employeeService;

    @Autowired
    public BoardMapperImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Board boardPostDtoToBoard(BoardCreationDto boardCreationDto) {
        if (boardCreationDto == null)
            return null;

        Board board = new Board();
        board.setName(boardCreationDto.name());
        board.setStartDate(boardCreationDto.startDate());
        board.setEndDate(boardCreationDto.endDate());
        board.setBoardStatus(boardCreationDto.boardStatus());

        if (boardCreationDto.memberships() != null) {
            boardCreationDto.memberships().forEach(m -> {
                    m.setEmployee(employeeService.getById(m.getEmployee().getId()));
                    board.addMembership(m);
            });
        }
        return board;
    }

    @Override
    public BoardDto boardToBoardDto(Board board) {
        return new BoardDto(board.getName(),board.getStartDate(),board.getEndDate(),
                board.getBoardStatus(),board.getBoardFiles()!=null ? board.getBoardFiles().size() : 0,
                board.getMemberships()!=null ? board.getMemberships().size():0);
    }


}
