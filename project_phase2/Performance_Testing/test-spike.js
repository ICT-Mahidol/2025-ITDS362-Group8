/**
 * K6 Spike Test
 * Simulates sudden traffic surge (e.g., flash sale, viral event)
 * 
 * Test Goal: Test system's ability to handle sudden traffic spikes and recovery
 * Duration: 4.5 minutes
 * Virtual Users: Sudden spike from 5 to 25 users
 * 
 * Key Metrics to Watch:
 * - How does the system respond during the spike?
 * - How long does recovery take after spike ends?
 * - Are there cascading failures?
 */

import { CONFIG } from './config.js';
import { userJourney } from './scenarios/user-journey.js';

export const options = {
  stages: [
    { duration: '30s', target: 5 },   // Normal load
    { duration: '30s', target: 25 },  // SPIKE! Sudden surge
    { duration: '1m', target: 25 },   // Maintain spike
    { duration: '30s', target: 5 },   // Drop back to normal
    { duration: '1m', target: 5 },    // Observe recovery
    { duration: '30s', target: 0 },   // Ramp down
  ],
  thresholds: {
    // More lenient thresholds during spike
    http_req_duration: ['p(95)<3000'],
    http_req_failed: ['rate<0.20'], // Allow 20% error rate during spike
  },
  tags: {
    test_type: 'spike',
  },
};

export default function () {
  userJourney();
}
