package wiki.cwm.tiny.blog.api.dto;

import lombok.Data;

@Data
public class ListReq {

    private Integer page;
    private Integer size;
}
