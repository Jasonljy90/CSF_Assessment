import { Component, OnDestroy, OnInit } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Review } from 'src/model/review';

@Component({
  selector: 'app-movie-review-list-component',
  templateUrl: './movie-review-list-component.component.html',
  styleUrls: ['./movie-review-list-component.component.css'],
})
export class MovieReviewListComponentComponent implements OnInit, OnDestroy {
  movieName = '';
  param$!: Subscription;
  review!: Review;
  reviews!: Review[];

  constructor(
    private activatedRoute: ActivatedRoute,
    private movieSvc: MovieService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.param$ = this.activatedRoute.params.subscribe(async (params) => {
      this.movieName = params['movieName'];
      const l = await this.movieSvc.getMovieReviews(this.movieName);
      this.reviews.push(l.review);
      //  this.character = l.details;
      //  const ll = await this.marvelCharSvc.getCharComments(this.charId);
      //  console.log(ll);
      //  this.comments = ll;
    });
  }

  ngOnDestroy(): void {
    this.param$.unsubscribe();
  }

  comment() {
    this.router.navigate(['/comment']);
  }

  back() {
    this.router.navigate(['/']);
  }
}
