package uz.jl.dto.subject;

import lombok.*;
import uz.jl.dto.GenericBaseDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubjectCreateDto implements GenericBaseDto {
    private String title;
    private String code;
    private String description;
}
