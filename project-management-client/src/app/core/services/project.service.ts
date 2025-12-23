import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private API_URL = 'http://localhost:8080/api/project';
  private http = inject(HttpClient);

  constructor() { }


  addProject(projectInfo: any){
    return this.http.post(`${this.API_URL}`,projectInfo);
  }

  getProjects(){
    return this.http.get<any[]>(`${this.API_URL}`)
  }

  getProjectDetails(idProject: number){
    return this.http.get(`${this.API_URL}/${idProject}`);
  }
}
