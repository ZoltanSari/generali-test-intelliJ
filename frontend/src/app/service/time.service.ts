import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TimeService {

  constructor(private http: HttpClient) { }

  getCurrentTime()  {
    return this.http.get<string>(`http://localhost:8080/time`);
  }
}
