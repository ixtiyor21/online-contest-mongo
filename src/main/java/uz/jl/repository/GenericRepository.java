package uz.jl.repository;

import uz.jl.criteria.GenericCriteria;
import uz.jl.dto.GenericDto;

import java.util.List;

public interface GenericRepository<D extends GenericDto, C extends GenericCriteria> {
    List<D> list(C criteria);
}
