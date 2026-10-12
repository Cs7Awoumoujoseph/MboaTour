package com.mbatour.mboa.DTO;

import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCommentDTO {
    private Long id;
    private Long utilisateurId;
    private String nom;
    private String comment;
    private Integer note;
    private Date dateComment;
}