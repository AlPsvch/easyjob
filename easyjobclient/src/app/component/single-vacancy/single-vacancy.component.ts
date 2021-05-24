import {Component, OnInit} from '@angular/core';
import {VacancyService} from "../../service/vacancy.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Vacancy} from "../../model/vacancy/vacancy";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-single-vacancy',
  templateUrl: './single-vacancy.component.html',
  styleUrls: ['./single-vacancy.component.css']
})
export class SingleVacancyComponent implements OnInit {

  public vacancyId;
  public vacancy: Vacancy;

  constructor(private vacancyService: VacancyService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    if (!localStorage.getItem('firstReload') || localStorage.getItem('firstReload') == 'true') {
      localStorage.setItem('firstReload', 'false');
      window.location.reload();
    } else {
      localStorage.setItem('firstReload', 'true');
    }

    this.vacancyId = parseInt(this.route.snapshot.paramMap.get('id'));

    this.getVacancy();
  }

  public getVacancy() {
    this.vacancyService.getVacancy(this.vacancyId).subscribe(
      response => {
        this.vacancy = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }

}
