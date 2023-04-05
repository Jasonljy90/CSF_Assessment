import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Comment } from 'src/model/comment';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css'],
})
export class CommentComponent {
  form!: FormGroup;
  movieName?: string;
  rating?: number;
  comment?: string;
  commentObj?: Comment;

  ratingList: any[] = [
    { value: 1, viewValue: '1' },
    { value: 2, viewValue: '2' },
    { value: 3, viewValue: '3' },
    { value: 4, viewValue: '4' },
    { value: 5, viewValue: '5' },
  ];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private movieSvc: MovieService
  ) {}

  ngOnInit(): void {
    this.form = this.createForm();
  }

  ngOnDestroy(): void {}

  add() {
    const movieName = this.form?.value['movieName'];
    const rating = this.form?.value['rating'];
    const comment = this.form?.value['comment'];
    this.commentObj = {
      movieName: movieName,
      rating: rating,
      comment: comment,
    };
    this.movieSvc.addComment(this.commentObj);
    this.router.navigate(['/']);
  }

  private createForm(): FormGroup {
    return this.formBuilder.group({
      movieName: this.formBuilder.control('', [
        Validators.required,
        Validators.minLength(3),
      ]),
      rating: this.formBuilder.control(''),
      comment: this.formBuilder.control('', [Validators.required]),
    });
  }

  back() {
    this.router.navigate(['/']);
  }
}
