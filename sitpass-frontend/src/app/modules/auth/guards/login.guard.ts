import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { AuthService } from "../services/auth.service";

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {
  constructor(public auth:AuthService, public router: Router){}

  canActivate():  boolean {
    if(this.auth.isLoggedIn()){
      this.router.navigate(['homePage']);
      return true;
    }
    return true;
  }
  
}