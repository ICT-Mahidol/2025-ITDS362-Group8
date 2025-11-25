# Performance Testing Report (Group 8)

## Overview
การทดสอบประสิทธิภาพระบบ (Performance Testing) ทำการทดสอบผ่านเครื่องมือ **K6** โดยมุ่งเน้นการทดสอบที่ฝั่ง Storefront (`http://10.34.112.158:8000/dk`) เพื่อจำลองพฤติกรรมผู้ใช้งานจริง โดยมีเป้าหมายเพื่อวัดขีดความสามารถสูงสุด (Capacity), จุดที่ระบบล้มเหลว (Breaking Point), และความเสถียรของระบบ (Stability)

---

## 1. Test Results & Metrics
ผลลัพธ์จากการรัน K6 ในแต่ละรูปแบบการทดสอบ

### 1.1 Average-load Test
*(การจำลองโหลดผู้ใช้ปกติ เพื่อหา Baseline Performance)*
* **duration :** 3m 22.9s
* **iterations :** 52
* **vus :** 1 (min) - 15 (max)
* **max (response time) :** 19.09s
* **data_received :** 37 MB
* **data_sent :** 39 kB
* **http_req_duration :** avg=4.00s, **p(95)=7.66s**
* **http_req_failed :** 19.40%
* **http_reqs :** 268

### 1.2 Stress Test
*(การเพิ่มจำนวนผู้ใช้ไปเรื่อยๆ จนถึง 40 VUs เพื่อหาจุดพัง)*
* **duration :** 5m 11.4s
* **iterations :** 218
* **vus :** 1 (min) - 40 (max)
* **max (response time) :** 15.94s
* **data_received :** 152 MB
* **data_sent :** 156 kB
* **http_req_duration :** avg=3.42s, **p(95)=8.72s**
* **http_req_failed :** 19.71%
* **http_reqs :** 1,106

### 1.3 Soak Test
*(การรันโหลดต่อเนื่อง 13 นาที เพื่อดูความเสถียรและ Memory Leak)*
* **duration :** 13m 06.3s
* **iterations :** 143
* **vus :** 1 (min) - 10 (max)
* **max (response time) :** 59.16s
* **data_received :** 95 MB
* **data_sent :** 103 kB
* **http_req_duration :** avg=5.21s, **p(95)=31.43s**
* **http_req_failed :** 21.81%
* **http_reqs :** 715

### 1.4 Spike Test
*(การกระชากโหลดจาก 5 ไป 25 VUs ทันที เพื่อดูการฟื้นตัว)*
* **duration :** 4m 02.0s
* **iterations :** 11
* **vus :** 1 (min) - 25 (max)
* **max (response time) :** 1m 0s (60.0s)
* **data_received :** 10 MB
* **data_sent :** 22 kB
* **http_req_duration :** avg=23.09s, **p(95)=53.90s**
* **http_req_failed :** 32.47%
* **http_reqs :** 117

---

## 2. Analysis & Answers to Assignment Questions

จากการวิเคราะห์ K6 Metrics (`http_req_failed`, `http_req_duration`, `vus_max`) สามารถสรุปผลได้ดังนี้:

### Q1: ภาพรวม: ระบบสามารถรองรับผู้ใช้ได้สูงสุดกี่คนก่อนที่ค่า response time จะเกิน 1 วินาที?
**คำตอบ: รองรับได้ไม่เกิน 1 - 3 Concurrent Users (VUs)**

**วิเคราะห์:**
* จากผล **Average-load Test** ที่จำลองผู้ใช้เพียงสูงสุด 15 คน พบว่าค่าเฉลี่ย Response Time (`avg`) สูงถึง **4.00 วินาที** และค่า `p(95)` สูงถึง **7.66 วินาที** ซึ่งเกินกว่าเกณฑ์ 1 วินาทีไปมาก
* ข้อมูลบ่งชี้ว่าระบบเริ่มทำงานช้าลงทันทีที่มีผู้ใช้งานพร้อมกันเพียงไม่กี่คน (Low Concurrency) และไม่สามารถรักษา Performance ให้ต่ำกว่า 1 วินาทีได้ในการใช้งานปกติ

