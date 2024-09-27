import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Rate } from '../types/rate';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RateService {

  private _url:string ="api/rates"

  constructor(private http:HttpClient) { }

  createRate(rate: Rate): Observable<Rate> {
    return this.http.post<Rate>(this._url, rate);
  }
}
