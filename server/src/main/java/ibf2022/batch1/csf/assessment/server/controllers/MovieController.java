package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;

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
	// TODO: Task 3, Task 4, Task 8
	/// reviews/search.json?query=lebowski
}
