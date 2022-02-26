package uz.jl.services;

import uz.jl.criteria.GenericCriteria;
import uz.jl.dto.GenericDto;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;

import java.util.List;

public interface GenericService<D extends GenericDto, C extends GenericCriteria> {
    ResponseEntity<Data<List<D>>> list(C criteria);
}
