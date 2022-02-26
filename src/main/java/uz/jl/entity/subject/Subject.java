package uz.jl.entity.subject;

import lombok.*;
import uz.jl.entity.GenericCollection;
import uz.jl.entity.level.Level;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends GenericCollection {
    private String title;
    private String code;
    private String description;
    private List<Level> levels = new ArrayList<>();

    public Subject(String title, String code, String description) {
        this.title = title;
        this.code = code;
        this.description = description;
    }
}
