package wiki.cwm.tiny.blog.api.service.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import wiki.cwm.tiny.blog.api.common.StarTypeEnum;
import wiki.cwm.tiny.blog.api.dto.StarDTO;

@Data
@NoArgsConstructor
public class StarBO {

    Long postId;

    StarTypeEnum starType;

    public StarBO(StarDTO dto) {
        this.postId = dto.getPostId();
        this.starType = StarTypeEnum.valueOfCode(dto.getType());
    }

}
