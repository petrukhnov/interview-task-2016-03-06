package konstantin.petrukhnov.task20160306;

import io.advantageous.qbit.server.EndpointServerBuilder;
import io.advantageous.qbit.server.ServiceEndpointServer;
import konstantin.petrukhnov.task20160306.entitiy.EntityService;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-03-07.
 */
public class AppConfig {

    public static void main(String[] args) {
        ServiceEndpointServer server = new EndpointServerBuilder().setPort(8085).build();
        server.initServices(new EntityService());
        server.start();
    }



}
