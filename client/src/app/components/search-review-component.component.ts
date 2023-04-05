import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MovieService } from '../services/movie.service';
import { Review } from 'src/model/review';

@Component({
  selector: 'app-search-review-component',
  templateUrl: './search-review-component.component.html',
  styleUrls: ['./search-review-component.component.css'],
})
export class SearchReviewComponentComponent implements OnInit {
  form!: FormGroup;
  movieName?: string;
  model = new Review('', '', '', '', '', '', '');
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private movieSvc: MovieService
  ) {}
  ngOnInit(): void {
    this.form = this.createForm();
  }

  search() {
    const movieName = this.form?.value['movieName'];
    // const city = this.form?.value['city'];
    // const imageUrl = this.form?.value['imageUrl'];
    // this.cityObj = { country: countryName, city: city, imageUrl: imageUrl };
    // this.weatherSvc.addCity(this.cityObj);
    //this.movieSvc.getMovieReviews(movieName);
    //this.router.navigate([`search/${movieName}`]);
  }

  private createForm(): FormGroup {
    return this.formBuilder.group({
      movieName: this.formBuilder.control('', [Validators.minLength(2)]),
    });
  }

  getReviewsFromAPI(movieName: string) {
    this.movieSvc
      .getMovieReviews(movieName)
      .then((result) => {
        this.model = new Review(
          result.display_title,
          result.mpaa_rating,
          result.byline,
          result.headline,
          result.summary_short,
          result.link_url,
          result.multimedia_src
        );
      })
      .catch((err) => {
        console.log(err);
        this.router.navigate(['']);
      });
  }
}
