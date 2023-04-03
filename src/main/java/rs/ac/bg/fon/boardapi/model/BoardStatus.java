package rs.ac.bg.fon.boardapi.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "board_status")
public class BoardStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public BoardStatus(String name) {
        this.name = name;
    }

    public BoardStatus() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardStatus that = (BoardStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "BoardStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
