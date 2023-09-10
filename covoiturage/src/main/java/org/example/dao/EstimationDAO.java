package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;

import org.bson.Document;
import org.example.model.Estimation;

public class EstimationDAO {

    private MongoClient mongoClient;
    private static MongoDatabase database;

    public EstimationDAO() {
        mongoClient = MongoClients.create("mongodb://localhost:27017"); // Replace with your MongoDB connection string
        database = mongoClient.getDatabase("db-tp");

    }

        public static List<Estimation> getEstimationsByUserId(long userId) {
            MongoCollection<Document> collection = database.getCollection("estimations"); // Replace with your collection name

            FindIterable<Document> results = collection.find(new Document("userId", userId));

            List<Estimation> estimations = new ArrayList<>();

            try (MongoCursor<Document> cursor = results.iterator()) {
                while (cursor.hasNext()) {
                    Document document = cursor.next();

                    // Map the MongoDB document to your Estimation model
                    Estimation estimation = mapDocumentToEstimation(document);

                    estimations.add(estimation);
                }
            }

            return estimations;
        }

        private static Estimation mapDocumentToEstimation(Document document) {
            // Implement the logic to map a MongoDB document to your Estimation model
            Estimation estimation = new Estimation();
            estimation.setEstimationId(document.getLong("estimation_id"));
            estimation.setComment(document.getString("comment"));
            estimation.setRating(document.getDouble("rating"));
            // Map other fields as needed

            return estimation;
        }

    public int getTopRatingByUserId(long userIdLong) {
        MongoCollection<Document> collection = database.getCollection("estimations");
        FindIterable<Document> results = collection.find(new Document("userId", userIdLong));
        int topRating = 0;
        try (MongoCursor<Document> cursor = results.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                if (document.getDouble("rating") > topRating) {
                    topRating = document.getInteger("rating");
                }
            }
        }
        return topRating;
    }

    public int getLowRatingByUserId(long userIdLong) {
        MongoCollection<Document> collection = database.getCollection("estimations");
        FindIterable<Document> results = collection.find(new Document("userId", userIdLong));
        int lowRating = 5;
        try (MongoCursor<Document> cursor = results.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                if (document.getDouble("rating") < lowRating) {
                    lowRating = document.getInteger("rating");
                }
            }
        }
        return lowRating;
    }

    public Object getAverageRatingByUserId(long userIdLong) {
        MongoCollection<Document> collection = database.getCollection("estimations");
        FindIterable<Document> results = collection.find(new Document("userId", userIdLong));
        int averageRating = 0;
        int count = 0;
        try (MongoCursor<Document> cursor = results.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                averageRating += document.getInteger("rating");
                count++;
            }
        }
        return averageRating/count;
    }

    public void deleteEstimation(long id) {
        MongoCollection<Document> collection = database.getCollection("estimations");
        collection.deleteOne(new Document("estimation_id", id));
    }

    public void getEstimationTableModel() {
        MongoCollection<Document> collection = database.getCollection("estimations");
        FindIterable<Document> results = collection.find();
        try (MongoCursor<Document> cursor = results.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(document);
            }
        }
    }

    public void addEstimation(Estimation estimation) {
        MongoCollection<Document> collection = database.getCollection("estimations");
        Document document = new Document();
        document.append("estimation_id", estimation.getEstimationId());
        document.append("comment", estimation.getComment());
        document.append("rating", estimation.getRating());
        document.append("reservationId", estimation.getReservation().getReservationId());
        document.append("userId", estimation.getUser().getUserId());
        collection.insertOne(document);
    }

    public void getEstimationById(long estimationId) {
        MongoCollection<Document> collection = database.getCollection("estimations");
        FindIterable<Document> results = collection.find(new Document("estimation_id", estimationId));
        try (MongoCursor<Document> cursor = results.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(document);
            }
        }
    }

    public void updateEstimation(Estimation estimation) {
        MongoCollection<Document> collection = database.getCollection("estimations");
        collection.updateOne(new Document("estimation_id", estimation.getEstimationId()), new Document("$set", new Document("comment", estimation.getComment())));
        collection.updateOne(new Document("estimation_id", estimation.getEstimationId()), new Document("$set", new Document("rating", estimation.getRating())));
        collection.updateOne(new Document("estimation_id", estimation.getEstimationId()), new Document("$set", new Document("reservationId", estimation.getReservation().getReservationId())));
        collection.updateOne(new Document("estimation_id", estimation.getEstimationId()), new Document("$set", new Document("userId", estimation.getUser().getUserId())));
    }

    public void getAllEstimations() {
        MongoCollection<Document> collection = database.getCollection("estimations");
        FindIterable<Document> results = collection.find();
        try (MongoCursor<Document> cursor = results.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(document);
            }
        }
    }
}

