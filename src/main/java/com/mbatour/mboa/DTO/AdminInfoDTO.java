package com.mbatour.mboa.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminInfoDTO {
  private Long administrateur_id;
  private Long nbreTotalSite ;
  private Long nbrSitePublie ;
  private Long nbrSiteBrouillon;


}
