import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { ReviewService } from '../../services/review.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentCreate } from '../../types/comment-create';
import { ExerciseService } from '../../services/exercise.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-review-add',
  templateUrl: './review-add.component.html',
  styleUrl: './review-add.component.scss'
})
export class ReviewAddComponent implements OnInit {

  @Output() reviewCreated = new EventEmitter<void>();

  
  id!:number
  form:FormGroup;
  countExercise!:number
  currentUserId!:number
  constructor(private fb:FormBuilder, private reviewService: ReviewService,private route:ActivatedRoute,  private router: Router,private exerciseService:ExerciseService){
    this.form=this.fb.group({
      exerciseCount: [{ value: this.countExercise, disabled: true }],
      equipment: ['', [Validators.required, Validators.min(1), Validators.max(10)]],
      staff: ['', [Validators.required, Validators.min(1), Validators.max(10)]],
      hygiene: ['', [Validators.required, Validators.min(1), Validators.max(10)]],
      space: ['', [Validators.required, Validators.min(1), Validators.max(10)]],
      comment: ['']
    }
      

    )
    
  }
  getCountExercise(): void {
    this.exerciseService.getCountExercise(this.id, this.currentUserId).subscribe(data => {
      this.countExercise = data;
      this.form.patchValue({
        exerciseCount: this.countExercise
      });
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
  
  

  ngOnInit(): void {

      this.route.params.subscribe(params=>{
      this.id=+params['id']
      this.getCurrentUserId(); 
      this.getCountExercise();
      
      
      });
      
    
   
  }
  createReview(): void {
    if(this.countExercise<=0){
      alert("Nisi nikad bio u facility ne mozes da ostavis utisak!")
      return;
    }
    let rate ={
      equipment:this.form.value.equipment,
      staff:this.form.value.staff,
      hygiene:this.form.value.hygiene,
      space:this.form.value.space


    };
    
    let comment: CommentCreate = {
      text: this.form.value.comment,
      userId: this.currentUserId
    }
    let reviewCreate = {
      exerciseCount:this.countExercise,
      hidden: false,
      facilityId: this.id,
      userId:this.currentUserId,
      rateCreateDto: rate,
      commentCreateDto: comment
    }
  
  this.reviewService.createReview(reviewCreate).subscribe( data => {
    console.log("KREIRANO?");
    console.log(data);
    alert("Uspjesno si kreirao review")
    
    this.reviewCreated.emit();  
  },
   error => {

  })

  }

}
