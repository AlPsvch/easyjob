import {Component, OnInit} from '@angular/core';
import {CheckboxItem} from "../../model/checkbox-item";
import {Router} from "@angular/router";
import {ResumeService} from "../../service/resume.service";
import {NgForm} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";
import {Resume} from "../../model/resume/resume";

@Component({
  selector: 'app-add-resume',
  templateUrl: './add-resume.component.html',
  styleUrls: ['./add-resume.component.css']
})
export class AddResumeComponent implements OnInit {

  gendersList: CheckboxItem[];
  jobCategoriesList: CheckboxItem[];

  constructor(private resumeService: ResumeService, private router: Router) {
  }

  ngOnInit(): void {
    if (!localStorage.getItem('firstReload') || localStorage.getItem('firstReload') == 'true') {
      localStorage.setItem('firstReload', 'false');
      window.location.reload();
    } else {
      localStorage.setItem('firstReload', 'true');
    }

    this.getGenders();
    this.getJobCategories();
  }

  onAddResume(addForm: NgForm) {
    console.log(addForm.value);
    this.resumeService.addResume(addForm.value).subscribe(
      (response: Resume) => {
        this.router.navigate(['/resumes', response.id]);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  getGenders() {
    this.gendersList = [
      {code: "MALE", description: "Мужской", isSelected: false},
      {code: "FEMALE", description: "Женский", isSelected: false}
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
