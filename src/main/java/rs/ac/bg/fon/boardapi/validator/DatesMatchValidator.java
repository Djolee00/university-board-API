package rs.ac.bg.fon.boardapi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import rs.ac.bg.fon.boardapi.dto.BoardPostDto;
import rs.ac.bg.fon.boardapi.validator.constraint.DatesMatch;

import java.time.LocalDate;

public class DatesMatchValidator implements ConstraintValidator<DatesMatch, BoardPostDto> {
    @Override
    public boolean isValid(BoardPostDto boardPostDto, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate startDate = boardPostDto.startDate();
        LocalDate endDate = boardPostDto.endDate();

        if (startDate == null || endDate == null) {
            return true;
        }

        return startDate.isBefore(endDate);
    }
}
