package br.com.maringanoticias.domain.devicelog;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeviceLogService extends CrudBaseService<DeviceLogDTO, DeviceLogEntity, Long, DeviceLogRepository, DeviceLogMapper> {
    public DeviceLogService(DeviceLogRepository repository, DeviceLogMapper mapper) {
        super(repository, mapper);
    }
}
