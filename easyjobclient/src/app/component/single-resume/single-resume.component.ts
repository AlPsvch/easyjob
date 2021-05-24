import {Component, OnInit} from '@angular/core';
import {Resume} from "../../model/resume/resume";
import {ActivatedRoute} from "@angular/router";
import {ResumeService} from "../../service/resume.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-single-resume',
  templateUrl: './single-resume.component.html',
  styleUrls: ['./single-resume.component.css']
})
export class SingleResumeComponent implements OnInit {

  public resumeId: number;
  public resume: Resume;

  constructor(private resumeService: ResumeService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    if (!localStorage.getItem('firstReload') || localStorage.getItem('firstReload') == 'true') {
      localStorage.setItem('firstReload', 'false');
      window.location.reload();
    } else {
      localStorage.setItem('firstReload', 'true');
    }

    this.resumeId = parseInt(this.route.snapshot.paramMap.get('id'));

    this.getResume();
  }

  public getResume() {
    this.resumeService.getResume(this.resumeId).subscribe(
      response => {
        this.resume = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }

}
