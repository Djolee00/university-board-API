package rs.ac.bg.fon.boardapi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import rs.ac.bg.fon.boardapi.dto.BoardCreationDto;
import rs.ac.bg.fon.boardapi.validator.constraint.DatesMatch;

import java.time.LocalDate;

public class DatesMatchValidator implements ConstraintValidator<DatesMatch, BoardCreationDto> {
    @Override
    public boolean isValid(BoardCreationDto boardCreationDto, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate startDate = boardCreationDto.startDate();
        LocalDate endDate = boardCreationDto.endDate();

        if (startDate == null || endDate == null) {
            return true;
        }

        return startDate.isBefore(endDate);
    }
}
