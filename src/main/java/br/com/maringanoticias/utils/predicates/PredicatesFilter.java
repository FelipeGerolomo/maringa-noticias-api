package br.com.maringanoticias.utils.predicates;

import com.querydsl.core.BooleanBuilder;

interface PredicatesFilter <TValues, TPath>{
    BooleanBuilder applyFilter(TValues value, TPath path);
}