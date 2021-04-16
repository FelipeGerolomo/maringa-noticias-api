package br.com.maringanoticias.utils.predicates;


import br.com.maringanoticias.utils.DateUtil;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import org.springframework.data.querydsl.binding.MultiValueBinding;

import java.util.*;

public class DateTimePredicateManager implements MultiValueBinding<DateTimePath<Date>, Date> {
    @Override
    public Optional<Predicate> bind(DateTimePath<Date> path, Collection<? extends Date> values) {
        List<? extends Date> dates = new ArrayList<>(values);
        //Collections.sort(dates);
        if (dates.size() == 1) {
            return Optional.of(path.eq(dates.get(0)));
        } else {
            Date from = DateUtil.getStart(dates.get(0));
            Date to = DateUtil.getEnd(dates.get(1));
            return Optional.of(path.between(from, to));
        }
    }
}
