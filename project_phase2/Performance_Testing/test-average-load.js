/**
 * K6 Average Load Test
 * Simulates normal daily traffic to establish baseline performance
 * 
 * Test Goal: Determine normal system behavior under typical load
 * Duration: 3 minutes
 * Virtual Users: Gradual ramp from 5 to 15 users
 */

import { CONFIG } from './config.js';
import { userJourney } from './scenarios/user-journey.js';

export const options = {
  stages: [
    { duration: '30s', target: 5 },   // Ramp up to 5 users
    { duration: '1m', target: 10 },   // Increase to 10 users
    { duration: '1m', target: 15 },   // Peak at 15 users
    { duration: '30s', target: 0 },   // Ramp down to 0
  ],
  thresholds: CONFIG.THRESHOLDS,
  tags: {
    test_type: 'average_load',
  },
};

export default function () {
  userJourney();
}
