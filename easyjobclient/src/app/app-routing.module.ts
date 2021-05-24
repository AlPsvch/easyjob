import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {VacancyComponent} from "./component/vacancy/vacancy.component";
import {ContactComponent} from "./component/contact/contact.component";
import {SingleVacancyComponent} from "./component/single-vacancy/single-vacancy.component";
import {AddVacancyComponent} from "./component/add-vacancy/add-vacancy.component";
import {ResumeComponent} from "./component/resume/resume.component";
import {SingleResumeComponent} from "./component/single-resume/single-resume.component";
import {AddResumeComponent} from "./component/add-resume/add-resume.component";


const routes: Routes = [
  {path: 'vacancies', component: VacancyComponent},
  {path: 'vacancies/:id', component: SingleVacancyComponent},
  {path: 'vacancy/add', component: AddVacancyComponent},
  {path: 'resumes', component: ResumeComponent},
  {path: 'resumes/:id', component: SingleResumeComponent},
  {path: 'resume/add', component: AddResumeComponent},
  {path: 'contact', component: ContactComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

export const routingComponents = [
  VacancyComponent,
  SingleVacancyComponent,
  AddVacancyComponent,
  ResumeComponent,
  SingleResumeComponent,
  ContactComponent,
  AddResumeComponent
]
