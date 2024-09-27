import { Facility } from "./facility";
import { User } from "./user";

export interface Image{
    id:number,
    path:string,
    user: User | null;
    facility: Facility | null;
  
}