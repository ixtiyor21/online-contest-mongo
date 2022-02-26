package uz.jl.dto.subject;

import lombok.*;
import uz.jl.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubjectUpdateDto extends GenericDto {
    private String id;
    private String title;
    private String code;
    private String description;
}
