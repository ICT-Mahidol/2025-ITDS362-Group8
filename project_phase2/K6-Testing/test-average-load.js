/**
 * K6 Average Load Test
 * Simulates normal daily traffic to establish baseline performance
 * 
 * Test Goal: Determine normal system behavior under typical load
 * Duration: 5 minutes
 * Virtual Users: Gradual ramp from 1 to 50 users
 */

import { CONFIG } from './config.js';
import { userJourney } from './scenarios/user-journey.js';

export const options = {
  stages: [
    { duration: '1m', target: 10 },  // Ramp up to 10 users
    { duration: '2m', target: 30 },  // Increase to 30 users
    { duration: '1m', target: 50 },  // Peak at 50 users
    { duration: '1m', target: 0 },   // Ramp down to 0
  ],
  thresholds: CONFIG.THRESHOLDS,
  tags: {
    test_type: 'average_load',
  },
};

export default function () {
  userJourney();
}
