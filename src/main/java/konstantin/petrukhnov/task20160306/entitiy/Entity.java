package konstantin.petrukhnov.task20160306.entitiy;

import java.util.Map;
import java.util.Set;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-03-07.
 */
public interface Entity {

    // Returns a unique identifier

    String getID();

    // Returns the sub-entities of this entity

    Set<Entity> getSubEntities();

    // Returns a set of key-value data belonging to this entity

    Map<String,String> getData();


}
