import { Routes } from "@angular/router";
import { LoginComponent } from "./pages/login/login.component";
import { LoginGuard } from "./guards/login.guard";

export const authRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoginGuard]
  }
];
