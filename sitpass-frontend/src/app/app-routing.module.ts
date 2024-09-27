import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FacilityListComponent } from './modules/facility/pages/facility-list/facility-list.component';
import { RoleGuard } from './modules/auth/guards/role.guard';

const routes: Routes = [
  {
    path:'facilitys',
    component:FacilityListComponent,
    canActivate:[RoleGuard],
    data:{expectedRoles:"USER|ADMIN"}
  },
  {
    path: 'auth',
    loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule)
  }
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
