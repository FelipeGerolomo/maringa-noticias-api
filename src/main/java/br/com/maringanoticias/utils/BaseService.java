package br.com.maringanoticias.utils;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseService<TRepository extends BaseRepository<?, ?>, TMapper extends BaseMapper<?, ?>> {

    protected TRepository repository;

    protected TMapper mapper;

    public BaseService(TRepository repository, TMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

}
