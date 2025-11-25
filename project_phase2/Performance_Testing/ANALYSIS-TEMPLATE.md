# Performance Testing Analysis Report
**Project Assignment 2 - Group 8**  
**Target System**: Medusa Store  
**Test Date**: [INSERT DATE]  
**Tester**: [INSERT NAME]

---

## 1. Test Environment

| Parameter | Value |
|-----------|-------|
| **Base URL** | http://localhost:8000 (or http://10.34.112.158:8000) |
| **Test User** | group8@store.local |
| **K6 Version** | [INSERT VERSION - run `k6 version`] |
| **Server Specs** | [INSERT: CPU, RAM, OS] |
| **Database** | PostgreSQL (Docker) |

---

## 2. Test Results Summary

### 2.1 Average Load Test

**Test Configuration**:
- Duration: 5 minutes
- Max VUs: 50
- Ramp Pattern: 0→10→30→50→0

**Results**:

| Metric | Value | Pass/Fail |
|--------|-------|-----------|
| http_req_duration (avg) | [INSERT]ms | [✓/✗] |
| http_req_duration (p95) | [INSERT]ms | [✓/✗] |
| http_req_failed | [INSERT]% | [✓/✗] |
| Total Requests | [INSERT] | - |
| Requests/sec | [INSERT] | - |

**Screenshot**: [INSERT TERMINAL OUTPUT]

**Observations**:
- [INSERT: System behavior under normal load]
- [INSERT: Any bottlenecks noticed]

---

### 2.2 Stress Test

**Test Configuration**:
- Duration: 10 minutes
- Max VUs: 200
- Ramp Pattern: 0→50→100→150→200→0

**Results**:

| VU Count | p95 Response Time | Error Rate | Status |
|----------|-------------------|------------|--------|
| 50 | [INSERT]ms | [INSERT]% | ✓ |
| 100 | [INSERT]ms | [INSERT]% | ✓/✗ |
| 150 | [INSERT]ms | [INSERT]% | ✓/✗ |
| 200 | [INSERT]ms | [INSERT]% | ✗ |

**Screenshot**: [INSERT TERMINAL OUTPUT]

**Observations**:
- [INSERT: Breaking point analysis]
- [INSERT: Error types observed]

---

### 2.3 Soak Test

**Test Configuration**:
- Duration: 56 minutes
- Sustained VUs: 30-50
- Pattern: Ramp up → Sustain → Small spike → Sustain → Ramp down

**Results**:

| Time Period | Avg Response Time | p95 Response Time | Error Rate |
|-------------|-------------------|-------------------|------------|
| 0-15 min | [INSERT]ms | [INSERT]ms | [INSERT]% |
| 15-30 min | [INSERT]ms | [INSERT]ms | [INSERT]% |
| 30-45 min | [INSERT]ms | [INSERT]ms | [INSERT]% |
| 45-56 min | [INSERT]ms | [INSERT]ms | [INSERT]% |

**Screenshot**: [INSERT TERMINAL OUTPUT]

**Observations**:
- [INSERT: Performance degradation over time?]
- [INSERT: Memory leak indicators?]

---

### 2.4 Spike Test

**Test Configuration**:
- Duration: 8 minutes
- Spike: 10 VUs → 200 VUs (in 30 seconds)
- Recovery Period: 2 minutes at 10 VUs

**Results**:

| Phase | VUs | Avg Response | p95 Response | Error Rate |
|-------|-----|--------------|--------------|------------|
| Before Spike | 10 | [INSERT]ms | [INSERT]ms | [INSERT]% |
| During Spike | 200 | [INSERT]ms | [INSERT]ms | [INSERT]% |
| Recovery (0-30s) | 10 | [INSERT]ms | [INSERT]ms | [INSERT]% |
| Recovery (30-60s) | 10 | [INSERT]ms | [INSERT]ms | [INSERT]% |
| Fully Recovered | 10 | [INSERT]ms | [INSERT]ms | [INSERT]% |

**Screenshot**: [INSERT TERMINAL OUTPUT]

**Observations**:
- [INSERT: System behavior during spike]
- [INSERT: Cascading failures?]

---

## 3. Assignment Questions - Detailed Answers

### Question 1: Maximum Concurrent Users (Before 1s Response Time)

**Answer**: The system can handle approximately **[INSERT NUMBER]** concurrent users before the 95th percentile response time exceeds 1 second.

**Evidence**:
- From stress test results:
  - At **[INSERT]** VUs: p(95) = **[INSERT]**ms ✓ (under 1000ms)
  - At **[INSERT]** VUs: p(95) = **[INSERT]**ms ✗ (exceeds 1000ms)

**Supporting Screenshot**: [INSERT]

**Business Impact**:
- [INSERT: Can the system handle expected daily traffic?]
- [INSERT: What happens during peak hours/sales?]

---

