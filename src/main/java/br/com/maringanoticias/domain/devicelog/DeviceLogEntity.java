package br.com.maringanoticias.domain.devicelog;

import br.com.maringanoticias.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE_LOG")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@Where(clause = "DH_DELETE IS NULL")
public class DeviceLogEntity extends BaseEntity<DeviceLogDTO, Long> {
    private static final long serialVersionUID = 1L;

    @Basic(optional = true)
    @Column(name = "DS_DEVICE_OS")
    private String dsDeviceOS;

    @Basic(optional = true)
    @Column(name = "DS_DEVICE_IP")
    private String dsDeviceIP;

    @Basic(optional = true)
    @Column(name = "DS_DEVICE_MODEL")
    private String dsDeviceModel;

    @Basic(optional = true)
    @Column(name = "DS_APP_VERSION")
    private String dsAppVersion;

    @Override
    public DeviceLogDTO getSimpleDTO() {
        return DeviceLogDTO.builder().build();
    }

}
