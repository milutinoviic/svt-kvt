import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { WorkDay } from '../../types/workday';
import { ActivatedRoute, Router } from '@angular/router';
import { WorkDayService } from '../../services/work-day.service';
import { ExerciseService } from '../../services/exercise.service';
import { jwtDecode } from 'jwt-decode';
import { isBefore, isAfter, parseISO, format } from 'date-fns';


@Component({
  selector: 'app-exercise-add',
  templateUrl: './exercise-add.component.html',
  styleUrl: './exercise-add.component.scss'
})
export class ExerciseAddComponent implements OnInit {

  @Output() exerciseCreated = new EventEmitter<void>();

  
  id!:number
  form:FormGroup;
  workdays:WorkDay[]=[]
  currentUserId!:number
  constructor(private exerciseServise:ExerciseService,private route:ActivatedRoute,private fb: FormBuilder,private workDaysService:WorkDayService,private router:Router){

    this.form = this.fb.group({
      startDateTime: ['', [Validators.required]],
      endDateTime: ['',[Validators.required]],
    });
    this.form.get('startDateTime')?.valueChanges.subscribe(startDateTime => {
      if (startDateTime) {
        this.updateEndDateTime(startDateTime);
      }
    });
  }
  
  ngOnInit(): void {
    this.route.params.subscribe(params=>{
    this.id=+params['id']
    this.getCurrentUserId();
    this.loadWorkDays();
    
    
    });
    
    
  }
  loadWorkDays(): void {
    this.workDaysService.getAllWorkDaysFromFacility(this.id).subscribe(data => {
      this.workdays = data;
    });
  }
  getCurrentUserId(): void {
    const token = localStorage.getItem("user");
    console.log("Token:", token);
    if (token) {
      try {
        const decodedToken: any = jwtDecode(token);
        console.log("Tokennnnnnnn:", decodedToken); 
        this.currentUserId = decodedToken.id;
      } catch (error) {
        console.error("Error decoding token:", error);
      }
    } else {
      console.warn("Token not found in localStorage.");
    }
  }

 createExercise(){
  const startDateTime = new Date(this.form.value.startDateTime);
  const endDateTime = new Date(this.form.value.endDateTime);

  if (!this.isWithinWorkDays(startDateTime, endDateTime)) {
    alert('Facility does not work at the selected time.');
    return;
  }
  
  let exercise = {
    from:this.form.value.startDateTime,
    until:this.form.value.endDateTime,
    userId:this.currentUserId,
    facilityId:this.id

  }
  this.exerciseServise.createExercise(exercise).subscribe(data => {
    
    alert('Successfully created exercise!');
    console.log(data);

    this.exerciseCreated.emit();  
    
    
  },
  error => {
    console.error('Error creating exercise:', error);
    alert('Failed to create exercise.');
  });
  
  
  

 }

 private  isTimeBefore(date1: Date, date2: Date): boolean {
  // Izvuci sate, minute i sekunde iz oba datuma
  const hours1 = date1.getHours();
  const minutes1 = date1.getMinutes();
  const seconds1 = date1.getSeconds();
  
  const hours2 = date2.getHours();
  const minutes2 = date2.getMinutes();
  const seconds2 = date2.getSeconds();
  
  // Pretvori sve u sekunde od početka dana
  const timeInSeconds1 = hours1 * 3600 + minutes1 * 60 + seconds1;
  const timeInSeconds2 = hours2 * 3600 + minutes2 * 60 + seconds2;
  
  // Uporedi vremena i vrati boolean vrednost
  return timeInSeconds1 < timeInSeconds2;
}
 private isWithinWorkDays(startDateTime: Date, endDateTime: Date): boolean {
  for (let workday of this.workdays) {
    const workdayStart = new Date(`${workday.validFrom}T${workday.from}`);
    const workdayEnd = new Date(`${workday.validFrom}T${workday.until}`);

    // Check if the time range intersects with workday time range on the same date
    if (startDateTime.getDay() === workdayStart.getDay() &&
      ((this.isTimeBefore(workdayStart, startDateTime) && this.isTimeBefore(endDateTime, workdayEnd)))) {
      return true;
    }
    console.log(startDateTime.getDay() === workdayStart.getDay());
    console.log(startDateTime.getTime() >= workdayStart.getTime() && endDateTime.getTime() <= workdayEnd.getTime());
    console.log(endDateTime.getTime() <= workdayEnd.getTime())
    console.log(endDateTime.getTime());
    console.log(workdayEnd.getTime());
    console.log(startDateTime.getTime() >= workdayStart.getTime());
    console.log(startDateTime);
    console.log(workdayStart);
    console.log("00");
    console.log(startDateTime , workdayStart , endDateTime , workdayEnd)
    console.log("===============")
  }
  return false;
}


private updateEndDateTime(startDateTime: string): void {
  const startDate = new Date(startDateTime);
  const endDate = new Date(startDate.getTime() + 2 * 60 * 60 * 1000); 

  const year = endDate.getFullYear();
  const month = ('0' + (endDate.getMonth() + 1)).slice(-2);
  const day = ('0' + endDate.getDate()).slice(-2);
  const hours = ('0' + endDate.getHours()).slice(-2);
  const minutes = ('0' + endDate.getMinutes()).slice(-2);

  this.form.patchValue({
    endDateTime: `${year}-${month}-${day}T${hours}:${minutes}`
  });
}
 
}





