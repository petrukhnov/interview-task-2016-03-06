package konstantin.petrukhnov.task20160306.entitiy;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-03-07.
 */
@Data
@Builder
public class EntityItem implements Entity {
    private String ID;
    private Set<Entity> subEntities;
    private Map<String,String> data;
}
