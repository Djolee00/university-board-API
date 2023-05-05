
# University boards management
API made as part of backend for University project for board management system. The main goal of the whole system is to provide employees of my faculty to create boards for different purposes, track currently active boards, members of boards and files which are associated with them.

Whole backend is done using Spring Boot 3. MySQL was used as DBMS. This project is currently in development but CRUD operations for Board domain are finished.






## Domain

![Screenshot 2023-05-05 213502](https://user-images.githubusercontent.com/93478227/236553410-39d62ae1-fcfb-4835-9cf7-fc5ceb369145.png)

Brief explanation of domain:
- Board - the main domain class. Every domain is only one status and contains zero or more board files, which are associated with it. Board can have multiple members, who have their status in it
- BoardFile - files (.pdf,.csv,.doc) uploaded for every board
- Employee - members of board. Every employee can be part of multiple boards
- BoardStatus - status of board (active, closed)
- MembershipStatus - status of member in board (president, member)


## Project structure

Three main parts of this Spring Boot application are:

    1) REST Controller
    2) Services
    3) Repository

Also I used DTO pattern with my custom mapper implemention for mapping DTOs to Domain object and reverse.

Thins I would like to highlight:
- Usage of Criteria API (advanced search/filter) + **Builder pattern**
- Pagination
- BLOBs for storage of files
- Custom constraints as annotations
- Custom mapper


## Advanced search/filter

In this section I would like to explain my implementation of custom search of boards using Criteria API.

First there is an enum (SearchOperation) which maps simple operations specified in payload of POST request for filtering into appropriate enum values.

```
public enum SearchOperation {

    CONTAINS, LESS_THAN_EQUAL, GREATER_THAN_EQUAL, EQUAL,
    ALL,ANY;

    public static final String[] SIMPLE_OPERATION_SET={
            "cn","le","ge","eq"
    };

    public static SearchOperation getDataOption(final String dataOption){
        return switch (dataOption) {
            case "all" -> ALL;
            case "any" -> ANY;
            default -> null;
        };
    }

    public static SearchOperation getSimpleOperation(final String input){
        return switch (input) {
            case "cn" -> CONTAINS;
            case "le" -> LESS_THAN_EQUAL;
            case "ge" -> GREATER_THAN_EQUAL;
            case "eq" -> EQUAL;
            default -> null;
        };
    }
}
```

Next there is a SearchCriteria class which represent single criteria in list of criterias in payload, by which we want to search our boards.

```
public class SearchCriteria {

    private String filterKey;
    private Object value;
    private String operation;
    private String dataOption;
    
    ...

}
```

The implemention of **Criteria API's Specification** is given below. The Spring JPA needs the implementation of Specification so it can search boards based on defined rules and criterias:

```

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
                    return criteriaBuilder.like(employeeJoin(root).get(searchCriteria.getFilterKey()),"%"+strToSearch+"%");

                return criteriaBuilder.like(root.get(searchCriteria.getFilterKey()),"%"+strToSearch+"%");
            }
            case LESS_THAN_EQUAL -> {
                return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getFilterKey()), LocalDate.parse(strToSearch, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            }

            ...
```

At the end we can one implementation of creational pattern called **Builder pattern**. The pain purpose of this BoardSpecificationBuilder is to facilitate the concatenation of criterias in the final specification. Concatenation is done using with() method and final build is done using build() method. There is option for ALL and ANY (OR) concatenation of criterias.

```
public class BoardSpecificationBuilder {

    private final List<SearchCriteria> params;

    public BoardSpecificationBuilder(){
        this.params = new ArrayList<>();
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
```

Usage of builder:

```
    @PostMapping("/search")
    public ResponseEntity<Page<BoardDto>> searchBoards(
            @RequestParam(name = "pageNum",defaultValue = "0") int pageNum,
            @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
            @RequestBody BoardSearchDto  boardSearchDto
            ){

        BoardSpecificationBuilder builder = new BoardSpecificationBuilder();
        List<SearchCriteria> criteriaList = boardSearchDto.searchCriteriaList();

        if(criteriaList!=null){
            criteriaList.forEach(x->
            {x.setDataOption(boardSearchDto.dataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize,
                Sort.by("name")
                        .ascending());

        Page<BoardDto> boardPage = boardService.findBySearchCriteria
                (builder.build(),page);

        return ResponseEntity.ok(boardPage);
    }
```

**Example of payload** (API Request for search/filter of boards):

![Screenshot 2023-05-05 220150](https://user-images.githubusercontent.com/93478227/236558089-e60e3332-aa19-4c3c-bc17-fd4312c419ef.png)





## Tech Stack

- **Spring Boot 3**
- **MySQL**
- **Java 17**


## Notes
- This project is still under development
- Things to be done: frontend, API documentation, dockerizing