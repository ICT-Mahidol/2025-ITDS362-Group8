/**
 * K6 Soak Test (Endurance Test)
 * Runs consistent load over extended period to detect memory leaks and degradation
 * 
 * Test Goal: Identify memory leaks, resource exhaustion, and performance degradation
 * Duration: 13 minutes (reduced for testing, can be extended for production)
 * Virtual Users: Steady 5-10 users
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
    { duration: '1m', target: 5 },    // Ramp up
    { duration: '5m', target: 5 },    // Sustain load for 5 minutes
    { duration: '1m', target: 10 },   // Small spike to test stability
    { duration: '5m', target: 10 },   // Continue at higher load
    { duration: '1m', target: 0 },    // Ramp down
  ],
  thresholds: CONFIG.THRESHOLDS,
  tags: {
    test_type: 'soak',
  },
};

export default function () {
  userJourney();
}
