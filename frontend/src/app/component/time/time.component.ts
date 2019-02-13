import { Component, OnInit } from '@angular/core';
import {TimeService} from "../../service/time.service";

@Component({
  selector: 'app-time',
  templateUrl: './time.component.html',
  styleUrls: ['./time.component.css']
})
export class TimeComponent implements OnInit {
  time: string;

  constructor(private timeService: TimeService) { }

  ngOnInit() {}

  onTime() {
    this.timeService.getCurrentTime().subscribe(
      (time) => {this.time = time}
    );
  }
}
