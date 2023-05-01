package rs.ac.bg.fon.boardapi.dto;

import rs.ac.bg.fon.boardapi.util.SearchCriteria;

import java.util.List;

public record BoardSearchDto(
        List<SearchCriteria> searchCriteriaList,
        String dataOption
){}
