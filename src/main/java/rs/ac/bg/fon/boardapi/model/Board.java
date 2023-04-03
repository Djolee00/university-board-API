package rs.ac.bg.fon.boardapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "board_status_id")
    private BoardStatus boardStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Set<BoardFile> boardFiles;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Membership> memberships;

    public Board(String name, LocalDate startDate, LocalDate endDate, BoardStatus boardStatus, Set<BoardFile> boardFiles, Set<Membership> memberships) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.boardStatus = boardStatus;
        this.boardFiles = boardFiles;
        this.memberships = memberships;
    }

    public Board() {
    }

    public void addBoardFile(BoardFile boardFile) {
        if (boardFile != null) {
            if (boardFiles == null) {
                boardFiles = new HashSet<>();
            }
            boardFiles.add(boardFile);
        }
    }

    public void addEmployee(Employee employee, MembershipStatus membershipStatus){
        if(employee != null && membershipStatus != null){
            Membership membership = new Membership(this,employee,LocalDate.now(),membershipStatus);
            memberships.add(membership);
            employee.getMemberships().add(membership);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BoardStatus getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(BoardStatus boardStatus) {
        this.boardStatus = boardStatus;
    }

    public Set<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<Membership> memberships) {
        this.memberships = memberships;
    }

    public Set<BoardFile> getBoardFiles() {
        return boardFiles;
    }

    public void setBoardFiles(Set<BoardFile> boardFiles) {
        this.boardFiles = boardFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id) && Objects.equals(name, board.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


}
