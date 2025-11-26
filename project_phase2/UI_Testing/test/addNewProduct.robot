*** Settings ***
Library           SeleniumLibrary
Library           String
Library           OperatingSystem
Suite Setup       Open Browser To Login Page
Suite Teardown    Close Browser
Test Setup        Login to Admin panel
Test Teardown     Capture Page Screenshot
Resource          ../locators/Product.locator.robot

*** Variables ***
${WEBSITE}        http://10.34.112.158:9000/app/login
${BROWSER}        Chrome
${email}          group8@mu-store.local
${password}       Bw3$kxN6


*** Test Cases ***
Add new Product to Product Management
    Wait Until Element Is Visible    ${LOC_AdminPanel.PageTitle}          30s
    Wait Until Element Is Visible    ${LOC_AdminPanel.ProductNameBar}     30s
    Click Element                    ${LOC_AdminPanel.ProductNameBar}
    Wait Until Element Is Visible    ${LOC_AdminPanel.AddProductButton}   30s
    Click Element                    ${LOC_AdminPanel.AddProductButton}

    Wait Until Element Is Visible    ${LOC_AdminPanel.ProductTitle}       30s

    # ----- สุ่มข้อมูล Product -----
    ${PRODUCT_TITLE}        ${PRODUCT_SUBTITLE}    ${PRODUCT_HANDLE}    ${PRODUCT_DESC}=    Generate Random Product Data

    # ใช้ Press Keys ตามที่ขอ (ไม่เปลี่ยนเป็น Input Text)
    Press Keys    ${LOC_AdminPanel.ProductTitle}         ${PRODUCT_TITLE}
    Press Keys    ${LOC_AdminPanel.ProductSubTitle}      ${PRODUCT_SUBTITLE}
    Press Keys    ${LOC_AdminPanel.ProductHandle}        ${PRODUCT_HANDLE}
    Press Keys    ${LOC_AdminPanel.ProductDescription}   ${PRODUCT_DESC}

    # ----- อัปโหลดรูปตอนสร้างสินค้า -----
    ${IMAGE_PATH}=    Normalize Path    ${CURDIR}/../resources/mahidol-sweater.jpg
    ${file_input}=    Get WebElement    ${LOC_AdminPanel.ProductImage}

    Execute Javascript
    ...    arguments[0].removeAttribute('hidden');
    ...    arguments[0].style.display='block';
    ...    arguments[0].style.visibility='visible';
    ...    arguments[0].disabled=false;
    ...    return;
    ...    ARGUMENTS    ${file_input}

    Sleep    0.5s

    Choose File    ${LOC_AdminPanel.ProductImage}    ${IMAGE_PATH}

    ${count}=    Execute Javascript    return arguments[0].files.length;    ARGUMENTS    ${file_input}
    Should Be Equal As Integers    ${count}    1

    Sleep    2s

    Click Element    ${LOC_AdminPanel.ContinueButton}

    # ----- General Info: Type / Collection / Categories / Shipping profile -----
    Wait Until Element Is Visible    ${LOC_AdminPanel.DiscountCheckbox}        30s
    Wait Until Element Is Visible    ${LOC_AdminPanel.Type}                    30s
    Input Text                       ${LOC_AdminPanel.Type}                    Test Type

    Wait Until Element Is Visible    ${LOC_AdminPanel.Collection}              30s
    Click Element                    ${LOC_AdminPanel.Collection}
    Wait Until Element Is Visible    ${LOC_AdminPanel.Collection_Jacket}       10s
    Click Element                    ${LOC_AdminPanel.Collection_Jacket}

    Wait Until Element Is Visible    ${LOC_AdminPanel.Categories}              30s
    Input Text                       ${LOC_AdminPanel.Categories}              Test Category

    Wait Until Element Is Visible    ${LOC_AdminPanel.Tags}                    30s

    Scroll Element Into View         ${LOC_AdminPanel.ShippingProfileInput}
    Wait Until Element Is Visible    ${LOC_AdminPanel.ShippingProfileInput}    30s
    Click Element                    ${LOC_AdminPanel.ShippingProfileInput}
    Press Keys                       ${LOC_AdminPanel.ShippingProfileInput}    ARROW_DOWN
    Click Element                    ${LOC_AdminPanel.ShippingProfileOption}

    Click Element                    ${LOC_AdminPanel.ContinueButton}

    # ----- Pricing / Inventory -----
    Wait Until Element Is Visible    ${LOC_AdminPanel.InventoryCheckbox}    30s
    Press Key                        ${LOC_AdminPanel.PriceEUR}            49.99
    Press Key                        ${LOC_AdminPanel.PriceUSD}            59.99

    Click Element                    ${LOC_AdminPanel.PublishButtonByData}

    Sleep    30s

    # ----- Add media หลัง publish แล้ว -----
    Wait Until Element Is Visible    ${LOC_AdminPanel.AddMediaLink}        30s
    Click Element                    ${LOC_AdminPanel.AddMediaLink}

    Wait Until Element Is Visible    ${LOC_AdminPanel.MediaSectionTitle}   30s

    ${IMAGE_PATH}=    Normalize Path    ${CURDIR}/../resources/mahidol-sweater.jpg
    ${file_input}=    Get WebElement    ${LOC_AdminPanel.MediaUploadInput}

    Execute Javascript
    ...    arguments[0].removeAttribute('hidden');
    ...    arguments[0].style.display='block';
    ...    arguments[0].style.visibility='visible';
    ...    arguments[0].disabled=false;
    ...    return;
    ...    ARGUMENTS    ${file_input}

    Sleep    0.5s

    Choose File    ${LOC_AdminPanel.MediaUploadInput}    ${IMAGE_PATH}

    ${count}=    Execute Javascript    return arguments[0].files.length;    ARGUMENTS    ${file_input}
    Should Be Equal As Integers    ${count}    1

    Sleep    1s

    Wait Until Element Is Visible    ${LOC_AdminPanel.MediaSaveButton}    30s
    ${save_btn}=    Get WebElement    ${LOC_AdminPanel.MediaSaveButton}
    Execute Javascript    arguments[0].click();    ARGUMENTS    ${save_btn}

    Sleep    3s


*** Keywords ***
Open Browser To Login Page
    Open Browser    ${WEBSITE}    ${BROWSER}
    Maximize Browser Window

Login to Admin panel
    Go To           ${WEBSITE}
    Input Text      ${LOC_AdminLogin.email}       ${email}
    Input Text      ${LOC_AdminLogin.password}    ${password}
    Click Element   ${LOC_AdminLogin.loginButton}

Generate Random Product Data
    ${suffix}=       Generate Random String    4    [NUMBERS]

    ${PRODUCT_TITLE}=       Set Variable    Test Product Title ${suffix}
    ${PRODUCT_SUBTITLE}=    Set Variable    Test Product Subtitle ${suffix}
    ${PRODUCT_HANDLE}=      Set Variable    test-product-handle-${suffix}
    ${PRODUCT_DESC}=        Set Variable    This is a test product description ${suffix}.

    Log To Console    Generated product data: ${PRODUCT_TITLE} | ${PRODUCT_HANDLE}
    [Return]    ${PRODUCT_TITLE}    ${PRODUCT_SUBTITLE}    ${PRODUCT_HANDLE}    ${PRODUCT_DESC}
