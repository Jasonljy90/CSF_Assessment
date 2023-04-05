import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Comment } from 'src/model/comment';

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

  constructor(private formBuilder: FormBuilder, private router: Router) {}

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
    //this.weatherSvc.addCity(this.cityObj);
    this.router.navigate(['/']);
  }

  private createForm(): FormGroup {
    return this.formBuilder.group({
      countryName: this.formBuilder.control(''),
      city: this.formBuilder.control(''),
      imageUrl: this.formBuilder.control(''),
    });
  }
}
