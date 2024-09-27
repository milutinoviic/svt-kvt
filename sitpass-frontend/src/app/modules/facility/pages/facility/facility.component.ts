import { Component, OnInit } from '@angular/core';
import { FacilityService } from '../../services/facility.service';
import { Facility } from '../../types/facility';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ManagesService } from '../../services/manages.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-facility',
  templateUrl: './facility.component.html',
  styleUrl: './facility.component.scss'
})
export class FacilityComponent implements OnInit{
  constructor(private facilityService:FacilityService,
  private route:ActivatedRoute,
  private router:Router,
  private managesService:ManagesService){

  }

  facilityId!:number
  facility! :Facility
  roleNow!:string
  currentUserId!:number
  isExist!:boolean

  ngOnInit(): void {
    this.getCurrentUserId();
    this.loadFacility()
    
    
  }
  loadFacility(){
    this.route.params.subscribe(params=>{
      this.facilityId=+params['id'];
     
    });
  }
  isDeleteManagerModalOpen: boolean = false;
  isAddWorkDayModalOpen = false;
  isDeleteWorkDayModalOpen = false;
  isAddExerciseModalOpen = false;
  isAddReviewModal = false
  isDeleteFacilityModalOpen = false;

  openAddWorkDayModal() {
    this.isAddWorkDayModalOpen = true;
  }

  closeAddWorkDayModal() {
    this.isAddWorkDayModalOpen = false;
    window.location.reload();
  }

  openDeleteWorkDayModal() {
    this.isDeleteWorkDayModalOpen = true;
  }

  closeDeleteWorkDayModal() {
    this.isDeleteWorkDayModalOpen = false;
    window.location.reload();
  }
  closeAddExerciseModalOpen(){
    this.isAddExerciseModalOpen = false

  }
  openAddExerciseModalOpen() {
    this.isAddExerciseModalOpen = true;
  }
  closeAddReviewModal(){
    this.isAddReviewModal = false
    
  }

  openAddReviewModal(){
    this.isAddReviewModal = true
    
  }

  onReviewCreated() {
    this.closeAddReviewModal();  
    window.location.reload();    
  }

  onExerciseCreated() {
    this.closeAddExerciseModalOpen();  
    window.location.reload();    
  }
  
  logout() {
    localStorage.removeItem('user');  
    this.router.navigate(['/auth/login']);
  }
  deleteFacility() {
    this.facilityService.deleteFacility(this.facilityId).subscribe({
      next: (data) => {
        console.log("Obrisao si facility", data);
        this.router.navigate(['facilitys']);
      },
      error: (err) => {
        console.error("Greška prilikom brisanja facility", err);
        
      }
    });
    this.closeDeleteFacilityModal();
  }

  openDeleteFacilityModal() {
    this.isDeleteFacilityModalOpen = true;
  }


  closeDeleteFacilityModal() {
    this.isDeleteFacilityModalOpen = false;
  }
  openChangeFacility(){
    this.router.navigate(['change-facility/'+this.facilityId]);

  }

  openListManagerInFacilityModal(){
    this.router.navigate(['list-users/'+this.facilityId]);
  }

  deleteManagerInFacilityModal(): void {
    this.isDeleteManagerModalOpen = true;
  }

  closeDeleteManagerModal(): void {
    this.isDeleteManagerModalOpen = false;
  }


  deleteManager(): void {
    console.log('Facility ID:', this.facilityId); 
    this.managesService.removeManagerByFacilityId(this.facilityId).subscribe({
      next: () => {
        console.log('Menadžer je uspešno uklonjen.');
        alert('Menadžer je uspešno uklonjen.');
        this.closeDeleteManagerModal();
        this.updateFacilityStatus()
      },
      error: (error) => {
        console.error('Greška pri uklanjanju menadžera:', error);
        alert('Greška pri uklanjanju menadžera.');
      }
    });
  }
  
  updateFacilityStatus(){
    this.facilityService.updateFacilityStatus(this.facilityId,0).subscribe({
      next: () => {
        console.log('Facility status updated successfully');
        this.router.navigate(['/facilitys']);
        
      },
      error: (error) => {
        console.error('Error updating facility status:', error);
        alert('Error updating facility status');
      }
    });
  
    
  
  }
  

  getCurrentUserId(): void {
    const token = localStorage.getItem("user");
    console.log("Token:", token);
    if (token) {
      try {
        const decodedToken: any = jwtDecode(token);
        console.log("Tokennnnnnnn:", decodedToken); 
        this.roleNow = decodedToken.role[0]?.name || ''
        this.currentUserId = decodedToken.id;
        this.loadFacility()
        this.getManages()
      } catch (error) {
        console.error("Error decoding token:", error);
      }
    } else {
      console.warn("Token not found in localStorage.");
    }
  }

  getManages(){
    this.managesService.isManager(this.currentUserId,this.facilityId).subscribe(data=>{
      this.isExist=data
    })
  }


  

}
