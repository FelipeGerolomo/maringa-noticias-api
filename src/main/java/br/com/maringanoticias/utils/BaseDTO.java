package br.com.maringanoticias.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class BaseDTO implements Serializable {
	private static final long serialVersionUID = -1L;

	protected Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	protected Date dhInsert;

	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	protected Date dhUpdate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	protected Date dhDelete;

	protected Boolean flAtivo = true;
}
