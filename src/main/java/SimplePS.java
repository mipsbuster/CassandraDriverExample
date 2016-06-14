import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

/**
 * Created by markarquette on 4/9/16.
 */
public class SimplePS {

    private Session session;
    private Cluster cluster;
    private String userName;
    private String userPass;
    private String seedNode;

    public void connect() {
        setCluster( Cluster.builder()
                .addContactPoint(getSeedNode())
                .withCredentials("cassandra","cassandra")
                .build()
        );

        setSession((Session) getCluster().connect());

    }

    public void close() {
        cluster.close();
    }

    public void printInfo() {
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n",
                metadata.getClusterName());
        for ( Host host : metadata.getAllHosts() ) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }
    }

    public ResultSet getRows(String keyspace, String table) {
        Select query =  QueryBuilder.select().all().from(keyspace, table);
        return (ResultSet) getSession().execute(query).all();
    }

    public static void main(String[] args) {
        SimplePS myPS= new SimplePS();

        myPS.setSeedNode("127.0.0.2");
        myPS.connect();
        myPS.printInfo();

        myPS.session.execute("insert into mykeyspace.mytable  (id, address, name, phone ) values (1234, 'test','tester','555-555-555')");
/*
        Query insert = QueryBuilder.insertInto("mykeyspace", "mytable")
                .value("id", "12")
                .value("address", "Golden Brown")
                .value("name", "La Folie")
                .value("phone", "444-333-2222")
                .setConsistencyLevel(ConsistencyLevel.ONE).enableTracing()
                ;
                */
       // myPS.session.execute((Statement) insert);
        //ExecutionInfo executionInfo = results.getExecutionInfo();


        myPS.close();

    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getSeedNode() {
        return seedNode;
    }

    public void setSeedNode(String seedNode) {
        this.seedNode = seedNode;
    }
}

