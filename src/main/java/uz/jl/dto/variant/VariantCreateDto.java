package uz.jl.dto.variant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.jl.dto.GenericBaseDto;
@Builder
@Getter
@Setter
public class VariantCreateDto implements GenericBaseDto {
    private String title;
    private String context;
    private String code;
    private Boolean isTrue = false;
}
