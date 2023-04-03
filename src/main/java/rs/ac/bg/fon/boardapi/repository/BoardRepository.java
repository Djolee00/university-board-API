package rs.ac.bg.fon.boardapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.boardapi.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
}
