import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 * Created by markarquette on 4/14/16.
 */
public class ManagerUser {

    Cluster cluster;
    Session session;

    public void connectSession() {

        cluster = Cluster.builder().addContactPoint("127.0.0.2")
                .withCredentials("cassandra", "cassandra")
                .withClusterName("mycluster")
                .build();
        session = cluster.connect("jumpstart");
    }

    public void close() {
        cluster.close();
    }

    public ManagerUser() {
        this.connectSession();
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
