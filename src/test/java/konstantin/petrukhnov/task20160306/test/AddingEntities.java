package konstantin.petrukhnov.task20160306.test;

import com.google.common.collect.ImmutableBiMap;
import konstantin.petrukhnov.task20160306.entitiy.Entity;
import konstantin.petrukhnov.task20160306.entitiy.EntityItem;
import konstantin.petrukhnov.task20160306.entitiy.EntityService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * Created by konstantin.petrukhnov@gmail.com on 2017-03-08.
 */
public class AddingEntities {

    private static EntityService entityService;

    @BeforeClass
    public static void init() {
        //no need to mock anything, as service is not connected anywhere
        entityService = new EntityService();
    }

    /**
     * Validate that init have data.
     */
    @Test
    public void initHaveData() {
        assertThat(entityService.getList().size(), is(2));
    }


    @Test
    public void addtoRoot() {
        String entityId = "ent3";
        entityService.addToRoot(EntityItem.builder().ID(entityId).data(ImmutableBiMap.of("key1","val1","key2","val2")).build());
        int mapSize = entityService.get(entityId).getData().size();
        assertThat(mapSize, is(2));
    }

    /**
     * Validate that entities added to other entities appear in list, and could be retrieved from parent entity
     */
    @Test
    public void addSubNode() {
        String parentEntityId = "ent1";
        String entityId = "ent11";
        entityService.add(parentEntityId, EntityItem.builder().ID(entityId).data(ImmutableBiMap.of("key11","val11")).build());
        int mapSize = entityService.get(entityId).getData().size();
        //subentity added toi list
        assertThat(entityService.getList().size(), is(2));
        //subentity added to parent
        String value11 = (new ArrayList<Entity>(entityService.get(parentEntityId).getSubEntities())).get(0).getData().get("key11");
        assertThat(value11, is("val11"));
    }

}
