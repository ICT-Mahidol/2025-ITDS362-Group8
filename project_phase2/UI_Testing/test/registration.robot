*** Settings ***
Library           SeleniumLibrary
Library           String
Suite Setup       Open Browser To Login Page
Suite Teardown    Close Browser
Test Setup        Go To Login Page
Test Teardown     Capture Page Screenshot
Resource          ../locators/Register.locator.robot
*** Variables ***
${WEBSITE}                    http://10.34.112.158:8000/dk
${BROWSER}                    Chrome
${Account_Button}             css:[data-testid="nav-account-link"]
${SignUp_Button}              css:[data-testid="register-button"]
${ValidFirstname}
${ValidLastname}
${ValidEmail}
${ValidPhone}
${ValidPassword}
${InvalidEmail}              test@mahidol.com
*** Test Cases ***
Valid Registration Page
    Create Random User
    Click Element                     ${Account_Button}
    Wait Until Element Is Visible     ${SignUp_Button}           30s
    Click Element                     ${SignUp_Button}
    #Verify Element on Register Page
    Page Should Contain               Become a Medusa Store Member
    Wait Until Element Is Visible     ${LOC_REGISTER.first_name}    30s
    Wait Until Element Is Visible     ${LOC_REGISTER.last_name}     30s
    Wait Until Element Is Visible     ${LOC_REGISTER.email}         30s
    Wait Until Element Is Visible     ${LOC_REGISTER.phone}         30s
    Wait Until Element Is Visible     ${LOC_REGISTER.password}      30s
    #Input Valid Data
    Input Text                        ${LOC_REGISTER.first_name}    ${ValidFirstname}
    Input Text                        ${LOC_REGISTER.last_name}     ${ValidLastname}
    Input Text                        ${LOC_REGISTER.email}         ${ValidEmail}
    Input Text                        ${LOC_REGISTER.phone}         ${ValidPhone}
    Input Text                        ${LOC_REGISTER.password}      ${ValidPassword}
    Sleep                             2s
    Click Element                     ${LOC_REGISTER.submit}
    Wait Until Page Contains          Hello ${ValidFirstname}    30s
*** Keywords ***
Open Browser To Login Page
    Open Browser    ${WEBSITE}    ${BROWSER}
    Maximize Browser Window

Go To Login Page
    Go To    ${WEBSITE}

Close Browser
    Close All Browsers

Create Random User
    ${ValidFirstname}=    Generate Random String    6    [LETTERS]
    ${ValidLastname}=     Generate Random String    8    [LETTERS]
    ${RANDOM_NUM}=        Generate Random String    3    [NUMBERS]
    ${ValidEmail}=        Set Variable    ${ValidFirstname}.${ValidLastname}${RANDOM_NUM}@mahidol.com
    ${ValidPhone}=        Generate Random String    10   [NUMBERS]
    ${ValidPassword}=     Generate Random String    10   [LETTERS]

    # ให้ตัวแปรใช้ได้ใน test นี้
    Set Test Variable    ${ValidFirstname}
    Set Test Variable    ${ValidLastname}
    Set Test Variable    ${ValidEmail}
    Set Test Variable    ${ValidPhone}
    Set Test Variable    ${ValidPassword}
