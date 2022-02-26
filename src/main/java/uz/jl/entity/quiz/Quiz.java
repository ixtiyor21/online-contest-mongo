package uz.jl.entity.quiz;

import lombok.*;
import uz.jl.entity.GenericCollection;
import uz.jl.entity.answers.Answers;
import uz.jl.entity.question.Question;
import uz.jl.entity.user.User;
import uz.jl.enums.QuizStatus;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Quiz extends GenericCollection {
    private User user;
    private Integer score = 0;
    private Integer maxScore = 0;
    private List<Answers> answers;
    private QuizStatus status = QuizStatus.START;
}
