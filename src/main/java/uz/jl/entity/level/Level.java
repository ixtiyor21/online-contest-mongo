package uz.jl.entity.level;

import lombok.*;
import uz.jl.entity.GenericCollection;
import uz.jl.entity.question.Question;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Level extends GenericCollection {
    private String title;
    private String code;
    private String description;
    private List<Question> questions = new ArrayList<>();

    public Level(String title, String code, String description) {
        this.title = title;
        this.code = code;
        this.description = description;
    }
}
