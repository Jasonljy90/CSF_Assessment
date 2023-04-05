package ibf2022.batch1.csf.assessment.server.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.models.ReviewCount;
import ibf2022.batch1.csf.assessment.server.models.reviewCount;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;;

@Service
public class MovieService {
	private final RestTemplate restTemplate = new RestTemplate();
	private static final String MOVIE_API_URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";

	@Value("${moviesApi}")
	private String movieApiKey;

	@Autowired
	private MovieRepository movieRepo;

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviewsRepo(String query) {
		return movieRepo.searchReviewsRepo(query);
	}

	// https://api.nytimes.com/svc/movies/v2/reviews/search.json
	public List<String> searchReviews(String query) {
		// https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=godfather&api-key=yourkey
		String finalMovieReviewUrl = UriComponentsBuilder
				.fromUriString(MOVIE_API_URL)
				.queryParam("query", query)
				.queryParam("api-key", movieApiKey)
				.toUriString();
		System.out.println("Final URL: " + finalMovieReviewUrl);
		ResponseEntity<Review[]> resp = null;
		try {
			restTemplate.getForEntity(finalMovieReviewUrl, Review[].class);
			ResponseEntity<List<Review>> responseEntity = restTemplate.exchange(
					finalMovieReviewUrl,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<Review>>() {
					});
			List<Review> reviews = responseEntity.getBody();
			// return reviews.stream()
			// .map(Review::getTitle)
			// .map(Review::getRating)
			// .map(Review::getByline)
			// .map(Review::getHeadline)
			// .map(Review::getSummary)
			// .map(Review::getReviewUrl)
			// .map(Review::getImage)
			// .collect(Collectors.toList());
			int commentCount = 0;
			for (int i = 0; i < reviews.size(); i++) {
				ReviewCount reviewCount = new ReviewCount();
				reviewCount.setMovie(reviews.get(i).getTitle());
				// reviewCount.setCount(0);
				commentCount = movieRepo.countComments(reviewCount);
			}
			List<String> result = new ArrayList<>();
			return result;
		} catch (Error err) {
			List<String> result = new ArrayList<>();
			return result;
		}
	}

	public List<String> insertOrders(List<Review> reviews) {

		// Create a empty list to store id of orders
		List<String> id = new LinkedList<>();

		Collection<Document> docInserted = movieRepo.insertOrders(reviews);

		// Check of size is 0, if 0 means insertion into db unsuccessful
		if (docInserted.size() == 0) {
			id.add("-1");
			return id;
		}

		// Loop the document inserted to get the order id and store in an array
		for (Document x : docInserted) {
			id.add(x.getString("id"));
		}

		return id;
	}
	/*
	 * review.setTitle(o.getString("display_title"));
	 * review.setRating((o.getString("mpaa_rating")));
	 * review.setByline(o.getString("byline"));
	 * review.setHeadline(o.getString("headline"));
	 * review.setSummary(o.getString("summary_short"));
	 * review.setReviewURL(o.getString("link"));
	 * review.setImage(o.getString("multimedia.src"));
	 */
}
