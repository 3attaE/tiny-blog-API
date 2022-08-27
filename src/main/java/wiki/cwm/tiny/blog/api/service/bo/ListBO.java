package wiki.cwm.tiny.blog.api.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListBO {

    List<PostBO> list;
    Long total;

}
