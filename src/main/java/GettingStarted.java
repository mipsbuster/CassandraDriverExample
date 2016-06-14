import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import java.util.UUID;

/**
 * Created by markarquette on 4/10/16.
 */

public class GettingStarted {

    Cluster cluster;
    Session session;

    public Session connectSession() {

        cluster = Cluster.builder().addContactPoint("127.0.0.2")
                .withCredentials("cassandra", "cassandra")
                .withClusterName("mycluster")
                .build();
        session = cluster.connect("mykeyspace");
        return session;
    }

    public void close() {
        cluster.close();
    }

    public void createUsers(Session sessionIn) {
        //sessionIn.execute("create table")
    }

    public static void main(String[] args) {

        Session currSession;
        GettingStarted myApp = new GettingStarted();
        currSession = myApp.connectSession();

        MappingManager manager = new MappingManager(currSession);

        Mapper<MyTable> mapper = manager.mapper(MyTable.class);

        UUID userid = UUID.randomUUID();
        int userIDString = 333334;

        MyTable testInsert = new MyTable(userIDString,"testmapper with saveNull set","test address","111-1111");

        mapper.setDefaultSaveOptions(Mapper.Option.ttl(3600));
        mapper.setDefaultDeleteOptions(Mapper.Option.saveNullFields(false));
        mapper.save(testInsert);

        myApp.close();

    }
}
