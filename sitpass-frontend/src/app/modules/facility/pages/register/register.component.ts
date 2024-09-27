import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountRequest } from '../../types/accountRequest';
import { AccountRequestService } from '../../services/account-request.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit {

  
  accountRequestObject!:AccountRequest

  form:FormGroup;
  constructor(private fb: FormBuilder,private accountRequestServise:AccountRequestService) {

    this.form = this.fb.group({
      addressR: ['', [Validators.required, Validators.minLength(2)]],
      emailR: ['', [Validators.required, Validators.email]],
      passwordR: ['', [Validators.required, Validators.minLength(6)]]
    });
    
    
    
   }

  ngOnInit(): void {
    console.log('ngOnInit pozvan');
    
  }

  onChangeAccountRequest(): void {
    let accountRequest = {
      
      email: this.form.value.emailR,
      password: this.form.value.passwordR,
      address: this.form.value.addressR,
      status: "PENDING",
      rejectionReason: ""
      
      
    };
    console.log(accountRequest)
    this.accountRequestServise.createAccountRequest(accountRequest).subscribe(data => {
      
      console.log('Radiiiiii', data);
      alert("Kreirano")
      window.location.reload();  
      
    }, error => {
     
      console.error('Ne radiii', error);
      this.form.get('emailR')?.setErrors({ emailExists: true });
    });
    
  }


}
