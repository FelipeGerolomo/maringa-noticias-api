package br.com.maringanoticias.utils;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class CrudBaseService<TDTO extends BaseDTO, TEntity extends BaseEntity<TDTO, TID>, TID, TRepository extends BaseRepository<TEntity, TID>, TMapper extends BaseMapper<TEntity, TDTO>>
		extends BaseService<TRepository, TMapper> {

	public CrudBaseService(TRepository repository, TMapper mapper) {
		super(repository, mapper);
	}

	public List<TDTO> findAll() {
		final List<TEntity> listEntity = this.repository.findAll();
		return this.mapper.toDTO(listEntity);
	}

	public Page<TDTO> find(Predicate predicate, Pageable pageable) {
		return this.mapper.toDTO(this.findEntity(predicate, pageable));
	}

	public List<TDTO> find(Predicate predicate) {
		return this.mapper.toDTO(this.findEntity(predicate));
	}

	public TDTO findById(TID id) {
		final TEntity entity = this.repository.findById(id).orElseThrow(() -> {
			return new RuntimeException(this.getClass().getSimpleName() + "@findById: " + this.getClass().getName()
					+ " with ID " + id + " not found!");
		});

		return this.mapper.toDTO(entity);
	}

	public TDTO create(TDTO dto) {
		TEntity entityNew = this.mapper.toEntity(dto);
		entityNew.setFlAtivo(true);
		entityNew = this.repository.saveAndFlush(entityNew);
		return this.mapper.toDTO(entityNew);
	}

	@Transactional(rollbackFor = Exception.class)
	public List<TEntity> saveAllEntity(List<TEntity> entitys) {
		return repository.saveAll(entitys);
	}

	@Transactional(rollbackFor = Exception.class)
	public List<TEntity> saveAllListEntity(List<TDTO> dtos) {
		List<TEntity> entitys = mapper.toEntity(dtos);
		return this.saveAllEntity(entitys);
	}

	@Transactional(rollbackFor = Exception.class)
	public TEntity update(TID id, TEntity entity) throws NotFoundException {
		repository.findById(id).orElseThrow(() -> {
			return new NotFoundException(getClass().getSimpleName() + "@update: " + this.getClass().getName()
					+ " with ID " + id + " not found!");
		});

		return repository.saveAndFlush(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public TDTO updateDTO(TID id, TDTO dto) throws NotFoundException {
		return mapper.toDTO(this.updateEntity(id, dto));
	}

	@Transactional(rollbackFor = Exception.class)
	public TEntity updateEntity(TID id, TDTO dto) throws NotFoundException {
		TEntity entityNew = mapper.toEntity(dto);
		return this.update(id, entityNew);
	}

	public void delete(TID id) {
		// verificar as FK com repositoryRelacionamento.existsById(id)
		this.repository.deleteById(id);
	}

	public Page<TEntity> findEntity(Predicate predicate, Pageable pageable) {
		return this.repository.findAll(predicate, pageable);
	}

	public List<TEntity> findEntity(Predicate predicate) {
		return Lists.newArrayList(this.repository.findAll(predicate));
	}

}
