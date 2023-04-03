package rs.ac.bg.fon.boardapi.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.boardapi.dto.BoardPostDto;
import rs.ac.bg.fon.boardapi.mapper.BoardMapper;
import rs.ac.bg.fon.boardapi.model.Board;
import rs.ac.bg.fon.boardapi.model.Employee;
import rs.ac.bg.fon.boardapi.model.MembershipStatus;

import java.util.Map;

@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public Board boardPostDtoToBoard(BoardPostDto boardPostDto) {
        if (boardPostDto == null)
            return null;

        Board board = new Board();
        board.setName(boardPostDto.name());
        board.setStartDate(boardPostDto.startDate());
        board.setEndDate(boardPostDto.endDate());
        board.setBoardStatus(boardPostDto.boardStatus());

        Map<Employee, MembershipStatus> memberships = boardPostDto.memberships();
        if (memberships != null) {
            for (Map.Entry<Employee, MembershipStatus> entry : memberships.entrySet()) {
                board.addEmployee(entry.getKey(), entry.getValue());
            }
        }

        return board;
    }


}
