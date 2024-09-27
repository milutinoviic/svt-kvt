import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../types/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  private baseUrl = 'api/user';

  createUser(user: User): Observable<User> {
    return this.http.post<User>("api/user", user);
  }
  
  getUserById(idUser:number):Observable<User> {
    return this.http.get<User>("api/user/"+idUser);
  }

  updateUser(id:number,user: User): Observable<User> {
    return this.http.put<User>("api/user/"+id, user);
  }

  getAllPossibleManages():Observable<User[]> {
    return this.http.get<User[]>("api/user/possibleManages");
  }

  

  checkPassword(idUser:number,currentPassword:string):Observable<boolean> {
    return this.http.post<boolean>("api/user/verify-password/"+idUser,currentPassword);
  }
}
