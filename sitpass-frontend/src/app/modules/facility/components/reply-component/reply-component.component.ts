import { Component, Input, OnInit } from '@angular/core';
import { Comment } from '../../types/comment';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommentService } from '../../services/comment.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-reply-component',
  templateUrl: './reply-component.component.html',
  styleUrl: './reply-component.component.scss'
})
export class ReplyComponentComponent implements OnInit{
  @Input() comment!: Comment;

  form: FormGroup;
  isModalOpen = false; 
  selectedCommentId!: number;
  commentNew!:Comment
  currentUserId!:number
  

  ngOnInit(): void {
    this.getCurrentUserId()
    
  }

  constructor(private fb: FormBuilder,
    private commnetService:CommentService
  ){
    this.form = this.fb.group({
      commentText: ['', [Validators.required, Validators.minLength(3)]], 
    });
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
        this.currentUserId = decodedToken.id;
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
  

}
