import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Vacancy} from "../model/vacancy/vacancy";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class VacancyService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getVacancies(pageNumber: number = 0, searchParam: string = "", employmentModes: string = "", jobCategories: string = ""): Observable<Vacancy[]> {
    let url = '/vacancy/find?pageNumber=' + pageNumber;
    url += searchParam !== "" ? "&search=" + searchParam : "";
    url += employmentModes !== "" ? "&employmentModes=" + employmentModes : "";
    url += jobCategories !== "" ? "&jobCategories=" + jobCategories : "";

    return this.http.get<Vacancy[]>(this.apiServerUrl + url)
  }

  public getVacancy(vacancyId: number): Observable<Vacancy> {
    return this.http.get<Vacancy>(this.apiServerUrl + '/vacancy/find/' + vacancyId)
  }

  public addVacancy(vacancy: Vacancy): Observable<Vacancy> {
    return this.http.post<Vacancy>(this.apiServerUrl + '/vacancy/add', vacancy)
  }

  public updateVacancy(vacancy: Vacancy): Observable<Vacancy> {
    return this.http.put<Vacancy>(this.apiServerUrl + '/vacancy/update', vacancy)
  }

  public deleteVacancy(vacancyId: number): Observable<void> {
    return this.http.delete<void>(this.apiServerUrl + '/vacancy/delete/' + vacancyId)
  }
}
