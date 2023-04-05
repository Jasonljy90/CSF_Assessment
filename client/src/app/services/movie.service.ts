import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Comment } from 'src/model/comment';
import { Review } from 'src/model/review';

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  private API_URI: string = '/api/search';
  constructor(private httpClient: HttpClient) {}

  getMovieReviews(movieName: string): Promise<any> {
    const params = new HttpParams().set('movieName', movieName);

    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=utf-8'
    );

    return lastValueFrom(
      this.httpClient.get<Review[]>(this.API_URI, {
        params: params,
        headers: headers,
      })
    );
  }

  addComment(c: Comment): Promise<any> {
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=utf-8'
    );
    const body = JSON.stringify(c);
    console.log('save comment !');
    return lastValueFrom(
      this.httpClient.post<Comment>(
        this.API_URI + '/comment' + c.movieName,
        body,
        {
          headers: headers,
        }
      )
    );
  }
}
