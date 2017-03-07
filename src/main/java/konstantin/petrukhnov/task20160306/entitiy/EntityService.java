package konstantin.petrukhnov.task20160306.entitiy;

import io.advantageous.qbit.annotation.PathVariable;
import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.annotation.RequestMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-03-07.
 */
@Slf4j
@RequestMapping("/entity")
public class EntityService {

    //root for entities
    private List<Entity> entityList = new ArrayList<>();
    // poorman lookup service
    private HashMap<String, Entity> entityMap = new HashMap<>();


    @RequestMapping("/getList")
    public List<Entity> getList() {
        return entityList;
    }


    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public void add(@PathVariable("id") String id, final Entity entity) {
        log.error("add: {}, {}", id, entity.getID());
        if (id != null && !id.isEmpty()) {
            //add to other entity
            entityMap.get(id).getSubEntities().add(entity);
        } else {
            //add to root
            entityList.add(entity);
        }
        //add to map
        entityMap.put(entity.getID(), entity);
    }

    /**
     * Assuming that ids are never too long and not have special characters that unsupported by http, proxies, etc
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}")
    public Entity get(@PathVariable("id") String id) {
        return entityMap.get(id);
    }

    @RequestMapping("/test")
    public String qbittest() {
        return "234";
    }
}
