import { Image } from "./image";

export interface User {
    id?: number;
    name: string;
    surName: string;
    address: string;
    phoneNumber: string;
    birthday: string; 
    city: string;
    email: string;
    password?: string;
    createdAl?: string; 
    zipCode?: string;
    image?: Image | null;
}