import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Facility } from '../types/facility';
import { WorkDay } from '../types/workday';
import { Discipline } from '../types/discipline';
import { CreateFacility } from '../types/facilityCreate';

@Injectable({
  providedIn: 'root'
})
export class FacilityService {

  
  private _url:string ="api/facility"

  constructor(private http:HttpClient) {}

  getFacilitys(): Observable<Facility[]>{
    return this.http.get<Facility[]>(this._url+"/inactive")
  }

  createFacility(facility:CreateFacility): Observable<CreateFacility> {
    return this.http.post<CreateFacility>(this._url, facility);
  }

  updateFacility(id:number,facility:CreateFacility):Observable<CreateFacility>{
    return this.http.put<CreateFacility>(this._url+"/"+id, facility);
  }

  getFacility(id:number): Observable<Facility>{
    return this.http.get<Facility>(this._url+"/"+id)
  }
  getWorkDaysFromFacility(id:number):Observable<WorkDay[]>{
    return this.http.get<WorkDay[]>(this._url+"/workdays/"+id)
  }
  getDisciplinesFromFacility(id:number):Observable<Discipline[]>{
    return this.http.get<Discipline[]>("api/disciplines/disciplineFacility/"+id);
  }
  addWorkDayToFacility(id:number,workDay: WorkDay): Observable<void> {
    return this.http.post<void>(this._url+"/"+id+"/workdays",workDay);
  }
  deleteFacility(id: number): Observable<void> {
    return this.http.delete<void>(`${this._url}/${id}`);
  }
  updateFacilityStatus(facilityId: number, userId: number): Observable<void> {
    const url = `${this._url}/${facilityId}/users/${userId}/status`;
    return this.http.put<void>(url, {});
  }
  
  searchFacilities(
    city?: string,
    disciplineId?: number,
    fromRate?: number,
    toRate?: number,
    fromWorkTime?: string,
    toWorkTime?: string
  ): Observable<Facility[]> {
    let params = new HttpParams();
    if (city) params = params.set('city', city);
    if (disciplineId !== undefined) params = params.set('disciplineId', disciplineId.toString());
    if (fromRate !== undefined) params = params.set('fromRate', fromRate.toString());
    if (toRate !== undefined) params = params.set('toRate', toRate.toString());
    if (fromWorkTime) params = params.set('fromWorkTime', fromWorkTime);
    if (toWorkTime) params = params.set('toWorkTime', toWorkTime);
  
    return this.http.get<Facility[]>(`${this._url}/search`, { params });
  }
 
  searchForHomePage(
    city?: string,
    day?: string
): Observable<Facility[]> {
    let params = new HttpParams();

    if (city) {
        params = params.set('city', city);
    }

    if (day) {
        params = params.set('today', day);
    }

    return this.http.get<Facility[]>(`${this._url}/searchForHomePage`, { params });
}

  
}

