package wiki.cwm.tiny.blog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResp {

    private List<PostDTO> list;
    private Long total;

}
