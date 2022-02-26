package uz.jl.dto.level;

import lombok.*;
import uz.jl.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LevelUpdateDto extends GenericDto {
    private String subjectId;
    private String title;
    private String code;
    private String description;
}
