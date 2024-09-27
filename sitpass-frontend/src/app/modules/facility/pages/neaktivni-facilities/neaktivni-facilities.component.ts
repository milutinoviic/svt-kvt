import { Component, OnInit } from '@angular/core';
import { Facility } from '../../types/facility';
import { FacilityService } from '../../services/facility.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-neaktivni-facilities',
  templateUrl: './neaktivni-facilities.component.html',
  styleUrl: './neaktivni-facilities.component.scss'
})
export class NeaktivniFacilitiesComponent implements OnInit {
  
  facilities:Facility[]=[]

  constructor( private router: Router,private facilityService:FacilityService){}

  ngOnInit(): void {
    this.loadFacility()
    
  }

  loadFacility(){
    this.facilityService.getFacilitys().subscribe(data=>{
      this.facilities=data
    })
  }

  logout() {
    localStorage.removeItem('user');  
    this.router.navigate(['/auth/login']);
  }

  

}
