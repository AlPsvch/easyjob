import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-personality-type',
  templateUrl: './personality-type.component.html',
  styleUrls: ['./personality-type.component.css']
})
export class PersonalityTypeComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
    if (!localStorage.getItem('firstReload') || localStorage.getItem('firstReload') == 'true') {
      localStorage.setItem('firstReload', 'false');
      window.location.reload();
    } else {
      localStorage.setItem('firstReload', 'true');
    }
  }

}
