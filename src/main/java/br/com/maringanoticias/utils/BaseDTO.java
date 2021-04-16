package br.com.maringanoticias.utils;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class BaseDTO implements Serializable {
	private static final long serialVersionUID = -1L;

	protected Long id;

	protected LocalDateTime dhInsert;

	protected LocalDateTime dhUpdate;

	protected LocalDateTime dhDelete;

	protected Boolean flAtivo = true;
}
