*** Settings ***
Library           SeleniumLibrary
Library           String
Library           OperatingSystem
Suite Setup       Open Browser To Login Page
Suite Teardown    Close Browser
Test Setup        Login to Admin panel
Test Teardown     Capture Page Screenshot
Resource          ../locators/Promotion.locator.robot
Resource          ../locators/Product.locator.robot

*** Variables ***
${WEBSITE}        http://10.34.112.158:9000/app/login
${BROWSER}        Chrome
${email}          group8@mu-store.local
${password}       Bw3$kxN6


*** Test Cases ***
Create Free shipping Promotion Code
    [Documentation]    Verify that admin can create a Free Shipping promotion code successfully.
    [Tags]             ui    promotion    free_shipping

    Wait Until Element Is Visible    ${LOC_Promotion.PageTitle}           30s

    Wait And Click                   ${LOC_Promotion.PromotionNav}
    Wait And Click                   ${LOC_Promotion.AddPromotion}

    Wait And Click                   ${LOC_Promotion.ShippingDiscountBtn}
    Wait And Click                   ${LOC_Promotion.ContinueButton}

    Wait Until Element Is Visible    ${LOC_Promotion.PromotionMethod}     30s
    Click Element                    ${LOC_Promotion.PromotionMethod}
    Click Element                    ${LOC_Promotion.Status}

    ${PROMO_CODE}=                   Generate Promotion Code
    Input Text                       ${LOC_Promotion.CodeInput}    ${PROMO_CODE}

    Wait And Click                   ${LOC_Promotion.ContinueButton}
    Wait And Click                   ${LOC_Promotion.Campaign}
    Wait And Click                   ${LOC_Promotion.SaveButton}

    Wait Until Page Contains         ${PROMO_CODE}                 30s


*** Keywords ***
Open Browser To Login Page
    Open Browser    ${WEBSITE}    ${BROWSER}
    Maximize Browser Window

Login to Admin panel
    Go To           ${WEBSITE}
    Input Text      ${LOC_AdminLogin.email}       ${email}
    Input Text      ${LOC_AdminLogin.password}    ${password}
    Click Element   ${LOC_AdminLogin.loginButton}

Generate Promotion Code
    ${prefix}=       Set Variable           FREESHIP
    ${rand_part}=    Generate Random String    4    [LETTERS][NUMBERS]
    ${promo_code}=   Set Variable           ${prefix}-${rand_part}
    Log To Console   Generated promo code: ${promo_code}
    [Return]         ${promo_code}

Wait And Click
    [Arguments]    ${locator}    ${timeout}=30s
    Wait Until Element Is Visible    ${locator}    ${timeout}
    Click Element                    ${locator}
