package br.com.maringanoticias.utils.predicates;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class LikePredicate implements PredicatesFilter<String, StringPath> {
    @Override
    public BooleanBuilder applyFilter(String value, StringPath path) {
        BooleanBuilder predicate = new BooleanBuilder();
        String finalValue = value.substring(0, value.length() - 1).replaceFirst("%", "");
        return predicate.and(path.containsIgnoreCase(finalValue));
    }
}





