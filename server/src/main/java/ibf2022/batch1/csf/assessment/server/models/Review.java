package ibf2022.batch1.csf.assessment.server.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRating() {
		return this.rating;
	}

	public void setByline(String byline) {
		this.byline = byline;
	}

	public String getByline() {
		return this.byline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setReviewURL(String reviewURL) {
		this.reviewURL = reviewURL;
	}

	public String getReviewURL() {
		return this.reviewURL;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public boolean hasImage() {
		return null != this.image;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	};

	public int getCommentCount() {
		return this.commentCount;
	}

	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}

	public static Review create(String json) throws IOException {
		Review review = new Review();
		try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
			JsonReader r = Json.createReader(is);
			JsonObject o = r.readObject();
			review.setTitle(o.getString("display_title"));
			review.setRating((o.getString("mpaa_rating")));
			review.setByline(o.getString("byline"));
			review.setHeadline(o.getString("headline"));
			review.setSummary(o.getString("summary_short"));
			review.setReviewURL(o.getString("link"));
			review.setImage(o.getString("multimedia.src"));
		}
		return review;
	}
}