export interface CommentCreate{
    text: string;
    userId: number;
    repliesToCommentId?: number;
}