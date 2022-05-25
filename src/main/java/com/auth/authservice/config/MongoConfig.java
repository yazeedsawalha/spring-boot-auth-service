package com.auth.authservice.config;

import com.auth.authservice.entity.common.Language;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ConnectionPoolSettings;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String uri;
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        final ConnectionString connectionString = new ConnectionString(uri);
        final MongoClientSettings.Builder mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder -> builder.applySettings(connectionPoolSettings()))
                .codecRegistry(codecRegistry());
        return MongoClients.create(mongoClientSettings.build());
    }

    private CodecRegistry codecRegistry() {
        final CodecRegistry defaultCodecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        final CodecRegistry pojoRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        final CodecRegistry customCodecsRegistry = CodecRegistries.fromCodecs(customCodecs());
        return CodecRegistries.fromRegistries(defaultCodecRegistry, pojoRegistry, customCodecsRegistry);
    }

    ArrayList<Codec<?>> customCodecs() {
        ArrayList<Codec<?>> codecs = new ArrayList<>();
        codecs.add(new MongoDateCodec("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        codecs.add(new MongoEnumCodec<>(Language.class));
        return codecs;
    }


    private ConnectionPoolSettings connectionPoolSettings() {
        return ConnectionPoolSettings.builder()
                .maxSize(50)
                .maxWaitTime(20, TimeUnit.SECONDS)
                .maxConnectionIdleTime(20, TimeUnit.SECONDS)
                .maxConnectionLifeTime(60, TimeUnit.SECONDS).build();
    }

}
