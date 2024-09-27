import { Comment } from "./comment";
import { Facility } from "./facility";
import { Rate } from "./rate";
import { User } from "./user";

export interface Review {
    id?: number;
    createdAt?: string; 
    exerciseCount: number;
    hidden?: boolean;
    facility: Facility;
    user: User;
    rate: Rate;
    comment: Comment;
}