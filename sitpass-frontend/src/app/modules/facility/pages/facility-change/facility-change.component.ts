import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FacilityService } from '../../services/facility.service';
import { Facility } from '../../types/facility';
import { DisciplineService } from '../../services/discipline.service';
import { Discipline } from '../../types/discipline';

@Component({
  selector: 'app-facility-change',
  templateUrl: './facility-change.component.html',
  styleUrls: ['./facility-change.component.scss']
})
export class FacilityChangeComponent implements OnInit {

  idFacility!: number;
  facilityObject!: Facility;
  selectedDisciplineIds: number[] = []; 
  disciplines: Discipline[] = [];
  selectedDisciplines: Discipline[] = []; 

  constructor(private facilityService: FacilityService,
              private route: ActivatedRoute,
              private disciplineService: DisciplineService,
              private router: Router ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idFacility = +params['id'];
    });
    this.loadFacility();
    this.loadDiscipline();
  }

  loadDiscipline(): void {
    this.disciplineService.getAllDisciplines().subscribe(data => {
      this.disciplines = data;
    });
  }

  loadFacility(): void {
    this.facilityService.getFacility(this.idFacility).subscribe(data => {
      this.facilityObject = data;

      // Prethodno selektovane discipline
      this.selectedDisciplines = this.facilityObject.disciplines;
      this.selectedDisciplineIds = this.facilityObject.disciplines
        .map(discipline => discipline.id)
        .filter((id): id is number => id !== undefined);
    });
  }

  updateFacility(): void {
    let facility = {
      id: this.facilityObject.id,
      name: this.facilityObject.name,
      description: this.facilityObject.description,
      address: this.facilityObject.address,
      city: this.facilityObject.city,
      totalRating: this.facilityObject.totalRating,
      active: this.facilityObject.active,
      createdAt: this.facilityObject.createdAt,
      workDays: this.facilityObject.workDays,
      images: this.facilityObject.images,
      disciplinesIds: this.selectedDisciplineIds 
    };

    this.facilityService.updateFacility(this.idFacility, facility).subscribe(data => {
      this.router.navigate(['facility/'+this.idFacility]);
    });
  }

  addDiscipline(event: any): void {
    const selectedId = +event.target.value;
    const selectedDiscipline = this.disciplines.find(d => d.id === selectedId);

    if (selectedDiscipline && !this.selectedDisciplineIds.includes(selectedId)) {
      this.selectedDisciplines.push(selectedDiscipline);
      this.selectedDisciplineIds.push(selectedId);
      event.target.value = ''; // Resetovanje select elementa
    }
  }

  removeDiscipline(discipline: Discipline): void {
    if (discipline.id !== undefined) {
      this.selectedDisciplines = this.selectedDisciplines.filter(d => d.id !== discipline.id);
      this.selectedDisciplineIds = this.selectedDisciplineIds.filter(id => id !== discipline.id);
      this.facilityObject.disciplines=this.facilityObject.disciplines.filter(d => d.id !== discipline.id);
    }
  }
}
