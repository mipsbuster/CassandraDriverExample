import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.UUID;

/**
 * Created by markarquette on 4/11/16.
 */

@Table(keyspace = "jumpstart", name = "user01")
public class EntityUser01 {

    @PartitionKey
    private UUID id;

    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    @Column(name="metric_name")
    private String metricName;
    @Column(name="metric_value")
    private int metricValue;

    public EntityUser01() {
    }

    public EntityUser01(String name, String address, String phone, String metricName, int metricValue) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.metricName = metricName;
        this.metricValue = metricValue;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public int getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(int metricValue) {
        this.metricValue = metricValue;
    }
}
