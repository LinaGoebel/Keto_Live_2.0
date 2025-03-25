package com.ketolive.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App {
    public static void main(String[] args) {
        // Подключаемся к MongoDB
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Выбираем базу данных
            MongoDatabase database = mongoClient.getDatabase("ketolive");

            // Выбираем коллекцию
            MongoCollection<Document> collection = database.getCollection("users");

            // Выводим количество документов в коллекции
            System.out.println("Количество пользователей: " + collection.countDocuments());

            // Создаем новый документ (пользователя)
            Document user = new Document()
                    .append("name", "Тестовый пользователь")
                    .append("email", "test@example.com")
                    .append("password", "hashed_password");

            // Вставляем документ в коллекцию
            collection.insertOne(user);
            System.out.println("Пользователь добавлен");

            // Проверяем, что документ добавлен
            System.out.println("Количество пользователей после добавления: " + collection.countDocuments());

            // Удаляем тестовый документ
            collection.deleteOne(new Document("email", "test@example.com"));
            System.out.println("Тестовый пользователь удален");
        }
    }
}
