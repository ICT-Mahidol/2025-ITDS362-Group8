*** Variables ***
&{LOC_AdminLogin}
...    email=css:input[name="email"]
...    password=css:input[name="password"]
...    loginButton=xpath=//*[normalize-space(.)="Continue with Email"]

&{LOC_AdminPanel}
# --- หน้า Dashboard / Products ---
...    PageTitle=xpath=//button//p[normalize-space(.)="ITDS362 Store"]
...    ProductNameBar=xpath=//*[normalize-space(.)="Products"]
...    AddProductButton=xpath=//a[normalize-space(.)="Create"]

# --- หน้า General (Title / Subtitle / Handle / Description / Media) ---
...    ProductTitle=xpath=//label[normalize-space(.)="Title"]/following::input[1]
...    ProductSubTitle=xpath=//label[normalize-space(.)="Subtitle"]/following::input[1]
...    ProductHandle=xpath=//label[normalize-space(.)="Handle"]/following::input[1]
...    ProductDescription=xpath=//label[normalize-space(.)="Description"]/following::textarea[1]
...    ProductImage=xpath=//p[normalize-space(.)="Upload images"]/ancestor::button/following-sibling::input[@type="file"]

# ปุ่ม Continue (ใช้ตัวล่างสุดในหน้า เผื่อมีหลายปุ่ม)
...    ContinueButton=xpath=(//button[normalize-space(.)="Continue"])[last()]

# --- หน้า Organize ---
# สวิตช์ Discountable
...    DiscountCheckbox=xpath=//label[normalize-space(.)="Discountable"]/preceding::button[@role="switch"][1]

# Type (combobox)
...    Type=xpath=//label[normalize-space(.)="Type"]/ancestor::div[contains(@class,"flex flex-col space-y-2")][1]//input[@name="type_id"]

# Collection (combobox + option "jacket")
...    Collection=xpath=//label[normalize-space(.)="Collection"]/ancestor::div[contains(@class,"flex flex-col space-y-2")][1]//input[@name="collection_id"]
...    Collection_Jacket=xpath=//div[@role="option"]//span[normalize-space(.)="jacket"]

# Categories (plain input)
...    Categories=xpath=//label[normalize-space(.)="Categories"]/ancestor::div[contains(@class,"flex flex-col space-y-2")][1]//input[@name="categories"]

# Tags (combobox-like input)
...    Tags=xpath=//label[normalize-space(.)="Tags"]/ancestor::div[contains(@class,"flex flex-col space-y-2")][1]//input[@name="tags"]

# Shipping profile
...    ShippingProfileButton=xpath=//label[normalize-space(.)="Shipping profile"]/ancestor::div[contains(@class,"grid")][1]//button[@aria-haspopup="listbox"]
...    ShippingProfileOption=xpath=//div[@role="listbox"]//span[normalize-space(.)="Default Shipping Profile"]
...    ShippingProfileInput=xpath=//input[@name="shipping_profile_id"]
    
...    InventoryCheckbox=xpath=//button[@role="checkbox" and @data-field="variants.0.manage_inventory"]
...    PriceEUR=xpath=//input[@name="variants.0.prices.eur"]
...    PriceUSD=xpath=//input[@name="variants.0.prices.usd"]
...    PriceUSDCell=xpath=//div[@data-container-id="0:7"]
...    PublishButtonByData=xpath=//button[@data-name="publish-button"]
...    AddMediaLink=xpath=//a[normalize-space(.)="Add media"]
...    MediaUploadInput=xpath=//p[normalize-space(.)="Upload images"]/ancestor::button/following-sibling::input[@type="file"]
...    MediaSaveButton=xpath=//button[normalize-space(.)="Save"]
...    MediaSectionTitle=xpath=//label[normalize-space(.)="Media"]