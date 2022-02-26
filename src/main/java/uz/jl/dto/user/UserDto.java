package uz.jl.dto.user;

import lombok.*;
import uz.jl.dto.GenericDto;
import uz.jl.entity.quiz.Quiz;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends GenericDto {
    private String id;
    private String username;
    private String password;
    private String email;
    private Integer score;
    private List<Quiz> quizzes = new ArrayList<>();
}
