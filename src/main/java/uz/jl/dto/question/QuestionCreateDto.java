package uz.jl.dto.question;

import lombok.*;
import uz.jl.dto.GenericBaseDto;
import uz.jl.entity.variant.Variant;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDto implements GenericBaseDto {
    private String subjectCode;
    private String levelCode;
    private Integer ball;
    private String title;
    private String context;
    private List<Variant> variants;
}
