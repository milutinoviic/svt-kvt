import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { jwtDecode } from 'jwt-decode';
import { UserService } from '../../services/user.service';
import { User } from '../../types/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration-update',
  templateUrl: './registration-update.component.html',
  styleUrls: ['./registration-update.component.scss']
})
export class RegistrationUpdateComponent implements OnInit {

  userObject!: User;
  registrationForm: FormGroup;
  currentUserId!: number;
  passwordChanged: boolean = false;
  currentPasswordInvalid: boolean = false;

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {
    this.registrationForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      surName: ['', [Validators.required, Validators.minLength(2)]],
      address: ['', [Validators.required, Validators.minLength(5)]],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
      birthday: ['', [Validators.required]],
      city: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      currentPassword: ['', []],
      password: ['', []], // Initially not required
      confirmPassword: ['', []] // Initially not required
    }, { validator: this.passwordMatchValidator });
  }

  ngOnInit(): void {
    this.getCurrentUserId();
  }

  getCurrentUserId(): void {
    const token = localStorage.getItem("user");
    if (token) {
      try {
        const decodedToken: any = jwtDecode(token);
        this.currentUserId = decodedToken.id;
        this.loadUser(); 
      } catch (error) {
        console.error("Error decoding token:", error);
      }
    } else {
      console.warn("Token not found in localStorage.");
    }
  }

  loadUser(): void {
    this.userService.getUserById(this.currentUserId).subscribe(data => {
      this.userObject = data;
      this.registrationForm.patchValue({
        name: this.userObject.name,
        surName: this.userObject.surName,
        address: this.userObject.address,
        phoneNumber: this.userObject.phoneNumber,
        birthday: this.userObject.birthday,
        city: this.userObject.city,
        email: this.userObject.email
      });
    });
  }

  onSubmit(): void {
    if (this.registrationForm.valid) {
      const user: User = {
        name: this.registrationForm.value.name,
        surName: this.registrationForm.value.surName,
        address: this.registrationForm.value.address,
        phoneNumber: this.registrationForm.value.phoneNumber,
        birthday: this.registrationForm.value.birthday,
        city: this.registrationForm.value.city,
        email: this.registrationForm.value.email,
        password: this.passwordChanged ? this.registrationForm.value.password : undefined
      };
    if(!this.currentPasswordInvalid){
  
      this.userService.updateUser(this.currentUserId, user).subscribe(data => {
      alert('Update uspešna!');
      this.router.navigate(['profile']);
  }, error => {
      console.error('Greška pri registraciji:', error);
      this.registrationForm.get('email')?.setErrors({ emailExists: true });
  });
}else{
  alert("Pogresna lozinka")
  this.disableUpdateButton();
}
    }
  }

  disableUpdateButton(): void {
    const updateButton = document.querySelector('button[type="submit"]');
    if (updateButton) {
      updateButton.setAttribute('disabled', 'true');
    }
  }

  passwordMatchValidator(formGroup: FormGroup): null | { mismatch: true } {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { mismatch: true };
  }

  onPasswordChange(): void {
    this.passwordChanged = true;
    this.registrationForm.get('password')?.setValidators([Validators.required]);
    this.registrationForm.get('confirmPassword')?.setValidators([Validators.required]);
    this.registrationForm.updateValueAndValidity();
  }

  checkCurrentPassword(): void {
    const currentPassword = this.registrationForm.value.currentPassword;
    this.userService.checkPassword(this.currentUserId, currentPassword).subscribe(isValid => {
      this.currentPasswordInvalid = !isValid;
      console.log("---------------------------")

      console.log(this.currentPasswordInvalid)

      console.log("---------------------------")
      if (isValid) {
        this.onPasswordChange();
      } else {
        this.registrationForm.get('password')?.clearValidators();
        this.registrationForm.get('confirmPassword')?.clearValidators();
        this.registrationForm.get('password')?.updateValueAndValidity();
        this.registrationForm.get('confirmPassword')?.updateValueAndValidity();
        this.passwordChanged = false;
      }
    });
  }
}


