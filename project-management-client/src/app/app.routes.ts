import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login/login.component';
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { authGuard } from './core/guards/auth.guard';
import { SignupComponent } from './features/auth/signup/signup.component';
import { DetailsComponent } from './features/details/details/details.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: SignupComponent }, 
  
  // Route protégée : accessible uniquement si connecté
  { 
    path: 'dashboard', 
    component: DashboardComponent, 
    canActivate: [authGuard] 
  },

  { path: 'project/:id',
    component: DetailsComponent,
    canActivate: [authGuard] 
  },

  // Redirection par défaut
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
