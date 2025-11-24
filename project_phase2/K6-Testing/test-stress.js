/**
 * K6 Stress Test
 * Pushes the system beyond normal capacity to find breaking points
 * 
 * Test Goal: Identify maximum capacity and failure threshold
 * Duration: 10 minutes
 * Virtual Users: Ramp from 1 to 200+ users
 * 
 * Key Metrics to Watch:
 * - At what VU count does p95 exceed 1000ms?
 * - At what VU count do errors start occurring?
 * - What is the maximum sustainable load?
 */

import { CONFIG } from './config.js';
import { userJourney } from './scenarios/user-journey.js';

export const options = {
  stages: [
    { duration: '2m', target: 50 },   // Ramp to normal load
    { duration: '2m', target: 100 },  // Increase to stress level
    { duration: '2m', target: 150 },  // Push further
    { duration: '2m', target: 200 },  // Maximum stress
    { duration: '2m', target: 0 },    // Ramp down
  ],
  thresholds: {
    // Relaxed thresholds for stress test (we expect failures)
    http_req_duration: ['p(95)<5000'], // Allow up to 5s for stress conditions
    http_req_failed: ['rate<0.50'],    // Allow up to 50% error rate
  },
  tags: {
    test_type: 'stress',
  },
};

export default function () {
  userJourney();
}
