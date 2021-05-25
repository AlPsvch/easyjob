import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {VacancyComponent} from "./component/vacancy/vacancy.component";
import {ContactComponent} from "./component/contact/contact.component";
import {SingleVacancyComponent} from "./component/single-vacancy/single-vacancy.component";
import {AddVacancyComponent} from "./component/add-vacancy/add-vacancy.component";
import {ResumeComponent} from "./component/resume/resume.component";
import {SingleResumeComponent} from "./component/single-resume/single-resume.component";
import {AddResumeComponent} from "./component/add-resume/add-resume.component";
import {PersonalityTypeComponent} from "./component/personality-type/personality-type.component";
import {HomeComponent} from "./component/home/home.component";
import {LoginComponent} from "./component/login/login.component";
import {RegistrationComponent} from "./component/registration/registration.component";


const routes: Routes = [
  {path: "", redirectTo: "/home", pathMatch: 'full'},
  {path: "home", component: HomeComponent},
  {path: 'vacancies', component: VacancyComponent},
  {path: 'vacancies/:id', component: SingleVacancyComponent},
  {path: 'vacancy/add', component: AddVacancyComponent},
  {path: 'resumes', component: ResumeComponent},
  {path: 'resumes/:id', component: SingleResumeComponent},
  {path: 'resume/add', component: AddResumeComponent},
  {path: 'personality', component: PersonalityTypeComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registry', component: RegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

export const routingComponents = [
  HomeComponent,
  VacancyComponent,
  SingleVacancyComponent,
  AddVacancyComponent,
  ResumeComponent,
  SingleResumeComponent,
  AddResumeComponent,
  PersonalityTypeComponent,
  ContactComponent,
  LoginComponent,
  RegistrationComponent
]
