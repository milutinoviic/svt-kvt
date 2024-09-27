import { Discipline } from "./discipline"
import { Image } from "./image"
import { WorkDay } from "./workday"

export interface Facility{
    id?:number,
    name:string,
    description:string,
    createdAt?:string,
    address:string,
    city:string,
    totalRating:number,
    active:boolean,
    workDays:WorkDay[],
    images:Image[],
    disciplines:Discipline[]
}