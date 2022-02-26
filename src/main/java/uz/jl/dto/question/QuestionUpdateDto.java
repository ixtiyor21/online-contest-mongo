package uz.jl.dto.question;

import lombok.*;
import uz.jl.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUpdateDto extends GenericDto {
    private String subjectId;
    private Integer ball;
    private String title;
    private String context;
}
