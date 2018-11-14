package sixkiller.sample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationBeans {
     
     @Autowired
     public ApplicationBeans(Environment environment) {
        System.getProperties().setProperty("MONGODB_URI", "mongodb://admin:phongheo@realestate-shard-00-00-1iu3y.mongodb.net:27017,realestate-shard-00-01-1iu3y.mongodb.net:27017,realestate-shard-00-02-1iu3y.mongodb.net:27017/admin?replicaSet=RealEstate-shard-0&ssl=true");
     }
}
