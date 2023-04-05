package ibf2022.batch1.csf.assessment.server;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.bson.Document;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Random;

import ibf2022.batch1.csf.assessment.server.models.Review;

public class Utils {
    // Convert json object to java object
    // public static Order toOrder(JsonObject json) {
    // Order order = new Order();
    // order.setName(json.getString("name"));
    // order.setEmail(json.getString("email"));
    // order.setDeliveryDate(json.getString("deliveryDate"));
    // List<LineItem> lineItem = json.getJsonArray("lineItems")
    // .stream()
    // .map(v -> (JsonObject) v) // Convert JsonObj String to JsonObj
    // .map(v -> toLineItem(v))
    // .toList();
    // order.setLineItem(lineItem);
    // return order;
    // }

    // Convert JsonObj to LineItem Obj
    // private static LineItem toLineItem(JsonObject json) {
    // LineItem lineItem = new LineItem();
    // lineItem.setItem(json.getString("item"));
    // lineItem.setQuantity(json.getInt("quantity"));
    // return lineItem;
    // }

    // Convert Json Obj String to Json Obj
    public static JsonObject toJson(String payload) {
        Reader reader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    // Convert Json Objects String to List of Java Obj
    // public static List<Order> toJsonList(String payload) throws
    // JsonMappingException, JsonProcessingException {
    // // Create a mapper
    // ObjectMapper mapper = new ObjectMapper();

    // // Convert Json Object Strings to Java object
    // List<Order> orderList = mapper.readValue(payload, new
    // TypeReference<List<Order>>() {
    // });

    // // Return the List
    // return orderList;
    // }

    // Convert lineItem obj to Document
    public static Document toDocument(Review review) {
        Document document = new Document();
        document.put("title", review.getTitle());
        document.put("rating", review.getRating());
        document.put("byline", review.getByline());
        document.put("headline", review.getHeadline());
        document.put("summary", review.getSummary());
        document.put("reviewURL", review.getReviewURL());
        document.put("image", review.getImage());
        return document;
    }

    /*
     * // summary_short
     * private String summary;
     * // link.url
     * private String reviewURL;
     * // multimedia.src
     * private String image = null;
     * 
     */

    // Convert Order obj to Document
    // public static Document toDocument(Order order) {
    // Document document = new Document();
    // document.put("id", order.getId());
    // document.put("name", order.getName());
    // document.put("email", order.getEmail());
    // document.put("deliveryDate", order.getDeliveryDate());

    // List<Document> docs = order.getLineItem()
    // .stream()
    // .map(v -> toDocument(v))
    // .toList();
    // document.put("lineItems", docs);
    // return document;
    // }

    // Convert Document to Order obj
    // public static Order toJava(Document document) {
    // Order order = new Order();
    // order.setId(document.get("id").toString());
    // order.setName(document.get("name").toString());
    // order.setEmail(document.get("email").toString());
    // order.setDeliveryDate(document.get("deliveryDate").toString());
    // return order;
    // }

    public static synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numChars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

    // Convert list of string to JsonArray of string
    public static JsonArray toJsonArr(List<String> orderIdList) {
        // Create a array builder to build the array of objects
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (String x : orderIdList) {
            // Add the string to the array builder
            arrBuilder.add(x);
        }

        // Build the array to get final Json Array
        JsonArray jsonArr = arrBuilder.build();
        return jsonArr;
    }
}
