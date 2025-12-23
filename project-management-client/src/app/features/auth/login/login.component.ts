import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { CommonModule } from '@angular/common'; // Pour *ngIf

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink], 
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  loginForm: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.email]], 
    password: ['', [Validators.required, Validators.minLength(3)]] 
  });

  errorMessage: string = '';

  
  onSubmit() {
    
    if (this.loginForm.invalid) {
      return;
    }

    
    const credentials = this.loginForm.value;

    
    this.authService.login(credentials).subscribe({
      next: (response) => {
        
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = "Email ou mot de passe incorrect.";
      }
    });
  }
}