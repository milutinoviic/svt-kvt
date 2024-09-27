import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FacilityBaseInformationComponent } from './components/facility-base-information/facility-base-information.component';
import { WorkDayComponent } from './components/work-day/work-day.component';
import { ReviewListComponent } from './components/review-list/review-list.component';
import { FacilityComponent } from './pages/facility/facility.component';
import { FacilityListComponent } from './pages/facility-list/facility-list.component';
import { RouterModule } from '@angular/router';
import { facilityRoutes } from './facility.routes';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './pages/register/register.component';
import { ExerciseAddComponent } from './components/exercise-add/exercise-add.component';
import { FacilityChangeComponent } from './pages/facility-change/facility-change.component';
import { ReviewAddComponent } from './pages/review-add/review-add.component';
import { AccountRequestListComponent } from './pages/account-request-list/account-request-list.component';
import { ImageComponent } from './components/image/image.component';
import { WorkDayAddComponent } from './pages/work-day-add/work-day-add.component';
import { WorkDayDeleteComponent } from './components/work-day-delete/work-day-delete.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FacilityCreateComponent } from './components/facility-create/facility-create.component';
import { DisciplineCreateComponent } from './components/discipline-create/discipline-create.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { RegistrationUpdateComponent } from './pages/registration-update/registration-update.component';
import { NeaktivniFacilitiesComponent } from './pages/neaktivni-facilities/neaktivni-facilities.component';
import { ListUsersComponent } from './pages/list-users/list-users.component';
import { ReplyComponentComponent } from './components/reply-component/reply-component.component';




@NgModule({
  declarations: [
    FacilityBaseInformationComponent,
    WorkDayComponent,
    ReviewListComponent,
    FacilityComponent,
    FacilityListComponent,
    RegisterComponent,
    ExerciseAddComponent,
    FacilityChangeComponent,
    ReviewAddComponent,
    AccountRequestListComponent,
    ImageComponent,
    WorkDayAddComponent,
    WorkDayDeleteComponent,
    HomePageComponent,
    NavbarComponent,
    FacilityCreateComponent,
    DisciplineCreateComponent,
    ProfileComponent,
    RegistrationUpdateComponent,
    NeaktivniFacilitiesComponent,
    ListUsersComponent,
    ReplyComponentComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forChild(facilityRoutes)
  ]
})
export class FacilityModule { }
