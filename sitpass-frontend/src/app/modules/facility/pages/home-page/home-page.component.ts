import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Facility } from '../../types/facility';
import { FacilityService } from '../../services/facility.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})

export class HomePageComponent implements OnInit{
  
  searchFacilityForm!: FormGroup;
  facilities: Facility[] = [];
  currentCity!:string
  constructor(private router: Router,
    private facilityService: FacilityService,
    private fb: FormBuilder,
    private route: ActivatedRoute
  ){

    this.searchFacilityForm = this.fb.group({
      city: [''],
      today: ['']
    });

  }
  ngOnInit(): void {
    this.getCurrentUserId()

    this.route.queryParams.subscribe(params => {
      this.searchFacilityForm.patchValue({
        city: params['city'] || '',
        today: params['today'] || new Date().toISOString().split('T')[0] // Ako želiš današnji datum
      });
      this.submitSearch(); // Inicijalno učitavanje
    });
    
  }

  submitSearch(): void {
    const { city, today } = this.searchFacilityForm.value;
    this.facilityService.searchForHomePage(city, today).subscribe(
      (result: Facility[]) => {
        console.log('Result from API:', result); 
        this.facilities = result;
      },
      error => {
        console.error('Greška prilikom pretrage:', error);
      }
    );
  }


  logout() {
    localStorage.removeItem('user');  
    this.router.navigate(['/auth/login']);
  }


  navigateToCity(){
    this.router.navigate([], {
      queryParams: { city: this.currentCity },
      
    });

  }


  navigateToToday(){

    const today = new Date().toISOString().split('T')[0];
    console.log(today)
    this.router.navigate([], {
      queryParams: { today },
      
    });

  }

  getCurrentUserId(): void {
    const token = localStorage.getItem("user");
    console.log("Token:", token);
    if (token) {
      try {
        const decodedToken: any = jwtDecode(token);
        console.log("Tokennnnnnnn:", decodedToken);
        this.currentCity=decodedToken.city;
      } catch (error) {
        console.error("Error decoding token:", error);
      }
    } else {
      console.warn("Token not found in localStorage.");
    }
  }

  
  navigateToPopular(){
    this.router.navigate(['/homePage']);

  }

}
