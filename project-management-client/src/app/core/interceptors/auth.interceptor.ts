import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();

  // 1. Check if the request is for Authentication (Login/Register)
  if (req.url.includes('/auth/login') || req.url.includes('/auth/register')) {
    // Do not attach token, just pass the request
    return next(req);
  }

  // 2. For all other requests, attach the token if it exists
  if (token) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(cloned);
  }

  return next(req);
};