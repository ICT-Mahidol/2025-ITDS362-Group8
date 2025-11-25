import { sleep } from 'k6';
import { CONFIG } from '../config.js';

/**
 * Simulate realistic user think time between actions
 */
export function thinkTime() {
  const min = CONFIG.THINK_TIME.min;
  const max = CONFIG.THINK_TIME.max;
  const duration = Math.random() * (max - min) + min;
  sleep(duration);
}

/**
 * Extract cookies from response headers
 */
export function extractCookies(response) {
  const cookies = {};
  const setCookieHeaders = response.headers['Set-Cookie'];
  
  if (setCookieHeaders) {
    const cookieArray = Array.isArray(setCookieHeaders) 
      ? setCookieHeaders 
      : [setCookieHeaders];
    
    cookieArray.forEach(cookie => {
      const parts = cookie.split(';')[0].split('=');
      if (parts.length === 2) {
        cookies[parts[0].trim()] = parts[1].trim();
      }
    });
  }
  
  return cookies;
}

/**
 * Build cookie header string from cookies object
 */
export function buildCookieHeader(cookies) {
  return Object.entries(cookies)
    .map(([key, value]) => `${key}=${value}`)
    .join('; ');
}
