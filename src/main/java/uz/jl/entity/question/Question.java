package uz.jl.entity.question;

import lombok.*;
import uz.jl.entity.GenericCollection;
import uz.jl.entity.variant.Variant;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Question extends GenericCollection {
    private String subjectCode;
    private String levelCode;
    private Integer ball;
    private String title;
    private String context;
    private List<Variant> variants = new ArrayList<>();

    public Question( Integer ball, String context, String title) {
        this.ball = ball;
        this.context = context;
        this.title = title;
    }
}
