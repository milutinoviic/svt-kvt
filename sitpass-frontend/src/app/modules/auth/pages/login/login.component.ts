import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { AuthCredentials } from '../../types/AuthCredentials';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  
  form:FormGroup;
  constructor(private authService: AuthService,private fb:FormBuilder,private router: Router){
    this.form=this.fb.group({
      email: ['',[Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
    }
      

  )

  }

  login(){
    let authCredentials: AuthCredentials={
      email: this.form.value.email,  
      password: this.form.value.password
    }
    this.authService.login(authCredentials).subscribe({
      next: (res) => {
        localStorage.setItem("user",JSON.stringify(res));
        this.router.navigate(['/homePage']);
        
      },

      error: (err) => { console.log(err);
        alert("Pogresni kredencjali pokusaj ponovo");
        
        

      }
    })
  }

}