### Q2: ในการทำ Stress Test: จุดที่ระบบเริ่มแสดงปัญหาคือที่จำนวน virtual users (VUs) เท่าใด?
**คำตอบ: ระบบเริ่มล่ม (Fail) ที่จำนวนประมาณ 10 Virtual Users (VUs)**

**วิเคราะห์:**
* แม้ในการทดสอบ **Average-load** (Max 15 VUs) ก็พบ Error Rate สูงถึง **19.40%** ซึ่งใกล้เคียงกับ **Stress Test** (Max 40 VUs) ที่มี Error Rate **19.71%**
* สิ่งนี้ยืนยันว่าจุดแตกหัก (Breaking Point) ของระบบอยู่ต่ำมาก คือช่วงประมาณ **10-15 VUs** ระบบก็เริ่มตอบสนองไม่ทัน (Timeout) และเกิด Error อย่างมีนัยสำคัญแล้ว ไม่จำเป็นต้องรอถึง 40 VUs

### Q3: ในการทำ Soak Test: มี memory leak หรือปัญหาคงค้าง (residual errors) เกิดขึ้นหลังจากโหลดต่อเนื่องนาน ๆ หรือไม่?
**คำตอบ: มีแนวโน้มปัญหา Resource Exhaustion (ทรัพยากรหมด) และ Connection Timeout อย่างรุนแรง**

**วิเคราะห์:**
* **Response Time Degradation:** เมื่อเทียบระยะเวลาสั้น (Average/Stress) กับระยะเวลายาว (Soak 13 นาที) พบว่าค่า `p(95)` ใน Soak Test พุ่งสูงขึ้นไปถึง **31.43 วินาที** (สูงกว่า Average load เกือบ 4 เท่า)
* **Error Accumulation:** พบ Error `wsarecv: An existing connection was forcibly closed` จำนวนมาก แสดงให้เห็นว่าระบบไม่สามารถคืน Resource หรือจัดการ Connection ที่ค้างอยู่ได้ทันเมื่อมีการใช้งานต่อเนื่อง ส่งผลให้ประสิทธิภาพแย่ลงเรื่อยๆ ตามระยะเวลา

### Q4: ใน Spike Test: ระบบสามารถฟื้นตัวกลับมาให้บริการตามปกติหลังจาก spike ได้เร็วแค่ไหน? (Recovery Time)
**คำตอบ: ระบบไม่สามารถฟื้นตัวได้ (Recovery Failed) ภายในระยะเวลาการทดสอบ**

**วิเคราะห์:**
* **During Spike:** เมื่อ User เพิ่มจาก 5 เป็น 25 คน ทันที ระบบล่มโดยสมบูรณ์ (Fail 32.47%) และค่า Latency พุ่งชนเพดาน Timeout (60s)
* **No Recovery:** แม้ช่วงท้ายของการทดสอบจะลดจำนวน User ลง ระบบก็ยังคงทำงานล่าช้ามาก (`avg` ~23s) จนจบการทดสอบ แสดงว่าระบบเกิดอาการ "Crashed" หรือ "Hung" และต้องใช้เวลานานมากในการเคลียร์ Request ที่ค้างอยู่

---

## 3. Conclusion & Recommendations
ระบบ Medusa Store บน Environment ปัจจุบัน **ไม่ผ่านเกณฑ์มาตรฐาน** สำหรับการใช้งานจริง
1.  **Capacity ต่ำมาก:** รองรับผู้ใช้พร้อมกันได้ไม่ถึง 5 คน
2.  **Latency สูง:** Response time เฉลี่ย 3-5 วินาที แม้โหลดต่ำ
3.  **Unstable:** เกิด Error Rate ~20% ในทุกการทดสอบ

**ข้อเสนอแนะ:** จำเป็นต้องตรวจสอบ Infrastructure (CPU/RAM จำกัดเกินไปหรือไม่), การจูน Database Indexing, และตรวจสอบ Code ฝั่ง Backend ว่ามีการ Block I/O หรือไม่