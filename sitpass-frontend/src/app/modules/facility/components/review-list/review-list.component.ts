import { Component, OnInit } from '@angular/core';
import { Review } from '../../types/review';
import { ActivatedRoute } from '@angular/router';
import { ReviewService } from '../../services/review.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommentService } from '../../services/comment.service';
import { jwtDecode } from 'jwt-decode';
import { Comment } from '../../types/comment';
import { da } from 'date-fns/locale';
import { ManagesService } from '../../services/manages.service';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.scss'
})
export class ReviewListComponent implements OnInit {

  form: FormGroup;
  isModalOpen = false; 
  selectedCommentId!: number;
  commentNew!:Comment
  currentUserId!:number
  facilityId!:number
  reviews:Review[]=[]
  isExist!:boolean
  roleNow!:string

  

  constructor(private reviewService:ReviewService,
    private route:ActivatedRoute,
    private fb: FormBuilder,
    private commnetService:CommentService,
    private managesService:ManagesService){
    this.form = this.fb.group({
      commentText: ['', [Validators.required, Validators.minLength(3)]], 
    });
  }


ngOnInit(): void {
  this.route.params.subscribe(params=>{
    this.facilityId=+params['id'];
    this.getReview(this.facilityId);
    this.getCurrentUserId()

    
  });
  
}

getReview(id:number):void{
  this.reviewService.getReviews(id).subscribe(data=>{this.reviews=data;console.log(this.reviews)})
}
 


average(prvi: number, drugi: number, treci: number, cetvrti: number): number {
  
  const zbir = prvi + drugi + treci + cetvrti;
  
  
  return zbir / 4;
}



  openReplyModal(commentId: number) {
    this.selectedCommentId = commentId;
    this.isModalOpen = true; 
    this.form.reset(); 
  }

  closeReplyModal() {
    this.isModalOpen = false; 
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
        this.getManages()
      } catch (error) {
        console.error("Error decoding token:", error);
      }
    } else {
      console.warn("Token not found in localStorage.");
    }
  }

  sendComment() {
    if (this.form.valid) {
      const commentObject = {
        commentId: this.selectedCommentId,
        userId: this.currentUserId,
        content: this.form.value.commentText,
      };
      if(this.selectedCommentId !== null){
      this.commnetService.createCommentToReply(commentObject).subscribe(data=>{
        this.commentNew=data
        console.log(this.commentNew)
        alert("uspjesno si dodao macak")
        window.location.reload();
      })
      this.closeReplyModal();

      }
       
    }
  }


  onClickDelete(id: number | undefined) {
    if(id !== undefined){
      this.reviewService.deleteReview(id).subscribe(() => {
      window.location.reload();
        
       console.log("usloo")
      }, error => {
        console.error('Greška prilikom brisanja recenzije:', error);
      });
    }
    
     
    
  }

  onClickHide(id: number | undefined) {
    if(id !== undefined){

      this.reviewService.hiddenReview(id).subscribe(() => {
        window.location.reload();
      
        console.log("Uslooo")
      }, error => {
        console.error('Greška prilikom sakrivanja recenzije:', error);
      });
    }

    }
  
    getManages(){
      this.managesService.isManager(this.currentUserId,this.facilityId).subscribe(data=>{
        this.isExist=data
      })
    }
 

}
