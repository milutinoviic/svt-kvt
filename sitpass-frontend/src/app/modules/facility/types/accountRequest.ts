export interface AccountRequest {
    id?: number;           
    email: string;        
    password: string; 
    createdAt?: string;     
    address: string;      
    status?: string;      
    rejectionReason?: string; 
}
