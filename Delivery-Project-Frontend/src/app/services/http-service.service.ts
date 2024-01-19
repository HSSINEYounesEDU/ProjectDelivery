import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) { }

  public postRequest(url: string, request: any): Observable <any>{
    return this.http.post<any>(url, request);
  }

  public getRequest(url: string): Observable<any>{
    return this.http.get<any>(url);
  }
  public getRequestWithParams(baseUrl: string, userType: string, search: string = 'all') {
    let url = `${baseUrl}/${userType}/0/20/${search}`;
    return this.http.get<any>(url);
  }


}
