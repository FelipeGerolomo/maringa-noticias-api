package br.com.maringanoticias.utils.predicates;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class PredicateManager<T, t> {
    private Map<PredicatesFilter, String> predicates;
    Collection<? extends String> values;
    private t path;

    public PredicateManager(Collection<? extends String> values, t path) {
        this.path = path;
        this.values = values;
    }

	public PredicateManager<T, t> checkStringPredicateFilter() {
		this.predicates = new HashMap<>();

		this.values.forEach(value -> {
			this.predicates.put(this.resolveStringPredicate(value), value) ;
		});

		return this;
	}

	private PredicatesFilter resolveStringPredicate(String value) {
        
        if (value.startsWith("%") && value.endsWith("%")) {
            return new LikePredicate();
        }

        if (value.startsWith("%")) {
            return new LikeStartsWithPredicate();
        }

        if (value.endsWith("%")) {
            return new LikeEndsWithPredicate();
        }

        if (value.endsWith(Predicates.NOT_LIKE)) {
        	return new NotLikePredicate();
        }
        
        if (value.endsWith(Predicates.NOT_EQUALS)) {
        	return new NotEqualsPredicate();
        }

        return new EqualsPredicate();
    }

    public Optional<Predicate> apply() {
    	BooleanBuilder booleanPredicate = new BooleanBuilder();

    	predicates.forEach((key, value) -> {
        	booleanPredicate.or(key.applyFilter(value, this.path));
        });

        return Optional.of(booleanPredicate);
    }
}