import { Component, OnInit } from '@angular/core';
import { Facility } from '../../types/facility';
import { Discipline } from '../../types/discipline';
import { FacilityService } from '../../services/facility.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-facility-base-information',
  templateUrl: './facility-base-information.component.html',
  styleUrl: './facility-base-information.component.scss'
})
export class FacilityBaseInformationComponent implements OnInit {

  
  facility! :Facility 
  facilityId!:number
  disciplines:Discipline[]=[]

  constructor(private facilityService:FacilityService,private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.route.params.subscribe(params=>{
      this.facilityId=+params['id'];
      this.getFacility(this.facilityId);
      this.getDisciplines(this.facilityId);
    });
    
  }
  getFacility(facilityId:number): void{
    this.facilityService.getFacility(facilityId).subscribe({
      next: (data) => {
        data.totalRating = parseFloat(data.totalRating.toFixed(1));
        this.facility = data;
      },
      error: (err) => {
        console.error('Error fetching facility:', err);
      }
    });
  }
  getDisciplines(facilityId:number): void{
    this.facilityService.getDisciplinesFromFacility(facilityId).subscribe({
      next: (data) => {
        this.disciplines = data;
      },
      error: (err) => {
        console.error('Error fetching disciplines:', err);
      }
    });
      
  }

  
  

}
