import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { WorkDayService } from '../../services/work-day.service';
import { ActivatedRoute } from '@angular/router';
import { WorkDay } from '../../types/workday';
import { WorkDayRequest } from '../../types/workDayRequest';

@Component({
  selector: 'app-work-day-delete',
  templateUrl: './work-day-delete.component.html',
  styleUrl: './work-day-delete.component.scss'
})
export class WorkDayDeleteComponent implements OnInit {

  form: FormGroup;
  facilityId!:number
  workDays:WorkDay[]=[]
  constructor(private fb: FormBuilder,private workDayService:WorkDayService,private route: ActivatedRoute){
    this.form = this.fb.group({
      date: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.facilityId = +params.get('id')!; 
    });
    this.getAllWorkDays();

   
  }

getAllWorkDays():void{
  
  this.workDayService.getAllWorkDaysFromFacility(this.facilityId).subscribe(data=>{
    this.workDays=data;
  });
}

onClick():void{

  let workDayRequest ={
    validFrom: this.form.get('date')?.value
  }
  let exist=this.findDate(workDayRequest)
  if(!exist){
    alert("Datum ne postoji ")
    return
  }
  this.workDayService.deleteWorkDaysFromFacility(this.facilityId,workDayRequest).subscribe(data=>{
    alert("Uspjeno si obrisao radi dan!")
    console.log(data);
    this.getAllWorkDays();
  })
  

}

findDate(workDayNew:WorkDayRequest):boolean{
  for(let workDay of this.workDays){
    
    if (new Date(workDay.validFrom).toDateString() === new Date(workDayNew.validFrom).toDateString()) {
      return true; 
    }


  }
  return false;
}

}
