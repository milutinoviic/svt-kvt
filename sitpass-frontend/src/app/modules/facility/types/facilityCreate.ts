
import { Image } from "./image"
import { WorkDay } from "./workday"

export interface CreateFacility{
    id?:number,
    name:string,
    description:string,
    createdAt?:string
    address:string,
    city:string,
    active?:boolean,
    workDays:WorkDay[],
    images:Image[],
    disciplinesIds:number[]
}