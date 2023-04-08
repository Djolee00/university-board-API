package rs.ac.bg.fon.boardapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import rs.ac.bg.fon.boardapi.model.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;


public record BoardPostDto(

        @NotBlank(message = "Name of Board is required field")
        String name,
        @JsonFormat(pattern = "yyyy/MM/dd")
        @JsonDeserialize(using= LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate startDate,

        @JsonFormat(pattern = "yyyy/MM/dd")
        @JsonDeserialize(using= LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate endDate,
        @NotNull
        BoardStatus boardStatus,

        Set<Membership> memberships

){

}
