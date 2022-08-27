package wiki.cwm.tiny.blog.api.service.bo;

import lombok.Data;
import wiki.cwm.tiny.blog.api.common.StarTypeEnum;

@Data
public class StartBO {

    Long postId;

    StarTypeEnum starType;

}
