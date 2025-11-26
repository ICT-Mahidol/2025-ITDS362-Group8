# Automation UI Testing – Robot Framework + Selenium

## Folder Structure
- `test/`       – Robot Framework test scripts  
- `locators/`   – element locator variables  
- `resources/`  – assets used in tests (เช่นรูปภาพสำหรับอัปโหลด)  
- `results/`    – test output, logs, report และ demo video  

---

## Test Case 1: Add New Product

### Pre-condition
- ระบบมี admin account ที่สามารถล็อกอินได้  
  - Email: `group8@mu-store.local`  

### Test Scenario
1. ล็อกอินเข้า Admin Panel ด้วย admin account
2. ไปที่เมนู **Product Management**
3. กดปุ่ม **Add Product**
4. กรอกข้อมูลสินค้า:
   - Title, Subtitle, Handle, Description  
   - ข้อมูลมีการสุ่ม suffix เพื่อไม่ให้ซ้ำกับสินค้าที่มีอยู่แล้ว
5. อัปโหลดรูปภาพสินค้าในหน้า Create Product
6. กด **Continue** ไปหน้า General Info
7. กรอกข้อมูล General Info:
   - Type, Collection, Categories, Shipping Profile
8. กด **Continue** ไปหน้า Pricing / Inventory
9. กรอกข้อมูลราคา (เช่น EUR, USD) และตั้งค่า Inventory ตามที่กำหนด
10. กด **Publish** เพื่อเผยแพร่สินค้า
11. จากหน้า Product detail กดลิงก์ **Add media**
12. อัปโหลดรูปภาพเพิ่มเติมในหน้า Media
13. กด **Save** เพื่อบันทึก media

### Expected Result
- สินค้าใหม่ถูกสร้างสำเร็จ และไม่มี error ระหว่างขั้นตอนการกรอกข้อมูลและ publish

## Script Testing Video
> ดูตัวอย่างการรันทดสอบ Test Case: **Add new Product**
[▶️ Click to watch demo video](./results/product/AddProductUITesting.mp4)

---
## Test Case 2: Create Free Shipping Promotion Code

### Pre-condition
- ระบบมี admin account ที่สามารถล็อกอินได้  
  - Email: `group8@mu-store.local`  

### Test Scenario
1. ล็อกอินเข้า Admin Panel ด้วย admin account
2. ไปที่เมนู **Promotions** ผ่านเมนูด้านข้าง (Promotion Navigation)
3. กดปุ่ม **Add Promotion**
4. เลือกประเภทโปรโมชันเป็น **Shipping Discount**
5. กดปุ่ม **Continue** เพื่อไปหน้าตั้งค่าโปรโมชัน
6. บนหน้าโปรโมชัน:
   - เลือก **Promotion Method** สำหรับการลดค่าขนส่ง
   - ตั้งค่า **Status** ให้เป็นสถานะเปิดใช้งาน (Active)
7. ระบบสร้างรหัสโปรโมชันแบบสุ่ม โดยมีรูปแบบ `FREESHIP-XXXX` (ต่อท้ายด้วยอักขระสุ่ม 4 ตัว)
8. กรอกรหัสโปรโมชันที่สร้างขึ้นในช่อง **Code**
9. กดปุ่ม **Continue**
10. เลือก/กำหนด **Campaign** ที่ต้องการผูกกับโปรโมชัน
11. กดปุ่ม **Save** เพื่อบันทึกโปรโมชัน
12. ตรวจสอบที่หน้า Promotions ว่ามีการแสดงรหัสโปรโมชันที่สร้างขึ้น (`FREESHIP-XXXX`) อยู่ในรายการ

### Expected Result
- โปรโมชันแบบ **Free Shipping** ถูกสร้างสำเร็จโดยใช้รหัสรูปแบบ `FREESHIP-XXXX`
- โปรโมชันมีสถานะเป็น Active (พร้อมใช้งาน)
- รหัสโปรโมชันที่สร้างใหม่แสดงอยู่ในรายการ Promotions และไม่มี error ระหว่างขั้นตอนการสร้าง

## Script Testing Video
> ดูตัวอย่างการรันทดสอบ Test Case: **Create Free Shipping Promotion Code**  
[▶️ Click to watch demo video](./results/promotions/PromotionUITesting.mp4)
---
## Test Case 3: Valid User Registration

### Pre-condition
- ระบบ Medusa Store frontend พร้อมใช้งานที่ URL: `http://10.34.112.158:8000/dk`
- ระบบรองรับการสมัครสมาชิกใหม่ด้วยอีเมลที่ไม่เคยลงทะเบียนมาก่อน

### Test Scenario
1. เปิดหน้าเว็บหลักของ Medusa Store
2. คลิกปุ่ม **Account** จากแถบนำทางด้านบน (`nav-account-link`)
3. รอจนปุ่ม **Sign up** แสดง จากนั้นคลิกปุ่ม **Sign up**
4. ตรวจสอบว่าอยู่บนหน้า Register และมีข้อความบนหน้าว่า  
   - `Become a Medusa Store Member`
5. ตรวจสอบว่าฟิลด์ต่อไปนี้แสดงบนหน้า Register ครบถ้วน:
   - First name  
   - Last name  
   - Email  
   - Phone  
   - Password  
6. สร้างข้อมูลผู้ใช้ใหม่แบบสุ่ม (Random user) ด้วยคีย์เวิร์ด `Create Random User`:
   - สร้าง `ValidFirstname` เป็นสตริงตัวอักษร 6 ตัว
   - สร้าง `ValidLastname` เป็นสตริงตัวอักษร 8 ตัว
   - สร้างเลขสุ่ม 3 หลัก (`RANDOM_NUM`)
   - ประกอบเป็นอีเมลในรูปแบบ:  
     `Firstname.LastnameRANDOM_NUM@mahidol.com`
   - สร้างเบอร์โทรศัพท์ 10 หลักแบบสุ่ม
   - สร้างรหัสผ่าน 10 ตัวอักษรแบบสุ่ม
7. กรอกข้อมูลลงในฟอร์ม Register:
   - First name = `${ValidFirstname}`
   - Last name = `${ValidLastname}`
   - Email = `${ValidEmail}`
   - Phone = `${ValidPhone}`
   - Password = `${ValidPassword}`
8. คลิกปุ่ม **Submit / Register**
9. รอให้หน้าเว็บตอบกลับ และตรวจสอบว่ามีข้อความแสดงผลว่า  
   - `Hello ${ValidFirstname}`  
   (แสดงว่าระบบล็อกอินด้วยผู้ใช้ใหม่ที่เพิ่งสมัครสำเร็จ)

### Expected Result
- ระบบสามารถแสดงหน้า Register พร้อมฟิลด์ First name, Last name, Email, Phone, Password ได้ครบถ้วน
- ผู้ใช้ใหม่ที่สร้างจากข้อมูลสุ่มสามารถสมัครสมาชิกได้สำเร็จโดยไม่มี error
- หลังจากกดสมัครสมาชิก ระบบล็อกอินให้อัตโนมัติ และแสดงข้อความต้อนรับในรูปแบบ  
  - `Hello ${ValidFirstname}`
- ไม่มีข้อความแจ้งเตือนว่าอีเมลซ้ำ หรือฟิลด์ไม่ถูกต้อง

## Script Testing Video
> ดูตัวอย่างการรันทดสอบ Test Case: **Valid Registration Page**  
[▶️ Click to watch demo video](./results/register/RegisterUITesting.mp4)
---
