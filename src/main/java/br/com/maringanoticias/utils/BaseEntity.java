package br.com.maringanoticias.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString(of = "id")
@MappedSuperclass
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<TDTO, TID> implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, name = "ID")
	protected @Id TID id;

	@Transactional
	public abstract TDTO getSimpleDTO();

	@Basic(optional = true)
	@Column(name = "FL_ATIVO")
	protected boolean flAtivo = true;

	@Basic(optional = true)
	@Column(name = "DH_INSERT")
	@CreatedDate
	protected LocalDateTime dhInsert;

	@Basic(optional = true)
	@Column(name = "DH_UPDATE")
	@LastModifiedDate
	protected LocalDateTime dhUpdate;

	@Basic(optional = true)
	@Column(name = "DH_DELETE")
	protected LocalDateTime dhDelete;
}
