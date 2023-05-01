package rs.ac.bg.fon.boardapi.search.builder;

import org.springframework.data.jpa.domain.Specification;
import rs.ac.bg.fon.boardapi.model.Board;
import rs.ac.bg.fon.boardapi.search.SearchCriteria;
import rs.ac.bg.fon.boardapi.search.SearchOperation;
import rs.ac.bg.fon.boardapi.search.specification.BoardSpecification;

import java.util.ArrayList;
import java.util.List;

public class BoardSpecificationBuilder {

    private final List<SearchCriteria> params;

    public BoardSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final BoardSpecificationBuilder with(String key,String operation,Object value){
        params.add(new SearchCriteria(key,value,operation));
        return this;
    }

    public final BoardSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<Board> build(){
        if (params.size() == 0)
            return null;

        Specification<Board> result = new BoardSpecification(params.get(0));
        for(int i = 1;i<params.size();i++){
            SearchCriteria criteria = params.get(i);
            result = SearchOperation.getDataOption(criteria.getDataOption())
                    == SearchOperation.ALL ? Specification.where(result).and(new BoardSpecification(criteria))
                    : Specification.where(result).or(new BoardSpecification(criteria));
        }

        return result;
    }
}
