import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Resume} from "../model/resume/resume";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ResumeService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getResumes(pageNumber: number = 0, searchParam: string = "", jobCategories: string = "", genderParam: string = ""): Observable<Resume[]> {
    let url = '/resume/find?pageNumber=' + pageNumber;
    url += searchParam !== "" ? "&search=" + searchParam : "";
    url += jobCategories !== "" ? "&jobCategories=" + jobCategories : "";
    url += genderParam !== "" ? "&gender=" + genderParam : "";

    return this.http.get<Resume[]>(this.apiServerUrl + url)
  }

  public getResume(resumeId: number): Observable<Resume> {
    return this.http.get<Resume>(this.apiServerUrl + '/resume/find/' + resumeId)
  }

  public addResume(resume: Resume): Observable<Resume> {
    return this.http.post<Resume>(this.apiServerUrl + '/resume/add', resume)
  }

  public updateResume(resume: Resume): Observable<Resume> {
    return this.http.put<Resume>(this.apiServerUrl + '/resume/update', resume)
  }

  public deleteResume(resumeId: number): Observable<void> {
    return this.http.delete<void>(this.apiServerUrl + '/resume/delete/' + resumeId)
  }
}
