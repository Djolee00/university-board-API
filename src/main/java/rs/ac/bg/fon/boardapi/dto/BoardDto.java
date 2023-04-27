package rs.ac.bg.fon.boardapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import rs.ac.bg.fon.boardapi.model.BoardStatus;

import java.time.LocalDate;

public record BoardDto(

        String name,
        @JsonFormat(pattern = "yyyy/MM/dd")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate startDate,
        @JsonFormat(pattern = "yyyy/MM/dd")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate endDate,
        BoardStatus boardStatus,
        int numOfFiles,
        int numOfMembers
) {
}
