package uz.jl.dto.subject;

import lombok.*;
import uz.jl.dto.GenericDto;
import uz.jl.entity.level.Level;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto extends GenericDto {
    private String id;
    private String title;
    private String code;
    private String description;
    private List<Level> levels;
}
