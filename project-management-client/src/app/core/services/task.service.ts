import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

private API_URL = 'http://localhost:8080/api/task';
  private http = inject(HttpClient);

  constructor() { }


  addTask(taskInfo: any){
    return this.http.post(`${this.API_URL}`,taskInfo);
  }

  getTasksOfProject(projectId: number){
    return this.http.get<any[]>(`${this.API_URL}/${projectId}`)
  }

  markAsComplete(taskInfo: any){
    return this.http.put(`${this.API_URL}/${taskInfo.taskId}`,taskInfo);
  }

  deleteTask(taskId:number){
    return this.http.delete(`${this.API_URL}/${taskId}`)
  }
}
