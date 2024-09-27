import { CommentCreate } from "./comment-create";
import { Rate } from "./rate";

export interface ReviewCreate{
    facilityId : number,
    userId: number,
    rate:Rate,
    comment?:CommentCreate

}