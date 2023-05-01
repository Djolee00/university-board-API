package rs.ac.bg.fon.boardapi.dto;

import rs.ac.bg.fon.boardapi.search.SearchCriteria;

import java.util.List;

public record BoardSearchDto(
        List<SearchCriteria> searchCriteriaList,
        String dataOption
){}
