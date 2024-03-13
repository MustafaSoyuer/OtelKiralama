package com.mustafa.dto.response;


import com.mustafa.domain.Ozellik;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllParentsResponseDto { /**
    private String ozellikName;
    private String parentId;
    private List<Ozellikler> ozellikler;

    public static class Ozellikler {
//        private String ozellikId;
//        private String parentName; //

    }
 */
    private String parentName;
    private String parentId;
    private List<Ozellik> ozellikler;

}
