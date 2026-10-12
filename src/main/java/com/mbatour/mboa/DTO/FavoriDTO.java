package com.mbatour.mboa.DTO;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FavoriDTO{
       private Long id;
       private Long utilisateurId;
       private Long siteId;
       private Date dateAjout;

}