package rs.ac.bg.fon.boardapi.search.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;
import rs.ac.bg.fon.boardapi.model.Board;
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
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())),"%"+strToSearch+"%");
            }
            case LESS_THAN_EQUAL -> {
                return criteriaBuilder.lessThanOrEqualTo(root.<LocalDate>get(searchCriteria.getFilterKey()), LocalDate.parse(strToSearch, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            }
            case GREATER_THAN_EQUAL -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get(searchCriteria.getFilterKey()), LocalDate.parse(strToSearch, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            }
            case EQUAL -> {
                return criteriaBuilder.equal(criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())),strToSearch);
            }
            default -> throw new IllegalArgumentException("Specified value doesn't exist");
        }
    }
}
