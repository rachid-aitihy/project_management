import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-signup',
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  loginForm: FormGroup = this.fb.group({
    username: ['', [Validators.required]], 
    email: ['',[Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(4)]] 
  });

  errorMessage: string = '';

  
  onSubmit(){
    if(this.loginForm.invalid){
      return
    }

    const signupInfo = this.loginForm.value;

    this.authService.register(signupInfo).subscribe({
      next: response =>{
        console.log(response);
        this.router.navigate(['/login']);
      },
      error: err=>{
          console.error(err);
          this.errorMessage = "invalid input"
      }
    });
  }

}
