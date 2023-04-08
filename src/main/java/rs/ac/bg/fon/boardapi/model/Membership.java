package rs.ac.bg.fon.boardapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Membership {

    @EmbeddedId
    private MembershipId membershipId = new MembershipId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardId")
    @JsonBackReference
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeID")
    private Employee employee;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonDeserialize(using= LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "membership_status_id")
    private MembershipStatus membershipStatus;

    public Membership(Board board, Employee employee, LocalDate startDate, MembershipStatus membershipStatus) {
        this.board = board;
        this.employee = employee;
        this.startDate = startDate;
        this.membershipStatus = membershipStatus;
    }

    public Membership() {
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.membershipId.setEmployeeId(employee.getId());
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public MembershipId getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(MembershipId membershipId) {
        this.membershipId = membershipId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return Objects.equals(board, that.board) && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, employee);
    }

    @Override
    public String toString() {
        return "Membership{" +
                "employee=" +employee+
                ", membershipId=" + membershipId +
                ", startDate=" + startDate +
                ", membershipStatus=" + membershipStatus +
                '}';
    }
}
