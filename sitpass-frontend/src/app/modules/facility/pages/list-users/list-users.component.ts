import { Component, OnInit } from '@angular/core';
import { User } from '../../types/user';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ManagesService } from '../../services/manages.service';
import { FacilityService } from '../../services/facility.service';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrl: './list-users.component.scss'
})
export class ListUsersComponent implements OnInit{

  userList:User[]=[]
  facilityId!:number
  userId!:number
  existManager!:boolean

  constructor(
    
    private userService:UserService,
    private route:ActivatedRoute,
    private managerService:ManagesService,
    private router:Router,
    private facilityService:FacilityService
  ){}
  
  ngOnInit(): void {
    this.loadUser()
    this.loadFacility()
    this.checkManagerExist()
    
  }
  loadFacility(){
    this.route.params.subscribe(params=>{
      this.facilityId=+params['id'];
     
    });
  }
  checkManagerExist(){
    this.managerService.checkAnyManagerExists(this.facilityId).subscribe(data=>{
      this.existManager=data
    })
  }

  onApprove(user:User): void {
    if(this.existManager){
      alert("Vec postoji manager za taj facility")
      return;
    }

    if (user.id === undefined) {
      console.error('User ID is undefined');
      return;
    }
    this.userId=user.id
    let newManager ={
      "userId": user.id,
      "facilltyId": this.facilityId

    }
    this.managerService.createManages(newManager).subscribe({
      next: (data) => {
        
        console.log('Successfully created manage:', data);
        alert("Successfully created manage")
        this.updateFacilityStatus()
      },
      error: (error) => {
        console.error('Error creating manage:', error);
       
      }
    });

    

    
    
      
  }
updateFacilityStatus(){
  this.facilityService.updateFacilityStatus(this.facilityId,this.userId).subscribe({
    next: () => {
      console.log('Facility status updated successfully');
      this.router.navigate(['/facility/'+this.facilityId]);
      this.loadUser();
    },
    error: (error) => {
      console.error('Error updating facility status:', error);
      alert('Error updating facility status');
    }
  });

  

}

  loadUser(){
    this.userService.getAllPossibleManages().subscribe(data=>{
      this.userList=data
    })

  }

}
