package uz.jl.entity.variant;

import lombok.*;
import uz.jl.entity.GenericCollection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Variant extends GenericCollection {
    private String context;
    private String code;
    private Boolean isTrue = false;
}
