package uz.jl.services;

import uz.jl.dto.GenericBaseDto;
import uz.jl.dto.GenericDto;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;

import java.io.Serializable;

public interface GenericCRUDService<CD extends GenericBaseDto, UD extends GenericDto, D extends GenericDto, K extends Serializable> {
    ResponseEntity<Data<K>> create(CD dto);

    ResponseEntity<Data<Void>> update(UD dto);

    ResponseEntity<Data<Void>> delete(K key);

    ResponseEntity<Data<D>> get(K key);
}
