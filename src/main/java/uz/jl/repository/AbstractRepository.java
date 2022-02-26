package uz.jl.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import uz.jl.container.UNIContainer;
import uz.jl.properties.DbProperty;

import java.util.Objects;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class AbstractRepository {
    private static MongoDatabase instance;
    protected static final DbProperty property = UNIContainer.getBean(DbProperty.class);

    public MongoDatabase getDatabase() {
        if (Objects.isNull(instance)) {
            ConnectionString connectionString = new ConnectionString(property.get("db.url"));
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
            MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();
            com.mongodb.client.MongoClient mongoClient = MongoClients.create(clientSettings);
            instance = mongoClient.getDatabase(property.get("db.name"));
        }
        return instance;
    }
}
