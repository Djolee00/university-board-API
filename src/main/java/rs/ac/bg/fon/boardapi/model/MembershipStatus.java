package rs.ac.bg.fon.boardapi.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "membership_status")
public class MembershipStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public MembershipStatus(String name) {
        this.name = name;
    }

    public MembershipStatus() {
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
        MembershipStatus that = (MembershipStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MembershipStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
