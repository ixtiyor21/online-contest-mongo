package uz.jl.dto.question;

import lombok.*;
import uz.jl.dto.GenericDto;
import uz.jl.entity.variant.Variant;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto extends GenericDto {
    private String id;
    private Integer ball;
    private String title;
    private String context;
    private List<Variant> variants;
}
