package rs.ac.bg.fon.boardapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import rs.ac.bg.fon.boardapi.model.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;


public record BoardPostDto(

        @NotBlank(message = "Name of Board is required field")
        String name,
        LocalDate startDate,
        LocalDate endDate,
        @NotNull
        BoardStatus boardStatus,
        Map<Employee, MembershipStatus> memberships

){

}
