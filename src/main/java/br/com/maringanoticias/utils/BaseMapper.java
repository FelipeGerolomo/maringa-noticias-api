package br.com.maringanoticias.utils;

import com.querydsl.core.Tuple;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class BaseMapper<TEntity, TDTO> {

	@Autowired
	private ModelMapper modelMapper;

	private final Class<TEntity> typeEntity;
	private final Class<TDTO> typeDTO;

	@SuppressWarnings("unchecked")
	public BaseMapper() {
		this.typeEntity = (Class<TEntity>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.typeDTO = (Class<TDTO>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	public List<TDTO> toDTO(Iterable<TEntity> entities) {
		return StreamSupport.stream(entities.spliterator(), false).map(this::toDTO).collect(Collectors.toList());
	}

	public List<TDTO> toDTO(List<TEntity> entities) {
		return entities.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public List<TDTO> toTupleDTO(List<Tuple> entities) {
		return entities.stream().map(t -> this.toDTO(t.get(0, this.typeEntity))).collect(Collectors.toList());
	}

	public TDTO toDTO(Optional<TEntity> optional) {
		return optional.isPresent() ? this.modelMapper.map(optional.get(), this.typeDTO) : null;
	}

	public Page<TDTO> toDTO(Page<TEntity> listEntity) {
		return listEntity.map(source -> BaseMapper.this.toDTO(source));
	}

	public void toDTO(TDTO dtoSource, TDTO dtoDestination) {
		this.modelMapper.map(dtoSource, dtoDestination);
	}

	public TDTO toDTO(TEntity entity) {
		return entity != null ? this.modelMapper.map(entity, this.typeDTO) : null;
	}

	public TDTO toTupleDTO(Tuple entity) {
		return entity != null ? this.modelMapper.map(entity.get(0, this.typeEntity), this.typeDTO) : null;
	}

	public List<TEntity> toEntity(Iterable<TDTO> dtos) {
		return StreamSupport.stream(dtos.spliterator(), false).map(this::toEntity).collect(Collectors.toList());
	}

	public List<TEntity> toEntity(List<TDTO> dtos) {
		return dtos.stream().map(this::toEntity).collect(Collectors.toList());
	}

	public Page<TEntity> toEntity(Page<TDTO> dtos) {
		return dtos.map(source -> BaseMapper.this.toEntity(source));
	}

	public TEntity toEntity(TDTO dto) {
		return dto != null ? this.modelMapper.map(dto, this.typeEntity) : null;
	}

	public void toEntity(TEntity entitySource, TEntity entityDestination) {
		this.modelMapper.map(entitySource, entityDestination);
	}

	public void toFullEntity(TEntity entitySource, TEntity entityDestination) {
		this.modelMapper.map(entitySource, entityDestination);
	}

}
