import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

/**
 * Created by markarquette on 4/14/16.
 */
public class DemoUser {

    public static void main(String[] args) {

        //3 main functional areas

        //1.. Setup client application config and connection stuff
        ManagerUser myApp = new ManagerUser();

        //2.. Setup Mapping and binding of Objects to C* tables
        MappingManager manager = new MappingManager(myApp.getSession());
        Mapper<EntityUser> mapper = manager.mapper(EntityUser.class);
        EntityUser testInsert = new EntityUser("test user01","my address 3333 Evergreen CO","444-444-4444","dwell-time",4444);

        //3.. Execute CRUD
        mapper.setDefaultSaveOptions(Mapper.Option.ttl(3600));
        mapper.setDefaultSaveOptions(Mapper.Option.saveNullFields(false));
        mapper.setDefaultSaveOptions(Mapper.Option.tracing(true));
        mapper.save(testInsert);
        //mapper.save(EntityUser);

        myApp.close();

    }

}
