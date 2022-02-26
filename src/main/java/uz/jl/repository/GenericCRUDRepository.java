package uz.jl.repository;

import uz.jl.dto.GenericBaseDto;
import uz.jl.dto.GenericDto;

import java.io.Serializable;

public interface GenericCRUDRepository<CD extends GenericBaseDto, UD extends GenericDto, D extends GenericDto, K extends Serializable> {
    K create(CD dto);

    Void update(UD dto);

    Void delete(K key);

    D get(K key);
}
