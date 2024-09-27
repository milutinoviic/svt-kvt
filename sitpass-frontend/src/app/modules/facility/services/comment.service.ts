import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CommentReplyDto } from '../types/replyToComment';
import { Observable } from 'rxjs';
import { Comment } from '../types/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http:HttpClient) { }

  private _url:string ="api/comment/replyComment"

  createCommentToReply(comment:CommentReplyDto): Observable<Comment> {
    return this.http.post<Comment>(this._url,comment);
  }
}
