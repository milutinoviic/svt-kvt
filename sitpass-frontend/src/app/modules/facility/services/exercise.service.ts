import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Exercise } from '../types/exercise';
import { Observable } from 'rxjs';
import { ExerciseDetailsDto } from '../types/exerciseDetailsDto';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  constructor(private http:HttpClient) {
  }



  createExercise(exercise:Exercise): Observable<Exercise> {
   return this.http.post<Exercise>("api/exercise", exercise);
 }
 getCountExercise(idFacility:number,idUser:number): Observable<number>{
  return this.http.get<number>("api/exercise/byFacility/"+idFacility+"/"+idUser)
}
getExercisesByUserId(userId: number): Observable<ExerciseDetailsDto[]> {
  return this.http.get<ExerciseDetailsDto[]>(`api/exercise/user/${userId}`);
}
}
