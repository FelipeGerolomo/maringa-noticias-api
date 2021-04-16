package br.com.maringanoticias.utils;

import br.com.maringanoticias.utils.predicates.PredicateManager;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;


@Repository
public interface BaseRepository<TEntity extends BaseEntity<?, TID>, TID> extends JpaRepository<TEntity, TID>,
		QuerydslPredicateExecutor<TEntity>, QuerydslBinderCustomizer<EntityPathBase<TEntity>> {

	@Override
	default void customize(QuerydslBindings bindings, EntityPathBase<TEntity> entity) {
		bindings.bind(String.class)
				.all((MultiValueBinding<StringPath, String>) (path,value) ->
						new PredicateManager<String, StringPath>(value, path)
								.checkStringPredicateFilter().apply());
	}

}
