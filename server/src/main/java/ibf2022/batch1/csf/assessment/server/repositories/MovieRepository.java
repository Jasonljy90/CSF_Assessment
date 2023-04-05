package ibf2022.batch1.csf.assessment.server.repositories;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import ibf2022.batch1.csf.assessment.server.Utils;
import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.models.ReviewCount;

public class MovieRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Review> searchReviewsRepo(String query) {
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("movieName").is(query));
		return mongoTemplate.find(query1, Review.class);
	}

	public List<Review> searchReviews(String query) {
		return null;
	}

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	// db.movie_reviews.find({ "review": movieName })
	public int countComments(ReviewCount reviewCount) {
		int commentCount = 0;
		int count = reviewCount.getCount();
		// commentCount = getComments(reviewCount.getMovie());
		return count + commentCount;
	}

	public Collection<Document> insertOrders(List<Review> reviews) {

		// Create a linked list of document to store the docs that is to be inserted to
		// mongodb
		List<Document> docsToInsert = new LinkedList<>();

		// Convert List of Java object to List of Document
		for (Review x : reviews) {
			Document doc = Utils.toDocument(x);
			docsToInsert.add(doc);
		}

		return mongoTemplate.insert(docsToInsert, "comments");
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below

	// db.comments.insert({
	// movieName: "movieName"
	// posterName: "posterName"
	// rating: "rating"
	// comment: "comment"
	// })
	public Document insertComment(Comment comment) {
		Document doc = Utils.toDocument(comment);
		return mongoTemplate.insert(doc, "CSFAssessment");
	}
}
