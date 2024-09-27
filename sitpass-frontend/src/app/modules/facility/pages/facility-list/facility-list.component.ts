import { Component, OnInit } from '@angular/core';
import { Facility } from '../../types/facility';
import { FacilityService } from '../../services/facility.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DisciplineService } from '../../services/discipline.service';
import { Discipline } from '../../types/discipline';
import { jwtDecode } from 'jwt-decode';
import { ManagesService } from '../../services/manages.service';

@Component({
  selector: 'app-facility-list',
  templateUrl: './facility-list.component.html',
  styleUrls: ['./facility-list.component.scss'] // Promeni "styleUrl" u "styleUrls"
})
export class FacilityListComponent implements OnInit{
 
  
  facilities: Facility[] = [];
  searchFacilityForm: FormGroup;
  disciplines:Discipline[]=[]
  selectedDisciplineId: number | null = null;
  selectedDisciplineName: string | null = null;
  isFacilityModalOpen=false
  currentUserId!:number
  roleNow!:string;
  isDisciplineOpen!:boolean
 
 

  constructor(
    private facilityService: FacilityService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private disciplineService:DisciplineService,
    private managesService:ManagesService

  ) {
    this.searchFacilityForm = this.fb.group({
      city: [''],
      disciplineId: [''],
      fromRate: [''],
      toRate: [''],
      fromWorkTime: [''],
      toWorkTime: ['']
    });
  }

  ngOnInit(): void {
    this.getCurrentUserId();
    this.loadDisciplines();
    this.route.queryParams.subscribe(params => {
      this.searchFacilityForm.patchValue({
        city: params['city'] || '',
        disciplineId: params['disciplineId'] || '',
        fromRate: params['fromRate'] || '',
        toRate: params['toRate'] || '',
        fromWorkTime: params['fromWorkTime'] || '',
        toWorkTime: params['toWorkTime'] || ''
      });
      this.submitSearch(false); // Initial load
    });
    
  }

  loadDisciplines() {
    this.disciplineService.getAllDisciplines().subscribe(data => {
      console.log(data)
      this.disciplines = data;
      console.log(this.disciplines)
    });
  }

  openFacilityModal(){
    this.router.navigate(['/create-facility']);
    
  }

  openAccountRequests(){

    this.router.navigate(['/accountRequest']);
    
  }

  openAddDiscipline(){

    this.isDisciplineOpen=true

  }
  closeisDisciplineOpen(){
    this.isDisciplineOpen=false
  }
  

  submitSearch(updateUrl: boolean = true): void {
    const searchValues = this.searchFacilityForm.value;

    if (updateUrl) {
      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: {
          city: searchValues.city || null,
          disciplineId: searchValues.disciplineId || null,
          fromRate: searchValues.fromRate || null,
          toRate: searchValues.toRate || null,
          fromWorkTime: searchValues.fromWorkTime || null,
          toWorkTime: searchValues.toWorkTime || null
        },
        queryParamsHandling: 'merge'
      });
    }

    this.facilityService.searchFacilities(
      searchValues.city,
      searchValues.disciplineId,
      searchValues.fromRate,
      searchValues.toRate,
      searchValues.fromWorkTime,
      searchValues.toWorkTime
    ).subscribe(results => {
      this.facilities = results;
    });
  }
  logout() {
    localStorage.removeItem('user');  
    this.router.navigate(['/auth/login']);
  }
  getCurrentUserId(): void {
    const token = localStorage.getItem("user");
    console.log("Token:", token);
    if (token) {
      try {
        const decodedToken: any = jwtDecode(token);
        console.log("Tokennnnnnnn:", decodedToken); 
        this.roleNow = decodedToken.role[0]?.name || ''
        this.currentUserId = decodedToken.id;
        
      } catch (error) {
        console.error("Error decoding token:", error);
      }
    } else {
      console.warn("Token not found in localStorage.");
    }
  }

  

  

}
