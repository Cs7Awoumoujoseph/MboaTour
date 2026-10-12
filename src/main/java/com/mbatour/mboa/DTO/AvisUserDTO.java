package com.mbatour.mboa.DTO;

import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvisUserDTO {
    private Long id;
    private Long siteId;
    private Integer note;
    private String commentaire;
    private Date datePublication;
}