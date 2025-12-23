import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {  tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private API_URL = 'http://localhost:8080/auth';
  private http = inject(HttpClient);

  constructor() {}

  register(userData: any){
    return this.http.post(`${this.API_URL}/register`, userData, {responseType: 'text'});
  }

  login(cridentials: any){
    return this.http.post<any>(`${this.API_URL}/login`,cridentials).pipe(
      tap(response=>{
        if(response && response.token){
        localStorage.setItem('token', response.token);
        }
      })
    );
  }

  logout(){
    localStorage.removeItem('token');
  }

  getToken(){
    return localStorage.getItem('token');
  }


  isLoggedIn(): boolean{
    return !!localStorage.getItem('token');
  }

}
