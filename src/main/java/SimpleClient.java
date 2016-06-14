/**
 * Created by markarquette on 4/4/16.
 */

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleClient {
    private Cluster cluster;
    private Mapper mapper;
    private Session session;

    public void connect(String node) {

        cluster = Cluster.builder()
                .addContactPoint(node)
                .withCredentials("cassandra","cassandra")
                .build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n",
                metadata.getClusterName());
        for ( Host host : metadata.getAllHosts() ) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }
    }

    public void close() {
        cluster.close();
    }

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(SimpleClient.class);
        logger.info("Simple Client main start");

        SimpleClient client = new SimpleClient();

        client.connect("127.0.0.1");

        //Mapper mapper.save(SimpleClient, Mapper.Option.ttl(3600));

        com.datastax.driver.mapping.Mapper mapper;

        client.close();
    }
}