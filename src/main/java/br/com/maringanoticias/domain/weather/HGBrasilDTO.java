package br.com.maringanoticias.domain.weather;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class HGBrasilDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String temp;
    private String description;
    private String img_id;
    private String city_name;
}
