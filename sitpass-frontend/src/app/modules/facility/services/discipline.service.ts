import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Discipline } from '../types/discipline';

@Injectable({
  providedIn: 'root'
})
export class DisciplineService {

  constructor(private http:HttpClient) { }


  getAllDisciplines(): Observable<Discipline[]> {
    return this.http.get<Discipline[]>("/api/disciplines");
  }

  createDiscipline(disicpline:Discipline): Observable<Discipline> {
    return this.http.post<Discipline>("/api/disciplines",disicpline);
  }
}
