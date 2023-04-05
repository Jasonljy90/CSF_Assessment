package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.Utils;
import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/api/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping()
	public ResponseEntity<String> byName(@RequestParam(name = "movieName") String movieName) {
		List<Review> resp = movieService.searchReviewsRepo(movieName);

		// return ResponseEntity.status(HttpStatus.OK)
		// .body(resp.toString());
		return null;
	}

	@PostMapping(path = "/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postOrder(@RequestBody String payload) {
		JsonObject jsonObj = Utils.toJson(payload);

		// Convert json object to java object
		Comment comment = Utils.toComment(jsonObj);

		// Insert order to db via OrderSvc
		String id = movieService.insertComment(comment);

		// If id is -1, insertion is unsuccessful
		if (id == "-1") {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Order is unsuccessful, please try again.");
		}

		JsonObject resp = Json.createObjectBuilder()
				.add("orderId", id)
				.add("message", "Created")
				.build();

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(resp.toString());
	}

	// TODO: Task 3, Task 4, Task 8
	/// reviews/search.json?query=lebowski
}
