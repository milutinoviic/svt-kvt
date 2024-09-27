import { Component, OnInit } from '@angular/core';
import { User } from '../../types/user';
import { UserService } from '../../services/user.service';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import { ManagesService } from '../../services/manages.service';
import { ReviewService } from '../../services/review.service';
import { Review } from '../../types/review';
import { Exercise } from '../../types/exercise';
import { ExerciseService } from '../../services/exercise.service';
import { ExerciseDetailsDto } from '../../types/exerciseDetailsDto';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit {

  currentUserId!:number;
  userObject!:User
  isExist!:boolean
  facilityId!:number
  reviewList:Review[]=[]
  exerciseList:ExerciseDetailsDto[]=[]

  constructor(
    private userService:UserService,
    private router:Router,
    private manageService:ManagesService,
    private reviewService:ReviewService,
    private exerciseService:ExerciseService) {
    
  }
  ngOnInit(): void {
    this.getCurrentUserId();
    this.getManages()
    this.loadReview()
    this.loadExcercise()
  }

  logout() {
    localStorage.removeItem('user');  
    this.router.navigate(['/auth/login']);
  }

  loadReview(){
    this.reviewService.getActiveReviewsByUserId(this.currentUserId).subscribe(data=>{
      this.reviewList=data
      
      console.log(this.reviewList)
      
    })
  }




  loadUser(): void {
    this.userService.getUserById(this.currentUserId).subscribe(data => {
      this.userObject = data;
      console.log(this.userObject);
      });
  
    };
  

    average(prvi: number, drugi: number, treci: number, cetvrti: number): number {
  
      const zbir = prvi + drugi + treci + cetvrti;
      
      
      return zbir / 4;
    }

  onYourFacility(){

    this.router.navigate(['/facility/'+this.facilityId]);

    }

    getManages() {
      this.manageService.getFacilityIdForUser(this.currentUserId).subscribe(
        data => {
          this.facilityId = data;
          this.isExist = this.facilityId !== 0; 
        },
        error => {
          console.error('Greška prilikom dobijanja ID-a objekta:', error);
          this.isExist = false;
        }
      );
    }
    

    onClickDelete(id: number | undefined) {
      if(id !== undefined){
        this.reviewService.deleteReview(id).subscribe(() => {
          alert("Uspjeno si obrisao komentar")
        window.location.reload();
          
         console.log("usloo")
        }, error => {
          console.error('Greška prilikom brisanja recenzije:', error);
        });
      }
      
    }

  getCurrentUserId(): void {
    const token = localStorage.getItem("user");
    console.log("Token:", token);
    if (token) {
      try {
        const decodedToken: any = jwtDecode(token);
        console.log("Tokennnnnnnn:", decodedToken);
        this.currentUserId = decodedToken.id;
        this.loadUser(); 
      } catch (error) {
        console.error("Error decoding token:", error);
      }
    } else {
      console.warn("Token not found in localStorage.");
    }
  }




loadExcercise(){
  this.exerciseService.getExercisesByUserId(this.currentUserId).subscribe(data=>{
    this.exerciseList=data
    console.log("----------------------------")
    console.log(this.exerciseList)
    console.log("----------------------------")
  })

}




}
