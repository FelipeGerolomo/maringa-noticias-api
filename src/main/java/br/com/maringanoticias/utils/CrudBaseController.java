package br.com.maringanoticias.utils;

import com.querydsl.core.types.Predicate;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;

public abstract class CrudBaseController<TID, TDTO extends BaseDTO, TService extends CrudBaseService<TDTO, ?, TID, ?, ?>>
        extends BaseController<TService> {

    public CrudBaseController(TService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<TDTO> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Page<TDTO> find(@RequestParam Predicate predicate, Pageable pageable) {
        return service.find(predicate, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/query")
    public List<TDTO> find(Predicate predicate) {
        return service.find(predicate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public TDTO find(@PathVariable TID id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<Object> create(@RequestBody TDTO dto) {
        TDTO novoDto = service.create(dto);
        return new ResponseEntity<Object>(novoDto, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable TID id, @RequestBody TDTO dto) throws NotFoundException {
        TDTO novoDto = service.updateDTO(id, dto);
        return new ResponseEntity<Object>(novoDto, ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable TID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
