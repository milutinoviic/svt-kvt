import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WorkDay } from '../types/workday';
import { WorkDayRequest } from '../types/workDayRequest';

@Injectable({
  providedIn: 'root'
})
export class WorkDayService {

  constructor(private http:HttpClient) { }

  
 
  getWorkDaysFromFacility(id:number):Observable<WorkDay[]>{
    return this.http.get<WorkDay[]>("api/facility/workdays/"+id)
  }

  getAllWorkDaysFromFacility(id:number):Observable<WorkDay[]>{
    return this.http.get<WorkDay[]>("api/facility/workdays/"+id)
  }

  deleteWorkDaysFromFacility(facilityId: number, workDayRequest: WorkDayRequest): Observable<WorkDay> {
    return this.http.request<WorkDay>('DELETE', `api/facility/${facilityId}/workdays`, {
      body: workDayRequest
    });
  }


  
}
