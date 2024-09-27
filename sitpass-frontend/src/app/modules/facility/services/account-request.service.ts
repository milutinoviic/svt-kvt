import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AccountRequest } from '../types/accountRequest';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountRequestService {

  constructor(private http:HttpClient) { }


  createAccountRequest(accountRequest: AccountRequest): Observable<AccountRequest> {
    return this.http.post<AccountRequest>("/api/accountRequest", accountRequest);
  }
  getAllAccountRequest(): Observable<AccountRequest[]> {
    return this.http.get<AccountRequest[]>("/api/accountRequest");
  }
  updateAccountRequest(accountRequest:AccountRequest): Observable<AccountRequest> {
    return this.http.put<AccountRequest>("/api/accountRequest/"+accountRequest.id,accountRequest);
  }

}
