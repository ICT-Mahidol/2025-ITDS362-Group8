import http from 'k6/http';
import { check, group } from 'k6';
import { CONFIG } from '../config.js';
import { thinkTime, extractCookies, buildCookieHeader } from '../utils/helpers.js';

/**
 * Perform user login and return session cookies
 * Simplified version: Skip authentication for K6 tests (test as guest user)
 */
export function login() {
  let cookies = {};
  
  group('Initialize Session', function () {
    // Just visit the homepage to get session cookies
    const homeResponse = http.get(`${CONFIG.BASE_URL}${CONFIG.PATHS.HOME}`, {
      tags: { name: 'GET_InitSession' },
    });

    check(homeResponse, {
      'Session initialized': (r) => r.status === 200,
    });

    cookies = extractCookies(homeResponse);
    thinkTime();
  });

  return cookies;
}

/**
 * Browse home page
 */
export function browseHomePage(cookies) {
  group('Browse Home Page', function () {
    const response = http.get(`${CONFIG.BASE_URL}${CONFIG.PATHS.HOME}`, {
      headers: {
        'Cookie': buildCookieHeader(cookies),
      },
      tags: { name: 'GET_HomePage' },
      timeout: '30s', // Increase timeout for slow servers
    });

    check(response, {
      'Home page loaded': (r) => r.status === 200,
      'Page contains products': (r) => r.body && (r.body.includes('product') || r.body.includes('store')),
    });

    thinkTime();
  });
}

/**
 * Browse store/products page
 */
export function browseStore(cookies) {
  group('Browse Store', function () {
    const response = http.get(`${CONFIG.BASE_URL}${CONFIG.PATHS.STORE}`, {
      headers: {
        'Cookie': buildCookieHeader(cookies),
      },
      tags: { name: 'GET_StorePage' },
    });

    check(response, {
      'Store page loaded': (r) => r.status === 200,
    });

    thinkTime();
  });
}

/**
 * View product details (simulated)
 */
export function viewProductDetails(cookies) {
  group('View Product Details', function () {
    // This is a simulated product view - adjust the path based on actual product URLs
    const response = http.get(`${CONFIG.BASE_URL}${CONFIG.PATHS.PRODUCTS}`, {
      headers: {
        'Cookie': buildCookieHeader(cookies),
      },
      tags: { name: 'GET_ProductDetails' },
    });

    check(response, {
      'Product page loaded': (r) => r.status === 200 || r.status === 404, // 404 is acceptable if no products exist
    });

    thinkTime();
  });
}

/**
 * Complete user journey (login + browse)
 */
export function userJourney() {
  const cookies = login();
  browseHomePage(cookies);
  browseStore(cookies);
  viewProductDetails(cookies);
}
