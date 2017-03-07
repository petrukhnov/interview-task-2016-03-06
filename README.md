# interview-task-2016-03-06

1. Make and git-publish a Java project that implements a simple REST API to

 - list Entity entries

 - send an Entity (to root, or attached to another entity)

 - read an Entity

2. uses a build framework familiar to you to build, run

3. uses one of the following to implement the server+logic

 - Vert.X

 - QBit

 - (or select and justify something else)

4. For simplicity

 - no persistence

 - no TLS

 - single-host, we don't worry about distribution or performance

 - lets test with curl -> select suitable payload format
 - use as many external libraries as you wish to simplify


For the entity, implement the following naive interface with an abstract base/utility/builder class and a sample concrete implementation:

    interface Entity {

        // Returns a unique identifier
    
        String getID();
    
        // Returns the sub-entities of this entity
    
        Set<Entity> getSubEntities();
    
        // Returns a set of key-value data belonging to this entity
    
        Map<String,String> getData();

    } 


Extra notes:
 - no circular dependencies in documents
 - all objects have valid uniqueIds
 - any payload format (json)
 - java8 is fine
 - no need for special error handling
