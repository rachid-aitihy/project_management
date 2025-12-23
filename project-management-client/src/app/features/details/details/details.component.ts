import { Component, inject, OnInit, ViewChild, ElementRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ProjectService } from '../../../core/services/project.service';
import { TaskService } from '../../../core/services/task.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-project-details',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css' 
})
export class DetailsComponent implements OnInit {

  private route = inject(ActivatedRoute);
  private projectService = inject(ProjectService);
  private taskService = inject(TaskService);
  private fb = inject(FormBuilder);

  project: any = null; 
  projectId!: number;

  taskForm: FormGroup = this.fb.group({
    taskName: ['', Validators.required],
    taskDescription: [''],
    taskDueDate: [Date, Validators.required] 
  });

  @ViewChild('closeTaskModalBtn') closeTaskModalBtn!: ElementRef;

  ngOnInit() {
    // Récupérer l'ID depuis l'URL (ex: /projects/5)
    this.projectId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadProjectData();
  }

  loadProjectData() {
    this.projectService.getProjectDetails(this.projectId).subscribe({
      next: (res) => {
        console.log("Données reçues:", res);
        this.project = res;
      },
      error: (err) => console.error(err)
    });
  }

  

  addTask() {
    if (this.taskForm.invalid) return;

    const formValue = this.taskForm.value;
    const taskData = {
    taskName: formValue.taskName, 
    taskDescription: formValue.taskDescription,
    projectId: this.projectId,
    taskDueDate: formValue.taskDueDate ? formValue.taskDueDate + 'T00:00:00' : null
    };

    this.taskService.addTask(taskData).subscribe({
      next: () => {
        this.loadProjectData(); 
        this.taskForm.reset();
        this.closeTaskModalBtn.nativeElement.click(); 
      },
      error: (err) => alert("Erreur ajout tâche")
    });
  }

  markAsCompleted(task: any) {
    task.completed = !task.completed;
    
    this.taskService.markAsComplete(task).subscribe({
      next: () => this.loadProjectData(), 
      error: () => task.completed = !task.completed 
    });
  }

  deleteTask(taskId: number) {
    if(confirm("Supprimer cette tâche ?")) {
      this.taskService.deleteTask(taskId).subscribe({
        next: () => this.loadProjectData(), 
        error: (err) => alert("Erreur suppression")
      });
    }
  }
}