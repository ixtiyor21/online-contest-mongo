package uz.jl.mappers;

import uz.jl.dto.GenericDto;
import uz.jl.entity.GenericCollection;

public interface GenericMapper<M extends GenericCollection, D extends GenericDto> {
    D toList(M model);

    M fromList(D dto);
}
