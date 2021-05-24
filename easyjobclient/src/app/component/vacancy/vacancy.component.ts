import {Component, OnInit} from '@angular/core';
import {Vacancy} from "../../model/vacancy/vacancy";
import {VacancyService} from "../../service/vacancy.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {CheckboxItem} from "../../model/checkbox-item";

@Component({
  selector: 'app-vacancy',
  templateUrl: './vacancy.component.html',
  styleUrls: ['./vacancy.component.css']
})
export class VacancyComponent implements OnInit {

  employmentModesList: CheckboxItem[];
  jobCategoriesList: CheckboxItem[];

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

    this.getEmploymentModes();
    this.getJobCategories()
    this.getVacancies();
  }

  public getVacancies(pageNumber: number = 0, searchParam: string = ""): void {
    let employmentModeParam = this.buildParamsString(this.employmentModesList);
    let jobCategoryParam = this.buildParamsString(this.jobCategoriesList);

    this.vacancyService.getVacancies(pageNumber, searchParam, employmentModeParam, jobCategoryParam).subscribe(
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

  public searchVacancies(searchForm: NgForm) {
    let searchParam = searchForm.value.search;
    this.getVacancies(0, searchParam);
  }

  private buildParamsString(list: CheckboxItem[]): any {
    let params = "";

    for (let item of list) {
      if (item.isSelected) {
        params += item.code + ","
      }
    }

    if (params !== "") {
      params = params.substring(0, params.length - 1);
    }

    return params;
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

  getEmploymentModes() {
    this.employmentModesList = [
      {code: "FULL_TIME", description: "Полная занятость, полный день", isSelected: false},
      {code: "PART_TIME", description: "Неполный рабочий день", isSelected: false},
      {code: "INTERSHIP", description: "Неполный рабочий день, стажировка", isSelected: false}
    ]
  }

  getJobCategories() {
    this.jobCategoriesList = [
      {code: "CAR_BUSINESS", description: "Автомобильный бизнес", isSelected: false},
      {code: "ADMINISTRATIVE_STAFF", description: "Административный персонал", isSelected: false},
      {code: "BANKS_INVESTMENT_LEASING", description: "Банки, инвестиции, лизинг", isSelected: false},
      {code: "SECURITY", description: "Безопасность", isSelected: false},
      {code: "BOOKKEEPING", description: "Бухгалтерия", isSelected: false},
      {code: "RAW_MATERIALS_EXTRACTION", description: "Добыча сырья", isSelected: false},
      {code: "INFORMATION_TECHNOLOGY", description: "Информационные технологии, интернет, телеком", isSelected: false},
      {code: "ART_ENTERTAINMENT_MASS_MEDIA", description: "Искусство, развлечения, масс-медиа", isSelected: false},
      {code: "CONSULTING", description: "Консультирование", isSelected: false},
      {code: "MARKETING_ADVERTISING_PR", description: "Маркетинг, реклама, PR", isSelected: false},
      {code: "MEDICINE_PHARMACEUTICALS", description: "Медицина, фармацевтика", isSelected: false},
      {code: "SCIENCE_EDUCATION", description: "Наука, образование", isSelected: false},
      {code: "STUDENTS", description: "Начало карьеры, студенты", isSelected: false},
      {code: "SALES", description: "Продажи", isSelected: false},
      {code: "MANUFACTURING_AGRICULTURE", description: "Производство, сельское хозяйство", isSelected: false},
      {code: "WORKING_STAFF", description: "Рабочий персонал", isSelected: false},
      {code: "FITNESS", description: "Спортивные клубы, фитнес, салоны красоты", isSelected: false},
      {code: "INSURANCE", description: "Страхование", isSelected: false},
      {code: "REAL_ESTATE", description: "Строительство, недвижимость", isSelected: false},
      {code: "TRANSPORT_LOGISTICS", description: "Транспорт, логистика", isSelected: false},
      {code: "TOURISM", description: "Туризм, гостиницы, рестораны", isSelected: false},
      {code: "LAWYERS", description: "Юристы", isSelected: false}
    ]
  }
}
