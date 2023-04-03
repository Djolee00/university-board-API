package rs.ac.bg.fon.boardapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MembershipId implements Serializable {

    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "employee_id")
    private Long employeeId;

    public MembershipId(Long boardId, Long employeeId) {
        this.boardId = boardId;
        this.employeeId = employeeId;
    }

    public MembershipId() {
    }

    public Long getBoardId() {
        return boardId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembershipId that = (MembershipId) o;
        return Objects.equals(boardId, that.boardId) && Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, employeeId);
    }
}
