package com.example.Main;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MongoMigrate {
    //General
    public static final String MONGO_URI = "mongodb://%s:%s@%s:%d/%s";

    //Local
    public static final String LOCAL_HOST = "localhost";
    public static final Integer LOCAL_PORT = 27017;

    //Company
    public static final String COMPANY_HOST = "192.168.20.10";
    public static final Integer COMPANY_PORT = 27017;
    public static final String COMPANY_USER = "admin";
    public static final String COMPANY_PASS = "timebird%402020";
    public static final String COMPANY_DB = "admin";

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(MongoMigrate.class.getName());

        MongoClient local = new MongoClient(LOCAL_HOST, LOCAL_PORT);
        MongoClientURI comUri = new MongoClientURI(String.format(MONGO_URI,COMPANY_USER, COMPANY_PASS, COMPANY_HOST, COMPANY_PORT, COMPANY_DB));
        MongoClient company = new MongoClient(comUri);

        Scanner scan = new Scanner(System.in);
        System.out.println("Migrate DB: ");
        String dbName = scan.nextLine();
        String[] localDBNameArray = Arrays.stream(dbName.split("-")).filter(s -> !s.equals("cloud")).collect(Collectors.toList()).toArray(new String[0]);
        String localDbName = String.join("-", localDBNameArray);
        logger.info("DB name: " + dbName);

        logger.info("Get DB " + localDbName + " of local");
        MongoDatabase localDB = local.getDatabase(localDbName);
        MongoIterable<String> localCollectionNames = localDB.listCollectionNames();

        logger.info("Get DB " + dbName + " of company");
        MongoDatabase comDB = company.getDatabase(dbName);
        MongoIterable<MongoCollection<Document>> listCollection = comDB.listCollectionNames().map(s -> comDB.getCollection(s));

        for(MongoCollection<Document> collection : listCollection) {
            String collectionName = collection.getNamespace().getCollectionName();
            logger.info("Get collection: " + collectionName);
            if(isExistCollection(localCollectionNames, collectionName)){
                localDB.getCollection(collectionName).drop();
            }
            localDB.createCollection(collectionName);
            MongoCollection<Document> localCollection = localDB.getCollection(collectionName);
            FindIterable<Document> documents = collection.find();
            for(Document document : documents) {
                logger.info("Inserting: " + document.toJson());
                localCollection.insertOne(document);
                logger.info("Inserted");
            }
        }
    }

    public static boolean isExistCollection(MongoIterable<String> collectionNames, String name) {
        for(String colName : collectionNames) {
            if(colName.equals(name)) return true;
        }
        return false;
    }
}
