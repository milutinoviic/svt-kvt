import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { WorkDayService } from '../../services/work-day.service';
import { WorkDay } from '../../types/workday';
import { FacilityService } from '../../services/facility.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-work-day-add',
  templateUrl: './work-day-add.component.html',
  styleUrl: './work-day-add.component.scss'
})
export class WorkDayAddComponent implements OnInit {
  
  workDayForm: FormGroup;
  submitted = false;
  workDaysList:WorkDay[]=[]
  facilityId!:number

  constructor(private fb: FormBuilder,private workDayService:WorkDayService,private facilityService:FacilityService,private route: ActivatedRoute) {
    this.workDayForm = this.fb.group({
      date: ['', Validators.required],
      day: [{ value: '', disabled: true }, Validators.required],
      timeFrom: ['', Validators.required],
      timeUntil: ['', Validators.required]
    });
  }

  
  ngOnInit() {
    
    this.route.paramMap.subscribe(params => {
      this.facilityId = +params.get('id')!; 
      this.updateWorkDays(); 
    });
    
    this.workDayForm.get('date')?.valueChanges.subscribe(date => {
      this.updateDayFromDate(date);
      
    });
  }
  updateWorkDays(): void {
    this.workDayService.getAllWorkDaysFromFacility(this.facilityId).subscribe(data=>{
      this.workDaysList=data
    })
  }

  updateDayFromDate(date: string) {
    const daysOfWeek: { [key: number]: string } = {
      0: 'Sunday',
      1: 'Monday',
      2: 'Tuesday',
      3: 'Wednesday',
      4: 'Thursday',
      5: 'Friday',
      6: 'Saturday'
    };
      let selectedDate = new Date(date); 
      let dayIndex = selectedDate.getDay(); 
      let dayName = daysOfWeek[dayIndex]; 
      
      this.workDayForm.get('day')?.setValue(dayName);
    
    
  }

  OnChange(): void {
    enum DayOfWeek {
      Sunday = 'SUNDAY',
      Monday = 'MONDAY',
      Tuesday = 'TUESDAY',
      Wednesday = 'WEDNESDAY',
      Thursday = 'THURSDAY',
      Friday = 'FRIDAY',
      Saturday = 'SATURDAY'
    }
    
      const workDayNew = {
        validFrom: this.workDayForm.get('date')?.value,
        day: DayOfWeek[this.workDayForm.get('day')?.value as keyof typeof DayOfWeek],
        from: this.workDayForm.get('timeFrom')?.value,
        until: this.workDayForm.get('timeUntil')?.value
      };
      let exist=this.existWorkDay(workDayNew)
      if(exist){
        alert("Vec popstoji workDay")
        return;
      }
      this.facilityService.addWorkDayToFacility(this.facilityId, workDayNew).subscribe(
        () => {
          
          console.log('Work day added successfully');
          alert("Work day added successfully");
          this.updateWorkDays();
        },
        (error) => {
          
          console.error('Error occurred while adding work day:', error);
          
        }
      );
      
      console.log(exist);
    
  }

existWorkDay(workDayNew:WorkDay):boolean{

   for (let workday of this.workDaysList) {
    
    if (new Date(workday.validFrom).toDateString() === new Date(workDayNew.validFrom).toDateString()) {
      return true; 
    }
  }
  
  return false;
}




}

  