### Question 2: Failure Threshold (Stress Test)

**Answer**: The system started failing at approximately **[INSERT NUMBER]** Virtual Users.

**Failure Indicators Observed**:
1. **Error Rate**: Increased from **[INSERT]%** to **[INSERT]%**
2. **Response Time**: p(95) jumped from **[INSERT]ms** to **[INSERT]ms**
3. **Error Types**: [INSERT: 500 errors, timeouts, connection refused, etc.]

**Evidence Table**:

| VU Count | Error Rate | p95 Time | Status |
|----------|------------|----------|--------|
| [INSERT] | [INSERT]% | [INSERT]ms | ✓ Stable |
| [INSERT] | [INSERT]% | [INSERT]ms | ⚠ Warning |
| [INSERT] | [INSERT]% | [INSERT]ms | ✗ Failing |

**Supporting Screenshot**: [INSERT]

**Root Cause Analysis**:
- [INSERT: Database bottleneck?]
- [INSERT: CPU/Memory exhaustion?]
- [INSERT: Network saturation?]

---

### Question 3: Memory Leaks / Residual Errors (Soak Test)

**Answer**: [INSERT: Yes/No - memory leak detected]

**Analysis**:

**Response Time Trend**:
```
Start (0 min):    avg=[INSERT]ms, p(95)=[INSERT]ms
Middle (30 min):  avg=[INSERT]ms, p(95)=[INSERT]ms
End (56 min):     avg=[INSERT]ms, p(95)=[INSERT]ms

Trend: [INSERT: Stable / Increasing / Decreasing]
```

**Residual Errors After Ramp-Down**:
- VUs reduced to 0
- Error rate: **[INSERT]%**
- Interpretation: [INSERT: Normal / Abnormal]

**Memory Leak Indicators**:
- [ ] Response time increases steadily over time
- [ ] Error rate rises without load increase
- [ ] Errors persist after load removal
- [ ] Resource exhaustion messages in logs

**Conclusion**:
[INSERT: No memory leak detected - system stable over 56 minutes]
OR
[INSERT: Potential memory leak - response time increased by X% over test duration]

**Supporting Screenshot**: [INSERT]

---

### Question 4: Recovery Time (Spike Test)

**Answer**: The system took approximately **[INSERT TIME]** seconds to fully recover after the spike.

**Recovery Timeline**:

| Timestamp | Event | p95 Time | Error Rate |
|-----------|-------|----------|------------|
| T+0s | Normal load (10 VUs) | [INSERT]ms | [INSERT]% |
| T+60s | SPIKE START (→200 VUs) | [INSERT]ms | [INSERT]% |
| T+90s | During spike | [INSERT]ms | [INSERT]% |
| T+240s | SPIKE END (→10 VUs) | [INSERT]ms | [INSERT]% |
| T+270s | Recovery +30s | [INSERT]ms | [INSERT]% |
| T+300s | Recovery +60s | [INSERT]ms | [INSERT]% |
| T+[INSERT]s | **FULLY RECOVERED** | [INSERT]ms | [INSERT]% |

**Recovery Criteria Met**:
- ✓ p(95) < 1000ms
- ✓ Error rate < 1%
- ✓ Response time = baseline ± 10%

**Supporting Screenshot**: [INSERT]

**Observations**:
- [INSERT: Did the system auto-scale?]
- [INSERT: Were there cascading failures?]
- [INSERT: How gracefully did it handle the spike?]

---

## 4. Conclusions & Recommendations

### 4.1 Key Findings

1. **Capacity**: System handles **[INSERT]** concurrent users with acceptable performance
2. **Breaking Point**: Failures begin at **[INSERT]** VUs
3. **Stability**: [INSERT: Stable/Unstable] over extended periods
4. **Resilience**: Recovers in **[INSERT]** seconds after traffic spike

### 4.2 Bottlenecks Identified

- [INSERT: Database queries?]
- [INSERT: CPU/Memory?]
- [INSERT: Network I/O?]
- [INSERT: Third-party API calls?]

### 4.3 Recommendations

1. **Immediate Actions**:
   - [INSERT: e.g., Add database indexes]
   - [INSERT: e.g., Enable caching]

2. **Short-term Improvements**:
   - [INSERT: e.g., Implement connection pooling]
   - [INSERT: e.g., Optimize slow queries]

3. **Long-term Scaling**:
   - [INSERT: e.g., Horizontal scaling with load balancer]
   - [INSERT: e.g., CDN for static assets]

---

## 5. Appendix

### 5.1 Raw Test Data
[INSERT: Links to JSON/CSV exports]

### 5.2 Screenshots
[INSERT: All terminal outputs]

### 5.3 Test Scripts
- Location: `/k6-tests/`
- Configuration: `config.js`

---

**Report Compiled By**: [INSERT NAME]  
**Date**: [INSERT DATE]  
**Version**: 1.0
