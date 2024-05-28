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

  constructor(private http: HttpClient) { }

  get(url: string): any {
    return this.http.get("http://localhost:8081" + url);
  }

  getPrivate(url: string): any {
    return this.http.get("http://localhost:8081" + url, {headers: new HttpHeaders({"Authorization": "Bearer " + this.token})});
  }

  getToken(code: string): Observable<string | undefined> {
    return this.http.get<TokenDto>("http://localhost:8081/auth/callback?code=" + code, {observe: "response"})
      .pipe(map((response: HttpResponse<TokenDto>) => {
        if (response.status === 200 && response.body !== null) {
          return response.body.token;
        }
        return undefined;
      }));
  }
}