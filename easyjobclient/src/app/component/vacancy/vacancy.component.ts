import {Component, OnInit} from '@angular/core';
import {Vacancy} from "../../model/vacancy/vacancy";
import {VacancyService} from "../../service/vacancy.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-vacancy',
  templateUrl: './vacancy.component.html',
  styleUrls: ['./vacancy.component.css']
})
export class VacancyComponent implements OnInit {

  public vacancies: Vacancy[];

  public totalPages: number = 0;
  public currentPage: number;
  public fromPage: number;
  public toPage: number;
  public maxPagesNavigation: number = 5;
  public navigationPages: number[];

  constructor(private vacancyService: VacancyService, private router: Router) {
  }

  ngOnInit(): void {
    if (!localStorage.getItem('firstReload') || localStorage.getItem('firstReload') == 'true') {
      localStorage.setItem('firstReload', 'false');
      window.location.reload();
    } else {
      localStorage.setItem('firstReload', 'true');
    }

    this.getVacancies();
  }

  public getVacancies(pageNumber: number = 0): void {
    this.vacancyService.getVacancies(pageNumber).subscribe(
      response => {
        this.vacancies = response['content'];
        this.totalPages = response['totalPages'];
        this.currentPage = response['number'];
        this.updateNavigationData()
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  private updateNavigationData() {
    if (this.totalPages <= this.maxPagesNavigation) {
      this.fromPage = 0;
      this.toPage = this.totalPages;
    } else {
      this.fromPage = this.currentPage - Math.floor(this.maxPagesNavigation / 2);
      this.toPage = this.currentPage + Math.floor(this.maxPagesNavigation / 2) + 1;

      if (this.fromPage < 0) {
        this.fromPage = 0;
        this.toPage = this.maxPagesNavigation;
      }
      if (this.toPage > this.totalPages) {
        this.toPage = this.totalPages;
        this.fromPage = this.totalPages - this.maxPagesNavigation;
      }
    }

    this.navigationPages = Array.from({length: (this.toPage - this.fromPage)}, (_, i) => i + this.fromPage + 1)
  }

  openVacancy(vacancy: Vacancy) {
    this.router.navigate(['/vacancies', vacancy.id]);
  }
}
