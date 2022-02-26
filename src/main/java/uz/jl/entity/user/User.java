package uz.jl.entity.user;

import lombok.*;
import uz.jl.entity.GenericCollection;
import uz.jl.entity.quiz.Quiz;
import uz.jl.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends GenericCollection {
    private String username;
    private String password;
    private String email;
    private Integer score;
    private UserRole role = UserRole.ANONYMOUS;
    private List<Quiz> quizzes = new ArrayList<>();

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.quizzes = new ArrayList<>();
    }

    public User(String username, String password, String email, Integer score, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.score = score;
        this.role = role;
        this.quizzes = new ArrayList<>();
    }
}
