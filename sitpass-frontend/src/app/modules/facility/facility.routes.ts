import { Routes } from "@angular/router";
import { WorkDayComponent } from "./components/work-day/work-day.component";
import { FacilityBaseInformationComponent } from "./components/facility-base-information/facility-base-information.component";
import { FacilityComponent } from "./pages/facility/facility.component";
import { ReviewListComponent } from "./components/review-list/review-list.component";
import { RegisterComponent } from "./pages/register/register.component";
import { ExerciseAddComponent } from "./components/exercise-add/exercise-add.component";
import { FacilityChangeComponent } from "./pages/facility-change/facility-change.component";
import { ReviewAddComponent } from "./pages/review-add/review-add.component";
import { AccountRequestListComponent } from "./pages/account-request-list/account-request-list.component";
import { WorkDayAddComponent } from "./pages/work-day-add/work-day-add.component";
import { WorkDayDeleteComponent } from "./components/work-day-delete/work-day-delete.component";
import { HomePageComponent } from "./pages/home-page/home-page.component";
import { NavbarComponent } from "./components/navbar/navbar.component";
import { FacilityCreateComponent } from "./components/facility-create/facility-create.component";
import { DisciplineCreateComponent } from "./components/discipline-create/discipline-create.component";
import { ImageComponent } from "./components/image/image.component";
import { RoleGuard } from "../auth/guards/role.guard";
import { ProfileComponent } from "./pages/profile/profile.component";
import { RegistrationUpdateComponent } from "./pages/registration-update/registration-update.component";
import { NeaktivniFacilitiesComponent } from "./pages/neaktivni-facilities/neaktivni-facilities.component";
import { ListUsersComponent } from "./pages/list-users/list-users.component";
import { ReplyComponentComponent } from "./components/reply-component/reply-component.component";



export const facilityRoutes: Routes = [
    
      {
        path:'workdays/:id',
        component:WorkDayComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },
      {
        path: 'facilityBaseInfo/:id',
        component: FacilityBaseInformationComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },
      {
        path: 'facility/:id',
        component: FacilityComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },
      {
        path:'review-list/:id',
        component:ReviewListComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },{
        path:'register',
        component:RegisterComponent
      },{
        path:'exercise/:id',
        component:ExerciseAddComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },{
        path: 'change-facility/:id',
        component: FacilityChangeComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },{
        path:'review/:id',
        component:ReviewAddComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },{
        path:'accountRequest',
        component:AccountRequestListComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },{
        path:'workDayAdd/:id',
        component:WorkDayAddComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },{
        path:'workDayDelete/:id',
        component:WorkDayDeleteComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"ADMIN"}
      },{
        path:'homePage',
        component:HomePageComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },
      {
        path:'navbar',
        component:NavbarComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },
      {
        path:'create-facility',
        component:FacilityCreateComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"ADMIN"}
      },
      {
        path:'disicpline-create',
        component:DisciplineCreateComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"ADMIN"}
      },{
        path:'image',
        component:ImageComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },{
        path:'profile',
        component:ProfileComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}
      },{
        path:'registrationUpdate',
        component:RegistrationUpdateComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}

      },{
        path:'neaktivni-facilities',
        component:NeaktivniFacilitiesComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"USER|ADMIN"}

      },{
        path:'list-users/:id',
        component:ListUsersComponent,
        canActivate:[RoleGuard],
        data:{expectedRoles:"ADMIN"}

      }
      
  ];
