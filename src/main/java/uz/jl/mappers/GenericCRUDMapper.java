package uz.jl.mappers;

import uz.jl.dto.GenericBaseDto;
import uz.jl.dto.GenericDto;
import uz.jl.entity.GenericCollection;

public interface GenericCRUDMapper<CD extends GenericBaseDto, UD extends GenericDto, D extends GenericDto, M extends GenericCollection> {
    CD toCreate(M model);

    UD toUpdate(M model);

    D to(M model);

    M fromCreate(CD dto);

    M fromUpdate(UD dto);

    M from(D dto);
}
