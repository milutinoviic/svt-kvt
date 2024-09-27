import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DisciplineService } from '../../services/discipline.service';
import { Discipline } from '../../types/discipline';

@Component({
  selector: 'app-discipline-create',
  templateUrl: './discipline-create.component.html',
  styleUrl: './discipline-create.component.scss'
})
export class DisciplineCreateComponent {
  disciplineForm!: FormGroup;
  disciplineObjectList!:Discipline[]

  constructor(private fb: FormBuilder,private disciplineService:DisciplineService) { }

  ngOnInit(): void {
    this.disciplineForm = this.fb.group({
      name: ['', Validators.required]
    });
    this.loadDisiciplines()
  }

  onSubmit(): void {
    if (this.disciplineForm.valid) {
      const disciplineName = this.disciplineForm.get('name')?.value;

      let exist=this.existDisicplineByName(disciplineName)
      if(exist){
        alert("vec postoji sa tim imenom")
        return
      }
      console.log('Ime Discipline:', disciplineName);
      let disicipline ={
        name:disciplineName

      }
      this.disciplineService.createDiscipline(disicipline).subscribe(data=>{
        console.log(data);
        this.loadDisiciplines()
        alert("kreirana je disicplina")
      }

      )

      
    }
  }

loadDisiciplines(){
    this.disciplineService.getAllDisciplines().subscribe(data=>{
      this.disciplineObjectList=data
    })

  }

existDisicplineByName(disciplineName:string):boolean{
  for(let disicipline of this.disciplineObjectList){
    if(disicipline.name === disciplineName){
      return true
    }

  }
  return false;
}


}
