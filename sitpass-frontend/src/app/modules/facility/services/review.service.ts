import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../types/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http:HttpClient) { }

  private apiUrl = 'api/reviews';


  getReviews(id:number): Observable<Review[]>{
    return this.http.get<Review[]>("api/reviews/facility/"+id)
  }

  createReview(reviewToCreate: any):Observable<Review>{
    return this.http.post<Review>("api/reviews",reviewToCreate)
  }
 

  hiddenReview(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/hide/${id}`, {});
  }

  deleteReview(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getActiveReviewsByUserId(userId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.apiUrl}/user/${userId}`);
  }
}
