import {Component, OnInit} from '@angular/core';
import {CheckboxItem} from "../../model/checkbox-item";
import {NgForm} from "@angular/forms";
import {VacancyService} from "../../service/vacancy.service";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {Vacancy} from "../../model/vacancy/vacancy";

@Component({
  selector: 'app-add-vacancy',
  templateUrl: './add-vacancy.component.html',
  styleUrls: ['./add-vacancy.component.css']
})
export class AddVacancyComponent implements OnInit {

  employmentModesList: CheckboxItem[];
  jobCategoriesList: CheckboxItem[];

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
    this.getJobCategories();
  }

  onAddVacancy(addForm: NgForm) {
    this.vacancyService.addVacancy(addForm.value).subscribe(
      (response: Vacancy) => {
        console.log(response)
        this.router.navigate(['/vacancies', response.id]);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
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
