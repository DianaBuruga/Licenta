import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { TokenDto } from './services/models';


@Injectable({
  providedIn: 'root'
})
export class MyHttpService {

  token: string = "";
  
  getTokenValue(){
    return this.token;
  }

  constructor(private http: HttpClient) { }

  getToken(code: string): Observable<string> {
    return this.http.get<TokenDto>("http://localhost:8081/auth/callback?code=" + code, {observe: "response"})
      .pipe(map((response: HttpResponse<TokenDto>) => {
        if (response.status === 200 && response.body !== null) {
          return response.body.token as string;
        }
        return "";
      }));
  }
}