// K6 Test Configuration for Medusa Store
// Project Assignment 2 - Group 8

export const CONFIG = {
  // Base URL Configuration
  // Switch between local and production environments
  // BASE_URL: 'http://localhost:8000', // Storefront (Active environment)
  // BACKEND_URL: 'http://localhost:9000', // Medusa Backend API
  BASE_URL: 'http://10.34.112.158:8000', // Production storefront (commented out)
  BACKEND_URL: 'http://10.34.112.158:9000', // Production backend (commented out)
  
  // Storefront paths
  PATHS: {
    HOME: '/dk',
    STORE: '/dk/store',
    LOGIN: '/dk/account/login',
    PRODUCTS: '/dk/products',
  },

  // User credentials (already registered)
  USER: {
    email: 'group8@store.local',
    password: 'test301408',
  },

  // Performance thresholds
  THRESHOLDS: {
    http_req_duration: ['p(95)<1000'], // 95% of requests must complete under 1s
    http_req_failed: ['rate<0.01'],    // Error rate must be below 1%
  },

  // Think time configuration (simulating real user behavior)
  THINK_TIME: {
    min: 1,
    max: 5,
  },
};
