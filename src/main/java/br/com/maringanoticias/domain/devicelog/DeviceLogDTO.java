package br.com.maringanoticias.domain.devicelog;

import br.com.maringanoticias.utils.BaseDTO;
import lombok.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class DeviceLogDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dsDeviceOS;

    private String dsDeviceIP;

    private String dsDeviceModel;

    private String dsAppVersion;

    
}
