/**
 * K6 Soak Test (Endurance Test)
 * Runs consistent load over extended period to detect memory leaks and degradation
 * 
 * Test Goal: Identify memory leaks, resource exhaustion, and performance degradation
 * Duration: 30 minutes (can be extended to hours for production)
 * Virtual Users: Steady 30-50 users
 * 
 * Key Metrics to Watch:
 * - Does response time increase over time? (indicates memory leak)
 * - Does error rate increase over time?
 * - Are there residual errors after ramp-down?
 */

import { CONFIG } from './config.js';
import { userJourney } from './scenarios/user-journey.js';

export const options = {
  stages: [
    { duration: '2m', target: 30 },   // Ramp up
    { duration: '5m', target: 30 },  // Sustain load for 25 minutes
    { duration: '2m', target: 50 },   // Small spike to test stability
    { duration: '5m', target: 50 },  // Continue at higher load
    { duration: '2m', target: 0 },    // Ramp down
  ],
  thresholds: CONFIG.THRESHOLDS,
  tags: {
    test_type: 'soak',
  },
};

export default function () {
  userJourney();
}
