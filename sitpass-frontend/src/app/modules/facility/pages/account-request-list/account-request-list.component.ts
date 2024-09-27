import { Component, OnInit } from '@angular/core';
import { AccountRequest } from '../../types/accountRequest';
import { AccountRequestService } from '../../services/account-request.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-account-request-list',
  templateUrl: './account-request-list.component.html',
  styleUrl: './account-request-list.component.scss'
})
export class AccountRequestListComponent implements OnInit {
  
  accountRequests: AccountRequest[] = [];

  constructor(private accountRequestService: AccountRequestService, private userService: UserService) {}

  ngOnInit(): void {
    this.getAll(); // Pozivanje funkcije za dobijanje svih zahteva prilikom inicijalizacije
  }

  getAll(): void {
    this.accountRequestService.getAllAccountRequest().subscribe(date => {
      this.accountRequests = date;
      console.log("Proslooo");
    }, error => {
      console.error("Nije proslo", error);
    });
  }

  // PENDING, ACCEPTED, REJECTED

  onApprove(request: AccountRequest): void {
    
    if (request.status === "REJECTED") {
      alert("Zahtev je već odbijen i ne može biti ponovo odobren.");
      return; 
    }

    let accountRequest = {
      id: request.id,
      email: request.email,
      password: request.password,
      address: request.address,
      status: "ACCEPTED",
      rejectionReason: ""
    };
    let user ={
      "name": "",
      "surName": "",
      "address": request.address,
      "phoneNumber": "",
      "birthday": "",
      "city": "",
      "email": request.email,
      "password": request.password,
      "zipCode": ""
  }

    this.accountRequestService.updateAccountRequest(accountRequest).subscribe(data => {
      console.log("Zahtev je ažuriran:", data);
      this.getAll(); 
    });
    this.userService.createUser(user).subscribe(data =>{console.log("Ovo je user koji ide u bazu"+data)})
  }
  onReject(request: AccountRequest): void {
    let accountRequest = {
      id: request.id,
      email: request.email,
      password: request.password,
      address: request.address,
      status: "REJECTED",
      rejectionReason: "Nije nesto ispravno."
    };

    this.accountRequestService.updateAccountRequest(accountRequest).subscribe(data => {
      console.log("Zahtev je ažuriran:", data);
      this.getAll(); 
    });
  }
}
