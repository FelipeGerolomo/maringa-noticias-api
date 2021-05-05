package br.com.maringanoticias.domain.devicelog;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Transactional
public class DeviceLogService extends CrudBaseService<DeviceLogDTO, DeviceLogEntity, Long, DeviceLogRepository, DeviceLogMapper> {
    public DeviceLogService(DeviceLogRepository repository, DeviceLogMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public DeviceLogDTO create(DeviceLogDTO dto) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
        dto.setDsDeviceIP(ip);
        return super.create(dto);
    }
}
