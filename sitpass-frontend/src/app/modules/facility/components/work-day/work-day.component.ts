import { Component, OnInit } from '@angular/core';
import { WorkDayService } from '../../services/work-day.service';
import { ActivatedRoute } from '@angular/router';
import { WorkDay } from '../../types/workday';

@Component({
  selector: 'app-work-day',
  templateUrl: './work-day.component.html',
  styleUrl: './work-day.component.scss'
})
export class WorkDayComponent implements OnInit{
  
  facilityId!:number
  workDays:WorkDay[]=[]
  workDaysOfWeek: WorkDay[] = [];
  
  
    constructor(private workDayService:WorkDayService,private route:ActivatedRoute){
  
    }
    ngOnInit(): void {
      this.route.params.subscribe(params=>{
        this.facilityId=+params['id'];
        this.getWorkdays(this.facilityId);
        
        
       
      });

    }

    getWorkdays(facilityId:number):void{
      this.workDayService.getWorkDaysFromFacility(facilityId).subscribe(data=>{
        this.workDays=data;
        console.log("proslooo");
       
      })
    }

    

    formatTime(time: string): string {
      const [hours, minutes] = time.split(':');
      return `${hours}:${minutes}`;
    }


}
