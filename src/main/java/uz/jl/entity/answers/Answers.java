package uz.jl.entity.answers;

import lombok.*;
import uz.jl.entity.GenericCollection;
import uz.jl.entity.question.Question;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Answers extends GenericCollection {
    private String answer;
    private String value;
    private Question question;
    private Boolean isTrue = false;
}
