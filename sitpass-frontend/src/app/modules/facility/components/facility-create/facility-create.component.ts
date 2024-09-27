import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FacilityService } from '../../services/facility.service';
import { DisciplineService } from '../../services/discipline.service'; // Importuj servis za discipline
import { Router } from '@angular/router';
import { Discipline } from '../../types/discipline';

@Component({
  selector: 'app-facility-create',
  templateUrl: './facility-create.component.html',
  styleUrls: ['./facility-create.component.scss']
})
export class FacilityCreateComponent implements OnInit {
  form: FormGroup;
  disciplines: Discipline[] = [];
  selectedDisciplines: any[] = []; 
  selectedDisciplineIds: number[] = []; 

  constructor(
    private fb: FormBuilder,
    private facilityService: FacilityService,
    private disciplineService: DisciplineService,
    private router: Router
  ) {
    this.form = this.fb.group({
      facilityName: ['', [Validators.required, Validators.minLength(2)]],
      facilityDescription: ['', [Validators.required, Validators.minLength(2)]],
      facilityAddress: ['', [Validators.required, Validators.minLength(2)]],
      facilityCity: ['', [Validators.required, Validators.minLength(2)]]
    });
  }

  ngOnInit(): void {
    this.loadDisciplines(); // Učitaj sve discipline
  }

  loadDisciplines(): void {
    this.disciplineService.getAllDisciplines().subscribe(data => {
      this.disciplines = data;
    });
  }

  addDiscipline(event: any): void {
    const selectedId = +event.target.value;
    const selectedDiscipline = this.disciplines.find(d => d.id === +selectedId);
    console.log(selectedId+"id je to")
    console.log(selectedDiscipline+"disicplina je to je to")

    if (selectedDiscipline && !this.selectedDisciplines.some(d => d.id === selectedDiscipline.id)) {
      this.selectedDisciplines.push(selectedDiscipline);
      this.selectedDisciplineIds.push(selectedId);
      event.target.value = '';
      
    }
  }

  removeDiscipline(id: number): void {
    this.selectedDisciplineIds = this.selectedDisciplineIds.filter(disciplineId => disciplineId !== id);
    this.selectedDisciplines = this.selectedDisciplines.filter(d => d.id !== id);
    
    
    console.log(this.selectedDisciplineIds+ "ovo su idjevi")
    console.log("---------------------------------")
    console.log(this.selectedDisciplineIds);
    console.log("---------------------------------")
    console.log(Array.isArray(this.selectedDisciplineIds));
  }

  onChangeFacility(): void {
    const facility = {
      name: this.form.value.facilityName,
      description: this.form.value.facilityDescription,
      address: this.form.value.facilityAddress,
      city: this.form.value.facilityCity,
      active: true,
      workDays: [], 
      images: [], 
      disciplinesIds: this.selectedDisciplineIds
      
    };
    console.log(JSON.stringify(facility));

    this.facilityService.createFacility(facility).subscribe(data => {
      alert("Successfully created facility!");
      console.log('Facility created successfully', data);
      this.router.navigate(['facilities']);
    }, error => {
      console.error('Error creating facility', error);
    });
  }
  
}


