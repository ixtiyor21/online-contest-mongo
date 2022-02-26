package uz.jl.dto.level;

import lombok.*;
import uz.jl.dto.GenericBaseDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LevelCreateDto implements GenericBaseDto {
    private String subjectId;
    private String title;
    private String code;
    private String description;
}
