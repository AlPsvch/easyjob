import {ContactInfo} from "./contactInfo";
import {Education} from "./education";

export interface Resume {
  id:number;
  name: string;
  firstName: string;
  lastName: string;
  experience: string;
  about: string;
  contactInfo: ContactInfo;
  education: Education;
  gender: string;
  active: boolean;
  publishDate: Date;
  resumeCategory: string;
}
