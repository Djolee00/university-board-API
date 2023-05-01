package rs.ac.bg.fon.boardapi.search.specification;

import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;
import rs.ac.bg.fon.boardapi.model.Board;
import rs.ac.bg.fon.boardapi.model.Employee;
import rs.ac.bg.fon.boardapi.model.Membership;
import rs.ac.bg.fon.boardapi.search.SearchCriteria;
import rs.ac.bg.fon.boardapi.search.SearchOperation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class BoardSpecification implements Specification<Board> {

    private final SearchCriteria searchCriteria;

    public BoardSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Board> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){

            case CONTAINS -> {
                if(searchCriteria.getFilterKey().equals("firstName") || searchCriteria.getFilterKey().equals("lastName"))
                    return criteriaBuilder.like(employeeJoin(root).<String>get(searchCriteria.getFilterKey()),"%"+strToSearch+"%");

                return criteriaBuilder.like(root.get(searchCriteria.getFilterKey()),"%"+strToSearch+"%");
            }
            case LESS_THAN_EQUAL -> {
                return criteriaBuilder.lessThanOrEqualTo(root.<LocalDate>get(searchCriteria.getFilterKey()), LocalDate.parse(strToSearch, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            }
            case GREATER_THAN_EQUAL -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get(searchCriteria.getFilterKey()), LocalDate.parse(strToSearch, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            }
            case EQUAL -> {
                if(searchCriteria.getFilterKey().equals("empFirstname") || searchCriteria.getFilterKey().equals("empLastname"))
                    return criteriaBuilder.equal(employeeJoin(root).<String>get(searchCriteria.getFilterKey()),"%"+strToSearch+"%");

                return criteriaBuilder.equal(root.get(searchCriteria.getFilterKey()),strToSearch);
            }
            default -> throw new IllegalArgumentException("Specified value doesn't exist");
        }
    }

    private Join<Board, Employee> employeeJoin(Root<Board> root){
        return root.join("memberships").join("employee");
    }
}
