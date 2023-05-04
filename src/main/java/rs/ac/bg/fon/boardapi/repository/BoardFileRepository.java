package rs.ac.bg.fon.boardapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.boardapi.model.BoardFile;


@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile,Long> {

}
