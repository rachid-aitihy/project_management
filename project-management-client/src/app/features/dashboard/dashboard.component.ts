import { Component, ElementRef, inject, ViewChild } from '@angular/core';
import { OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectService } from '../../core/services/project.service';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  private projectService = inject(ProjectService);
  private router = inject(Router);
  private fb = inject(FormBuilder);
  projects: any[]=[];
  

  ngOnInit(){
    this.getMyProjects();
  }

  //cration d'une new project :
  projectForm : FormGroup= this.fb.group({
    projectName:['',[Validators.required,Validators.minLength(3)]],
    projectDescription:['',Validators.maxLength(300)]
  })

  @ViewChild('closeModalBtn') closeModalBtn!: ElementRef;

  createProject(){
    if(this.projectForm.invalid) return;
  
    const projectData = {
      projectName: this.projectForm.value.projectName,
      projectDescription: this.projectForm.value.projectDescription
    };

    this.projectService.addProject(projectData).subscribe({
      next: (Response)=>{
          console.log("project created succusfully");

          this.getMyProjects();

          this.projectForm.reset();

          this.closeModalBtn.nativeElement.click();
      },
      error: (err)=>{
        alert("error creating the project");
      }
    })
  }

  //load Projects
  getMyProjects(){

    this.projectService.getProjects().subscribe({
      next: response =>{
        console.log(response);
        this.projects=response;
      },
      error: err =>{
        console.error("something went wrong !!!!!!!!!!!");
      }
    })
    }

}
