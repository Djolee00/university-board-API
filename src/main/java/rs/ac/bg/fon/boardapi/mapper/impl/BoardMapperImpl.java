package rs.ac.bg.fon.boardapi.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.boardapi.dto.BoardPostDto;
import rs.ac.bg.fon.boardapi.mapper.BoardMapper;
import rs.ac.bg.fon.boardapi.model.Board;
import rs.ac.bg.fon.boardapi.model.Employee;
import rs.ac.bg.fon.boardapi.model.Membership;
import rs.ac.bg.fon.boardapi.model.MembershipStatus;
import rs.ac.bg.fon.boardapi.service.EmployeeService;

import java.util.Map;

@Component
public class BoardMapperImpl implements BoardMapper {

    private final EmployeeService employeeService;

    @Autowired
    public BoardMapperImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Board boardPostDtoToBoard(BoardPostDto boardPostDto) {
        if (boardPostDto == null)
            return null;

        Board board = new Board();
        board.setName(boardPostDto.name());
        board.setStartDate(boardPostDto.startDate());
        board.setEndDate(boardPostDto.endDate());
        board.setBoardStatus(boardPostDto.boardStatus());

        if (boardPostDto.memberships() != null) {
            boardPostDto.memberships().forEach(m -> {
                    m.setEmployee(employeeService.getById(m.getEmployee().getId()));
                    board.addMembership(m);
            });
        }
        return board;
    }


}
