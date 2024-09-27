import { NgModule } from '@angular/core';
import { LoginComponent } from './pages/login/login.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { authRoutes } from './auth.routes';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(authRoutes)
  ]
})
export class AuthModule { }