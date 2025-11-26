*** Variables ***
&{LOC_Promotion}
...    PageTitle=xpath=//button//p[normalize-space(.)="ITDS362 Store"]
...    PromotionNav=xpath=//*[normalize-space(.)="Promotions"]
...    AddPromotion=xpath=//a[normalize-space(.)="Create"]
...    ShippingDiscountBtn=xpath=//button[@role="radio" and @value="shipping_discount"]
...    ContinueButton=xpath=(//button[normalize-space(.)="Continue"])[last()]
...    PromotionMethod=xpath=//button[@role="radio" and .//label[normalize-space(.)="Promotion code"]]
...    Status=xpath=//button[@role="radio" and @value="active"]
...    CodeInput=xpath=//input[@placeholder="SUMMER15"]
...    Campaign=xpath=//button[@role="radio" and .//label[normalize-space(.)="Without Campaign"]]
...    SaveButton=xpath=(//button[normalize-space(.)="Save"])[last()]