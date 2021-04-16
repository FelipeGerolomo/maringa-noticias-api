package br.com.maringanoticias.utils.predicates;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class EqualsPredicate implements PredicatesFilter<String, StringPath> {
    @Override
    public BooleanBuilder applyFilter(String value, StringPath path) {
        BooleanBuilder predicate = new BooleanBuilder();
        return predicate.and(path.equalsIgnoreCase(value));
    }
}