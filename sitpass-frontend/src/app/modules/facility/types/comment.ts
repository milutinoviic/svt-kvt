import { User } from "./user";

export interface Comment {
    id: number;
    text: string;
    createdAl: string; 
    user: User;
    replyComment: Comment | null;
}