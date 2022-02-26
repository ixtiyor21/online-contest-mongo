package uz.jl.dto.level;

import lombok.*;
import uz.jl.dto.GenericDto;
import uz.jl.entity.question.Question;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LevelDto extends GenericDto {
    private String subjectId;
    private String title;
    private String code;
    private String description;
    private List<Question> questions;
}
