package br.com.maringanoticias.controller;

import br.com.maringanoticias.domain.devicelog.DeviceLogDTO;
import br.com.maringanoticias.domain.devicelog.DeviceLogEntity;
import br.com.maringanoticias.domain.devicelog.DeviceLogService;
import br.com.maringanoticias.utils.CrudBaseController;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device-log")
public class DeviceLogController extends CrudBaseController<Long, DeviceLogDTO, DeviceLogService> {

    public DeviceLogController(DeviceLogService service) {
        super(service);
    }

    @Override
    public Page<DeviceLogDTO>
    find(@QuerydslPredicate(root = DeviceLogEntity.class) Predicate predicate, Pageable pageable) {
        return super.find(predicate, pageable);
    }

    @Override
    public List<DeviceLogDTO> find(@QuerydslPredicate(root = DeviceLogEntity.class) Predicate predicate) {
        return super.find(predicate);
    }
}