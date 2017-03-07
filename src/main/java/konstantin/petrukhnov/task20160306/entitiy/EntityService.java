package konstantin.petrukhnov.task20160306.entitiy;

import io.advantageous.qbit.annotation.PathVariable;
import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.annotation.RequestMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-03-07.
 */
@Slf4j
@RequestMapping("/entity")
public class EntityService {

    //kind of persistence, in reality could be async calls to from dao layer to DB
    //root for entities
    private List<Entity> entityList = new ArrayList<>();
    // poorman lookup service
    private HashMap<String, Entity> entityMap = new HashMap<>();


    /**
     * Return list of all entities, for big number of entities, i would add default limit and pagination/search
     * @return
     */
    @RequestMapping("/getList")
    public List<Entity> getList() {
        return entityList;
    }

    /**
     * Adding entity to root. qbit doesn't support optional params, to merge this and 'add' methods
     * @param entity
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addToRoot(final EntityItem entity) {
        log.debug("addRoot: {}", entity.getID());
        entityList.add(entity);
        entityMap.put(entity.getID(), entity);
    }

    /**
     * Adding entity to other entity.
     * @param id of parent entity if any
     * @param entity
     */
    @RequestMapping(value = {"/add/{id}"}, method = RequestMethod.POST)
    public void add(@PathVariable("id") String id, final EntityItem entity) {
        log.debug("add: {}, {}", id, entity.getID());
        //add to other entity
        Set<Entity> subEntities = entityMap.get(id).getSubEntities();
        //create subentities if not exist yet
        if (subEntities == null) {
            subEntities = new HashSet<>();
            ((EntityItem)entityMap.get(id)).setSubEntities(subEntities);
        }
        //add subentity
        subEntities.add(entity);
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

    /**
     * Load initial data
     */
    public  EntityService() {
        Entity ent1 = EntityItem.builder().ID("ent1").build();
        entityList.add(ent1);
        entityMap.put(ent1.getID(), ent1);

        HashMap<String, String> ent2map = new HashMap<>();
        ent2map.put("k1", "val1");
        ent2map.put("abc","cde");
        Entity ent2 = EntityItem.builder().ID("ent2").data(ent2map).build();
        entityList.add(ent2);
        entityMap.put(ent2.getID(), ent2);
    }
}
