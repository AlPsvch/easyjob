import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {VacancyComponent} from "./component/vacancy/vacancy.component";
import {ContactComponent} from "./component/contact/contact.component";
import {SingleVacancyComponent} from "./component/single-vacancy/single-vacancy.component";


const routes: Routes = [
  {path: 'vacancies', component: VacancyComponent},
  {path: 'vacancies/:id', component: SingleVacancyComponent},
  {path: 'contact', component: ContactComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

export const routingComponents = [VacancyComponent,ContactComponent, SingleVacancyComponent]
