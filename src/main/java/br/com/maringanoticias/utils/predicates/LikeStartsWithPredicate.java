package br.com.maringanoticias.utils.predicates;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class LikeStartsWithPredicate implements PredicatesFilter<String, StringPath> {
    @Override
    public BooleanBuilder applyFilter(String value, StringPath path) {
        BooleanBuilder predicate = new BooleanBuilder();
        String finalValue = value.replaceFirst("%", "");
        return predicate.and(path.startsWithIgnoreCase(finalValue));
    }
}