import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateManages } from '../types/createManager';


@Injectable({
  providedIn: 'root'
})
export class ManagesService {

  constructor(private  http:HttpClient) { }
  private apiUrl = '/api/manages';
  
  isManager(userId: number,facilityId:number): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/${userId}/${facilityId}/isManager`);
  }
  createManages(createManager:CreateManages): Observable<CreateManages> {
    return this.http.post<CreateManages>(this.apiUrl, createManager);
  }

  checkAnyManagerExists(facilityId: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/check/${facilityId}`);
  }

  removeManagerByFacilityId(facilityId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/facility/${facilityId}`);
  }

  getFacilityIdForUser(userId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/user/${userId}/facility-id`);
  }
  
}
