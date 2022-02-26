package uz.jl.services;

import uz.jl.repository.AbstractRepository;

public abstract class AbstractService<R extends AbstractRepository> {
    protected final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }
}
