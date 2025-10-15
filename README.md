# Web Scraping in Java With ``Jsoup``

## Unit Test Path Folder : ``/src/test/java/org/jsoup/ITDS362UnitTest``
## Test Framework : ``Maven``

## Test Suite 1 : ``StringUtill.Join``
### Interface based
#### 1. Identify testable function
-  ``join(Iterable<?> strings, String separator)``
#### 2. Identify parameters, return types, return values, and exceptional behavior
-   **Parameters** :  ``String (List)``, ``separator (String)``
-	**Return type** : ``String``
-	**Return value** : 
    -   if strings is null return null
    -	if strings is [] return “”
    -	if strings contain characters return strings with separator
-	**Exceptional behavior** :
    -	Strings can join with separator
#### 3. Model the input domain
-   **C1** : List input is not empty
-   **C2** : Separator value is not empty

**Partition characteristics**
| Characteristic | B1 | B2 |
|---|---|---|
| C1 : List input is not empty | True | False |
| C2 : Separator value is not empty | True | False |

**Identify possible values**
| Characteristic | B1 | B2 |
|---|---|---|
| C1 : List input is not empty | [“A”, ”B”] | [] |
| C2 : Separator value is not empty | “-“ | "" |
#### 4. Combine partitions to define test requirements
-   **Assumption** : Each Choice Coverage (ECC)
-   **Test Requirements (TR)** : 2
-   **Selected tests:** : (C1B1,C2B1) , (C1B2,C2B2)
#### 5. Derive test values
| Test | List | Seperater | Expected Result |
|---|---|---|---|
| TC1  | [“A”, ”B”] | "," |A,B|
| TC2  | [] | "" |""|

### Functionality based
#### 1. Identify testable function
- ``join(Iterable<?> strings, String separator)``
#### 2. Identify parameters, return types, return values, and exceptional behavior
-   **Parameters** :  ``String (List)``, ``separator (String)``
-	**Return type** : ``String``
-	**Return value** : 
    -   if strings is null return null
    -	if strings is [] return “”
    -	if strings contain characters return strings with separator
-	**Exceptional behavior** :
    -	Strings can join with separator
#### 3. Model the input domain
-	**C1** : type of List input 
-	**C2** : type  of Delimiter input

**Partition characteristics**
| Characteristic | B1 | B2 | B3 |
|---|---|---|---|
| C1 : type of List input | Null value | Empty List |Lists contain element|
| C2 : type  of Delimiter input | Null value | Empty Delimiter |Normal Delimiter |
**Identify possible values**
| Characteristic | B1 | B2 | B3 |
|---|---|---|---|
| C1 : type of List input | Null  | [] | [“I”,”Love”,”You”] |
| C2 : type  of Delimiter input | Null  |""| "-" |
#### 4. Combine partitions to define test requirements
-   **Assumption** : Each Choice Coverage (ECC)
-	**Test Requirements (TR)** : 3
-   **Test Selected** : (C1B1,C2B1) , (C1B2,C2B2) , (C1B3,C2B3) 
#### 5. Derive test values
| Test | List | Seperater | Expected Result |
|---|---|---|---|
| T1 | [“I”,”Love”,”You”]  | "-" | I-Love-You |
| T2 | []  |""| "" |
| T3 | Null  | Null | Throw NullPointerException |

## Test Suite 2 : ``StringUtill.isBlank``
### Interface based
#### 1. Identify testable function
-  ``isBlank(@Nullable String string) ``
#### 2. Identify parameters, return types, return values, and exceptional behavior
-	**Parameters** : ``String ``
-	**Return type** : ``Boolean``
-	**Return value** : 
    -   if Input parameter is Blank , return true
    -   Otherwise, it return false
-	**Exceptional behavior** : If input parameter input is blank method return true
#### 3. Model the input domain
-	**C1** : String input is  empty

**Partition characteristics**
| Characteristic | B1 | B2 |
|---|---|---|
| C1 : String input is empty | True | False |
**Identify possible values**
| Characteristic | B1 | B2 |
|---|---|---|
| C1 : String input is empty | "" | "Hello world" |

#### 4.	Combine partitions to define test requirements
-	**Assumption** : All Combination Coverage Criteria (ACoC)
-	**Test Requirements** (TR) : Number of tests is 2*1 = 2
-   **Test Selected** : (C1B1) , (C1B2)

#### 5. Derive test values
| Test | String | Expected Result |
|---|---|---|
| TC1  | ""| True |
| TC2  | "Heeeeey" | False |

### Functionality based
#### 1. Identify testable function
-  ``isBlank(@Nullable String string)``
#### 2. Identify parameters, return types, return values, and exceptional behavior
-	**Parameters** : ``String``
-	**Return type** : ``Boolean``
-	**Return value** : 
    -   if Input parameter is Blank , return true
    -   Otherwise, it return false
-	**Exceptional behavior** : If input parameter input is blank method return true
#### 3. Model the input domain
-	**C1** : Blank Scraping (type of input Blank)

**Partition characteristics**
| Characteristic | B1 | B2 | B3 | B4 | B5 | 
|---|---|---|---|---|---|
| C1 : Blank Scraping (type of input string) | Null | Empty | Space only | Escape Character | Regular Character (non-blank)|
**Identify possible values**
| Characteristic | B1 | B2 | B3 | B4 | B5 | 
|---|---|---|---|---|---|
| C1 : Blank Scraping (type of input string) | Null | "" | "  " | "\n\t | "X" |

#### 4.	Combine partitions to define test requirements
-	**Assumption** : All Combination Coverage Criteria (ACoC)
-	**Test Requirements** (TR) : Number of tests is 5*1 = 5
-   **Test Selected** : (C1B1),(C1B2),(C1B3),(C1B4),(C1B5) 
#### 5. Derive test values
| Test | String | Expected Result |
|---|---|---|
| TC1  | Null | True |
| TC2  | "" | True |
| TC3  | " " | True |
| TC4  | "\n\t" | True |
| TC5  | "x" | False |

## Test Suite 3 : ``hasAttr``
### Interace based
#### 1. Identify testable function
-   ``boolean hasAttr(String attributeKey)``    

#### 2. Identify parameters, return types, return values, and exceptional behavior
-   **Parameters**: ``attributeKey`` → the name of the attribute to check (type: String)
-   **Return type**: ``Boolean``
-   **Return values**:
    -   true → if the element contains an attribute named attributeKey
    -   false → if the element does not contain that attribute
-   **Exceptional behavior**: May throw IllegalArgumentException if attributeKey is null or empty string ("")

#### 3. Model the input domain
-   **C1** = Whether attributeKey is null or not
-   **C2** = Whether attributeKey is an empty string ("")
-   **C3** = Whether the element actually contains that attribute key

**Partition Characteristics**
| Characteristic | B1 | B2 | 
|---|---|---|
| C1 : Whether attributeKey is null or not | True | False | 
| C2 : Whether attributeKey is an empty string ("") | True | False | 
| C3 : Whether the element actually contains that attribute key | True | False | 

**Identify possible values**
| Characteristic | B1 | B2 | 
|---|---|---|
| C1 : Whether attributeKey is null or not | el.hasAttr(null) → throws IllegalArgumentException | el.hasAttr("id")| 
| C2 : Whether attributeKey is an empty string ("") | el.hasAttr("") → throws exception| el.hasAttr("class")| 
| C3 : Whether the element actually contains that attribute key | el.hasAttr("id") when <divid="main"> → true | el.hasAttr("id") when <div> → false| 

#### 4. Combine partitions to define test requirements
-   **Assumption**: Base Choice Coverage (BCC)
-   **Test requirements**: ∑(Bi−1) = (2−1) + (2−1) + (2−1) = 3 test requirements
-   **Selected tests**:
    -   Base test: (C1b2, C2b2, C3b2)
    -   Variations: (C1b1, C2b2, C3b2), (C1b2, C2b1, C3b2), (C1b2, C2b2, C3b1)

#### 5. Derive test values
| Test Case | Test Value | Expected Result | 
|---|---|---|
| T1 | "id", <div id="main">  | true | 
| T2 | "class", <div id="main"> | False | 
| T3 | null, <div> | IllegalArgumentException | 
| T4 | "", <div> | IllegalArgumentException | 

### Functionality based
#### 1. Identify testable function
-   ``boolean hasAttr(String attributeKey)``    

#### 2. Identify parameters, return types, return values, and exceptional behavior
-   **Parameters**: ``attributeKey`` → the name of the attribute to check (type: String)
-   **Return type**: ``Boolean``
-   **Return values**:
    -   true → if the element contains an attribute named attributeKey
    -   false → if the element does not contain that attribute
-   **Exceptional behavior**: May throw IllegalArgumentException if attributeKey is null or empty string ("")

#### 3. Model the input domain
-   **C1** = attributeKey is null
-   **C2** = attributeKey is empty string
-   **C3** = attributes object is null
-   **C4** = attributeKey exists in attributes

**Partition Characteristics**
| Characteristic | B1 | B2 | 
|---|---|---|
| C1 : attributeKey is null | attributeKey = null | attributeKey ≠ null | 
| C2 : attributeKey is empty string | attributeKey = ""  | attributeKey ≠ "" | 
| C3 : attributes object is null | attributes = null | attributes ≠ null| 
| C4 : attributeKey exists in attributes | key not present (hasKey = false)| key present (hasKey = true)| 

**Identify possible values**
| Characteristic | B1 | B2 | 
|---|---|---|
| C1 : attributeKey is null | el.hasAttr(null) | el.hasAttr("id") | 
| C2 : attributeKey is empty string | el.hasAttr("")   | el.hasAttr("class") | 
| C3 : attributes object is null | element created with no attributes | element with attributes set | 
| C4 : attributeKey exists in attributes | <div> → el.hasAttr("id") | <div id="main"> → el.hasAttr("id"))| 

#### 4. Combine partitions to define test requirements
-   **Assumption**: Base Choice Coverage (BCC)
-   **Test requirements**: ∑(Bi−1) = (2−1) + (2−1) + (2−1) + (2−1) = 5 test requirements
-   **Test Selected** :  
    -   **Base test**: (C1b2, C2b2, C3b2, C4b2)   
    -   **Variations**: (C1b1, C2b2, C3b2,C4b2), (C1b2, C2b1, C3b2, C4b2), (C1b2, C2b2, C3b1, C4b2), (C1b2, C2b2,C3b2,C4b1)

#### 5. Derive test values
| Test Case | Test Value | Expected Result | 
|---|---|---|
| T1 | "id", <div id="main">  | true | 
| T2 | "class", <div id="main"> | False | 
| T3 | null, <div> | IllegalArgumentException | 
| T4 | "", <div> | IllegalArgumentException | 
| T5 | "id", (element has noattributes, e.g. attributes = null)| false | 

## Test Suite 4 : ``Text``
### Interface based
#### 1. Identify testable function
-  public String text() 
#### 2. Identify parameters, return types, return values, and exceptional behavior
-	**Parameters**: None — the function operates on the internal state of the Element object itself.
-	**Return type**: String
-	**Return value** : 
    -   Returns the combined text content of this element and all its child nodes.
    -  The resulting string is trimmed (leading and trailing spaces are removed)
-	**Exceptional behavior** : No explicit exceptions are thrown.
#### 3. Model the input domain
-	**C1** : Whether the element has child nodes or not  
-   **C2** : Whether the element contains text content or is empty  
-   **C3** : Whether the element contains nested elements with text or not
-   **C4** : Whether the text contains leading or trailing whitespace that should be trimmed

**Partition characteristics**
| Characteristic | B1 | B2 |
|---|---|---|
| C1 : element has child nodes | True | False |
| C2 : element contains text content | True | False |
| C3 : element contains nested elements with text | True | False |
| C4 : text contains leading/trailing whitespace | True | False |


**Identify possible values**
| Characteristic | B1 | B2 |
|---|---|---|
| C1 : element has child nodes | el.text() on <div><span>Hello</span></div> | el.text() on <p>Hello</p> |
| C2 : element contains text content | el.text() on <p>Hello</p> | el.text() on <div></div> |
| C3 : element contains nested elements with text | el.text() on <p>Hello <b>World</b></p> | el.text() on <p>Hello</p> |
| C4 : text contains leading/trailing whitespace | el.text() on <p> Trimmed Text </p> → "Trimmed Text" | el.text() on <p>CleanText</p> → "CleanText" |

#### 4.	Combine partitions to define test requirements
-	**Assumption** : Multiple Base Choice Coverage (MBCC)
-	**Test Requirements (TR)** : Σ(𝐵𝑖−1)=(2−1)+(2−1)+(2−1)+(2−1) = 5 base partitions
-   **Test Selected** : 
    - **Base test** : (C1b1, C2b1, C3b1, C4b2)   
    - **Variations** :   
                    (C1b2, C2b1, C3b1, C4b2)   
                    (C1b2, C2b1, C3b1, C4b2),  
                    (C1b1, C2b2, C3b1, C4b2),  
                    (C1b1, C2b1, C3b2, C4b2)
#### 5. Derive test values
| Test | Test Value | Expected Result |
|---|---|---|
| T1  | <div><span>Hello</span></div>| "Hello" |
| T2  | <p>Hello</p> | "Hello" |
| T3  | <div></div> | "" |
| T4  | <p>Hello <b>World</b></p>| "Hello World" |
| T5  | <p> Trimmed Text </p> | "Trimmed Text" |


### Functionality based
#### 1. Identify testable function
-  public String text()) 
#### 2. Identify parameters, return types, return values, and exceptional behavior
-	**Parameters** : None — the function depends on the internal state of the Element object 
-	**Return type** : String
-	**Return value** : 
    -   Returns the combined textual content of the element and all its child nodes.
    -   The resulting string is trimmed (leading and trailing spaces are removed)
-	**Exceptional behavior** : No explicit exception is thrown.
#### 3. Model the input domain
-	**C1** : Whether the element has child nodes or not  
-	**C2** : Whether the element contains text content or is empty
-	**C3** : Whether the element contains nested elements with text or not
-	**C4** : Whether the text contains leading or trailing whitespace to trim

**Partition characteristics**
| Characteristic | B1 | B2 |
|---|---|---|
| C1 : element has child nodes | True | False |
| C2 : element contains text content | True | False |
| C3 : element contains nested elements with text | True | False |
| C4 : text contains leading/trailing whitespace | True | False |

**Identify possible values**
| Characteristic | B1 | B2 |
|---|---|---|
| C1 : element has child nodes | <div><span>Hello</span></div> | <p>Hello</p> |
| C2 : element contains text content | <p>Hello</p> | <div></div> |
| C3 : element contains nested elements with text |  <p>Hello <b>World</b></p> | <p>Hello</p> |
| C4 : text contains leading/trailing whitespace |<p> Trimmed Text </p>  | <p>CleanText</p> |


#### 4.	Combine partitions to define test requirements
-	**Assumption** : Multiple Base Choice Coverage (MBCC)
-	**Test Requirements** (TR) : Σ(𝐵𝑖−1)=(2−1)+(2−1)+(2−1)+(2−1)= 5 base partitions
-   **Test Selected** :   
    -   **Base Test** : (C1b1, C2b1, C3b1, C4b2)
    -   **Variations** :   
            (C1b2, C2b1, C3b1, C4b2),  
            (C1b1, C2b2, C3b1, C4b2),  
            (C1b1, C2b1, C3b2, C4b2),  
            (C1b1, C2b1, C3b1, C4b1)

#### 5. Derive test values
| Test | HTML Input | Expected Result |
|---|---|---|
| TC1  | <div><span>Hello</span></div> | "Hello" |
| TC2  | <p>Hello</p> | "Hello" |
| TC3  | <div></div> | "" |
| TC4  | <p>Hello <b>World</b></p> | "Hello World" |
| TC5  | <p> Trimmed Text </p> | "Trimmed Text" |

## Test Suite 5 – ``Element.hasText()``

### Interface based

#### 1. Identify testable functions
The function is `Element.hasText()`.

#### 2. Identify parameters, return types, and exceptional behavior
* **Parameters**: This function has no parameters.
* **Return type**: `Boolean`.
* **Return value**:
    * It returns `true` if the element itself or any element inside it has text. The text must not be only spaces.
    * It returns `false` if the element is empty, only has spaces, or contains other empty elements.

#### 3. Model the input domain
* **C1**: Does the element have its own text? (Direct text)
* **C2**: Do any elements inside this element have text? (Child text)
* **C3**: Is the text just empty spaces (whitespace)?

**Partition Characteristics**

| Characteristic                | b1 (True) | b2 (False) |
| :---------------------------- | :-------- | :--------- |
| C1 = Has direct text          | True      | False      |
| C2 = Child has text           | True      | False      |
| C3 = Text is only whitespace  | True      | False      |

**Identify Possible Values**

| Characteristic                | b1 (True) | b2 (False) |
| :---------------------------- | :-------- | :--------- |
| C1 = Has direct text          | div.text("Hello");      | Element div = new Elemen("div");      |
| C2 = Child has text           | div.append("<p>World</p>");      | Element div = new Element("div");      |
| C3 = Text is only whitespace  | div.text(" ");      | div.text("Hello");      |

#### 4. Combine partitions to define test requirements (ECC)
* **Assumption**: Each Case Coverage.
* **Test requirements**: Maximum is 2.
* **Test requirements**: ([C1b1,C2b1,C3b1], [C1b2,C2b2,C3b2]) 

#### 5. Derive test values

| Test Case | Test Value            | Expected Result |
| :-------- | :-------------------- | :-------------- |
| T1        | `div.text("Hello");`  | `true`          |
| T2        | `div.text(" ");`      | `false`         |

---

### Functionality based

#### 1. Identify testable functions
The function is `Element.hasText()`.

#### 2. Identify parameters, return types, and exceptional behavior
* **Parameters**: This function has no parameters.
* **Return type**: `Boolean`.
* **Return value**:
    * It returns `true` if the element itself or any element inside it has text. The text must not be only spaces.
    * It returns `false` if the element is empty, only has spaces, or contains other empty 
elements.

#### 3. Model the input domain
* **C1**: Does the element have any children (nodes) inside it?
* **C2**: Is a child a `TextNode` (not an HTML tag like <p> or a comment)? 
* **C3**: If it is a `TextNode`, does it contain real text (not just spaces)?

**Partition Characteristics**

| Characteristic            | b1 (True) | b2 (False) |
| :------------------------ | :-------- | :--------- |
| C1 = Has child nodes      | True      | False      |
| C2 = Child is a TextNode  | True      | False      |
| C3 = TextNode has content | True      | False      |

#### 4. Combine partitions to define test requirements (ECC)
* **Assumption**: Each Case Coverage.
* **Test requirements**: Max is 2 
* **Selected tests**: `([C1b2], [C1b1, C2b2], [C1b1, C2b1, C3b2], [C1b1, C2b1, C3b1])`

#### 5. Derive test values

| Test Case | Test Value                          | Expected Result |
| :-------- | :---------------------------------- | :-------------- |
| T1        | `Element div = new Element("div");` | `false`         |
| T2        | `div.append("");`                   | `false`         |

## Test Suite 6 – ``Element.addClass()``

### Interface based

#### 1. Identify testable functions
`Element.addClass(String className)`

#### 2. Identify parameters, return types, and exceptional behavior
* **Parameters**: `className` (String)  - The class name to add. You can add more than one class by using a space. 
* **Return type**: `String`.
* **Return value**: The `Element` with the new class added.

#### 3. Model the input domain
* **C1**: State of the `class` attribute before adding a new one.
* **C2**: What kind of class name are we adding?
* **C3**: What is the format of the input string?

**Partition Characteristics**

| Characteristic      | b1                  | b2                     | b3                           |
| :------------------ | :------------------ | :--------------------- | :--------------------------- |
| C1 = Existing class | No class attribute  | Has one class          | Has multiple classes         |
| C2 = Input type     | Single new class    | Multiple new classes   | A class that already exists  |
| C3 = Input format   | Normal              | With extra spaces      |                              |

**Identify Possible Values**

| Characteristic      | b1                  | b2                     | b3                           |
| :------------------ | :------------------ | :--------------------- | :--------------------------- |
| C1 = Existing class | No class attribute  | div.attr("class", "a")          | div.attr("class", "a b")         |
| C2 = Input type     | .addClass("z")    | .addClass("x y")   | .addClass("a")  |
| C3 = Input format   | .addClass("new")              | .addClass(" padded ")      |                              |

#### 4. Combine partitions to define test requirements (PWC)
* **Assumption**: Pair-Wise Combination (PWC).
* **Test requirements**: Maximum is 9 (3*3).
* **Selected tests:**: ([C1b1, C2b1, C3b1],  
[C1b1, C2b2, C3b2],  
[C1b1, C2b3, C3b1],  
[C1b2, C2b1, C3b2],  
[C1b2, C2b2, C3b1],  
[C1b2, C2b3, C3b1],  
[C1b3, C2b1, C3b1],  
[C1b3, C2b2, C3b1],  
[C1b3, C2b3, C3b2])

#### 5. Derive test values

| Test Case | Test Value                                          | Expected Result      |
| :-------- | :-------------------------------------------------- | :------------------- |
| T1        | `div.addClass("z");`                                | `class="z"`          |
| T2        | `div.addClass(" x y ");`                            | `class="x y"`        |
| T3        | `div.addClass("a");`                                | `class="a"`          |
| T4        | `div.attr("class","a"); div.addClass(" z ");`        | `class="a z"`        |
| T5        | `div.attr("class","a"); div.addClass("x y");`        | `class="a x y"`      |
| T6        | `div.attr("class","a"); div.addClass("a");`          | `class="a"`          |
| T7        | `div.attr("class","a b"); div.addClass("z");`        | `class="a b z"`      |
| T8        | `div.attr("class","a b"); div.addClass("xy");`       | `class="a b xy"`     |
| T9        | `div.attr("class","a b"); div.addClass(" a ");`      | `class="a b"`        |

---

### Functionality based

#### 1. Identify testable functions
`Element.addClass(String className)`

#### 2. Identify parameters, return types, and exceptional behavior
* **Parameters**: `className` (String)  - The class name to add. You can add more than one class by using a space.
* **Return type**: `Element`.
* **Return value**: The `Element` with the new class added.

#### 3. Model the input domain
* **C1**: Does the `class` attribute already exist?
* **C2**: Does the input string have multiple classes?
* **C3**: Does the class to add already exist in the element?

**Partition Characteristics**

| Characteristic            | b1 (True) | b2 (False) |
| :------------------------ | :-------- | :--------- |
| C1 = Attribute exists     | True      | False      |
| C2 = Multiple class input | True      | False      |
| C3 = Class already exists | True      | False      |

**Identify Possible Values**

| Characteristic            | b1 (True) | b2 (False) |
| :------------------------ | :-------- | :--------- |
| C1 = Attribute exists     | div.attr("class","old");      | new Element("div")      |
| C2 = Multiple class input | .addClass("a b");      | .addClass("a");       |
| C3 = Class already exists | div.attr("class","a");.addClass("a");      | div.attr("class","a");.addClass("b");      |

#### 4. Combine partitions to define test requirements (PWC)
* **Assumption**: Pair-Wise Combination.
* **Test requirements**: Maximum is 4 (2*2).
* **Selected tests:**: ([C1b1, C2b1, C3b1], [C1b1, C2b2, C3b2], [C1b2, C2b1, C3b2], [C1b2, C2b2, C3b1])

#### 5. Derive test values

| Test Case | Test Value                                      | Expected Result     |
| :-------- | :---------------------------------------------- | :------------------ |
| T1        | `div.attr("class","a"); div.addClass("a b");`    | `class="a b"`       |
| T2        | `div.attr("class","old"); div.addClass("new");`  | `class="old new"`   |
| T3        | `div.addClass("xy");`                           | `class="x y"`       |
| T4        | `div.addClass("a");`                            | `class="a"`         |

## Test Suite 7 – matches

### Interface based

#### 1. Identify testable function
- `boolean matches(Element root, Element element)`

#### 2. Identify parameters, return types, return values, and exceptional behavior
- **Parameters:**
  - `root` → the root element of the DOM tree (type: Element)
  - `element` → the element to check for containing text (type: Element)
- **Return type:** `Boolean`
- **Return values:**
  - `true` → if the element contains any textual content  
  - `false` → if the element does not contain any text
- **Exceptional behavior:** None (no exception is thrown)

#### 3. Model the input domain
- **C1** = Whether the element contains text content  
- **C2** = Whether the element contains only whitespace text  
- **C3** = Whether the element is empty or has child elements with no text  

| Characteristic | B1 | B2 |
|----------------|----|----|
| C1 = element contains text | True | False |
| C2 = element contains only whitespace | True | False |
| C3 = element empty or childless | True | False |

#### Identify Possible Values
- **C1**  
  `<div>Hello</div>` → true  
  `<div></div>` → false  
- **C2**  
  `<div>   </div>` → true  
  `<div>Hello</div>` → false  
- **C3**  
  `<div></div>` → true  
  `<div><p>Hello</p></div>` → false  

#### 4. Combine partitions to define test requirements
- **Assumption:** Base Choice Coverage (BCC)  
- **Test requirements:** ∑(Bi−1) = (2−1) + (2−1) + (2−1) = **4 test requirements**
- **Selected tests:**  
    - **Base test** : (C1b1, C2b2, C3b2)  
    - **Variations** :  
        (C1b2, C2b2, C3b2),  
        (C1b1, C2b1, C3b2),  
        (C1b1, C2b2, C3b1)

#### 5. Derive test values

| Test Case | HTML Input | Expected Result |
|------------|-------------|-----------------|
| T1 | `<div>Hello</div>` | true |
| T2 | `<div></div>` | false |
| T3 | `<div>   </div>` | false |
| T4 | `<div><p>Hello</p></div>` | true |

---

###  Functionality based

#### 1. Identify testable function
- `public boolean matches(Element root, Element element)`

#### 2. Identify parameters, return types, return values, and exceptional behavior
- **Parameters:**
  - `root` → the root of DOM tree  
  - `element` → element to check  
- **Return type:** `Boolean`
- **Return values:**
  - `true` → if `element.text()` returns a non-empty string (ignoring whitespace)  
  - `false` → otherwise  
- **Exceptional behavior:** None

#### 3. Model the input domain
- **C1** = element has text content  
- **C2** = element has only whitespace  
- **C3** = element is empty  
- **C4** = element has text in nested elements  

#### 4. Combine partitions to define test requirements
- **Assumption:** Base Choice Coverage  
- **Selected tests:**  
    (C1b1, C2b2, C3b2, C4b2),  
    (C1b2, C2b1, C3b2, C4b2),  
    (C1b2, C2b2, C3b1, C4b2),  
    (C1b2, C2b2, C3b2, C4b1)

#### 5. Derive test values

| Test Case | HTML Input | Expected Result |
|------------|-------------|-----------------|
| T1 | `<div>Hello</div>` | true |
| T2 | `<div></div>` | false |
| T3 | `<div>   </div>` | false |
| T4 | `<div><span>Hello</span></div>` | true |

##  Test Suite 8 – `isSafeTag`

###   Interface based

#### 1. Identify testable function
- `boolean isSafeTag(String tag)`

#### 2. Identify parameters, return types, return values, and exceptional behavior
- **Parameters:** `tag` → name of HTML tag (String)
- **Return type:** `Boolean`
- **Return values:**
  - `true` → if tag is in whitelist (safe tags)
  - `false` → if tag is not in whitelist or tag is null/empty
- **Exceptional behavior:** None

#### 3. Model the input domain
- **C1** = Whether tag is null  
- **C2** = Whether tag is empty string  
- **C3** = Whether tag is in whitelist  

| Characteristic | B1 | B2 |
|----------------|----|----|
| C1 = tag is null | True | False |
| C2 = tag is empty | True | False |
| C3 = tag is in whitelist | True | False |

#### 4. Combine partitions to define test requirements
- **Assumption:** Base Choice Coverage (BCC)  
- **Test requirements:** ∑(Bi−1) = (2−1)+(2−1)+(2−1)= ** 3 test requirements**
- **Selected tests:**  
    - **Base test** : (C1b2, C2b2, C3b1)  
    - **Variations** :  
        (C1b1, C2b2, C3b1),  
        (C1b2, C2b1, C3b1),  
        (C1b2, C2b2, C3b2)

#### 5. Derive test values

| Test Case | Input Tag | Expected Result |
|------------|------------|-----------------|
| T1 | div | true |
| T2 | script | false |
| T3 | null | false |
| T4 | *(empty)* | false |

---

###  Functionality based

#### 1. Identify testable function
- `public boolean isSafeTag(String tag)`

#### 2. Identify parameters, return types, return values, and exceptional behavior
- **Parameters:** `tag` → name of tag to validate  
- **Return type:** `Boolean`
- **Return values:**
  - `true` → if tag exists in whitelist array/list  
  - `false` → if tag is null, empty, or not in whitelist  
- **Exceptional behavior:** None

#### 3. Model the input domain
- **C1** = tag is null  
- **C2** = tag is empty  
- **C3** = tag exists in whitelist  

#### 4. Combine partitions to define test requirements
- **Assumption:** All Combinations Coverage   
- **Test Requirements** : ∑n(Bi−1) = (2−1)+(2−1)+(2−1)= 4 test requirements   
- **Selected tests:**  
    (C1b2, C2b2, C3b1),  
    (C1b1, C2b2, C3b1),  
    (C1b2, C2b1, C3b1),  
    (C1b2, C2b2, C3b2)

#### 5. Derive test values

| Test Case | Input Tag | Expected Result |
|------------|------------|-----------------|
| T1 | p | true |
| T2 | script | false |
| T3 | null | false |
| T4 | *(empty)* | false |

##  Test Suite 9 – Cleanner.clean
###  Interface based
#### 1. Identify testable function
- `Document clean(Document dirtyDocument)`

#### 2. Identify parameters, return types, return values, and exceptional behavior
- **Parameters** : `dirtyDocument (Document)`  
- **Return type** : `Document`  
- **Return value** : 
  - สร้าง shell ใหม่ด้วย `dirtyDocument.baseUri()`
  - คัดลอก **เฉพาะโหนดที่ปลอดภัย** จาก `dirtyDocument.body()` ไปยัง `clean.body()`
  - โคลน `dirtyDocument.outputSettings()` ไปยังผลลัพธ์
- **Exceptional behavior** :
  - หาก `dirtyDocument == null` ให้โยน `IllegalArgumentException`

#### 3. Model the input domain
- **C1** : ความเป็น `null` ของอินพุต
- **C2** : ค่า `baseUri` ของอินพุต (เมื่อ C1 = not null)
- **C3** : ประเภทเนื้อหาใน `<body>` (เมื่อ C1 = not null)
- **C4** : ค่า OutputSettings.prettyPrint (เมื่อ C1 = not null)

**Partition characteristics**
| Characteristic | B1 | B2 | B3 | B4 |
|---|---|---|---|---|
| C1 : dirtyDocument เป็น null หรือไม่ | null | not null | - | - |
| C2 : baseUri | empty `""` | non-empty (เช่น `"https://ex"`) | - | - |
| C3 : ประเภทเนื้อหา | empty | safe-only | unsafe-only | mixed |
| C4 : prettyPrint | true | false | - | - |

**Identify possible values**
| Characteristic | B1 | B2 | B3 | B4 |
|---|---|---|---|---|
| C1 | `clean(null)` | `Document.parse(...)` | - | - |
| C2 | `Jsoup.parse("", "")` | `Jsoup.parse("<p>x</p>", "https://ex")` | - | - |
| C3 | `""` | `"<p>Hello</p>"` | `"<script>x()</script>"` | `"<p>x</p><a onclick=\"h()\">k</a>"` |
| C4 | `prettyPrint(true)` | `prettyPrint(false)` | - | - |


#### 4. Combine partitions to define test requirements
- **Assumption** : Each Choice Coverage (ECC)
- **Test Requirements (TR)** : เลือกเคสที่ครอบคลุมแต่ละบล็อคของ C1–C4 อย่างน้อยหนึ่งครั้ง
- **Selected tests:**  
  - (C1B1)  
  - (C1B2, C2B1, C3B2, C4B1)  
  - (C1B2, C2B2, C3B2, C4B1)  
  - (C1B2, C2B2, C3B3, C4B1)  
  - (C1B2, C2B2, C3B4, C4B1)  
  - (C1B2, C2B2, C3B2, C4B2)

#### 5. Derive test values
| Test | dirtyDocument setup | Expected Result |
|---|---|---|
| TC1 | `null` | โยน `IllegalArgumentException` |
| TC2 | baseUri = `""`, body empty, prettyPrint=true | baseUri คงเป็น `""`, body ว่าง, prettyPrint=true |
| TC3 | baseUri = `"https://ex"`, body = `<p>Hello</p>`, prettyPrint=true | `<p>Hello</p>` ถูกคงไว้, baseUri คงเดิม, prettyPrint=true |
| TC4 | baseUri = `"https://ex"`, body = `<script>x()</script>`, prettyPrint=true | ไม่มี `<script>` หรือข้อความจากสคริปต์หลงเหลือ |
| TC5 | baseUri = `"https://ex"`, body = `<p>x</p><a href="#" onclick="h()">k</a>`, prettyPrint=true | `<p>x</p>` ยังอยู่, `<a>` อยู่แต่ **ไม่มี** `onclick` |
| TC6 | baseUri = `"https://ex"`, body = `<p>Hello</p>`, prettyPrint=false | ค่าการจัดรูป (prettyPrint=false) ถูกคงไว้, `outputSettings` เป็นคนละอินสแตนซ์แต่ค่าเท่ากัน |

---

###  Functionality based
#### 1. Identify testable function
- `Document clean(Document dirtyDocument)`

#### 2. Identify parameters, return types, return values, and exceptional behavior
- **Parameters** : `dirtyDocument (Document)`  
- **Return type** : `Document`  
- **Return value** : สร้าง shell → คัดลอกเฉพาะโหนดปลอดภัย → โคลน output settings  
- **Exceptional behavior** : `IllegalArgumentException` เมื่ออินพุตเป็น `null`

#### 3. Model the input domain
- **C1** : เส้นทางการทำงาน (Execution path)
- **C2** : ผลลัพธ์การคัดกรองความปลอดภัยของเนื้อหา (เมื่อ C1 เป็นเส้นทาง non-null)
- **C3** : การแพร่ค่า baseUri (เมื่อ C1 เป็นเส้นทาง non-null)
- **C4** : คุณสมบัติของ output settings (ค่าเท่าเดิม vs อินสแตนซ์คนละตัว)

**Partition characteristics**
| Characteristic | B1 | B2 | B3 | B4 |
|---|---|---|---|---|
| C1 : Path | guard (null → throw) | shell สร้างสำเร็จ | copy safe nodes | clone output settings |
| C2 : Safety outcome | empty→empty | safe-only copied | mixed→filtered | - |
| C3 : baseUri | empty | non-empty | - | - |
| C4 : OutputSettings | values preserved | different instance | - | - |

**Identify possible values**
| Characteristic | B1 | B2 | B3 | B4 |
|---|---|---|---|---|
| C1 | `clean(null)` | `createShell(… )` | `copySafeNodes(… )` | `outputSettings.clone()` |
| C2 | `""` | `"<p>Hello</p>"` | `"<p>x</p><script>y</script>"` | - |
| C3 | `""` | `"https://ex"` | - | - |
| C4 | `prettyPrint` เท่าเดิม | อ็อบเจกต์ `OutputSettings` คนละตัว | - | - |

#### 4. Combine partitions to define test requirements
- **Assumption** : Each Choice Coverage (ECC)
- **Selected tests:**  
  - (C1B1)  
  - (C1B2, C3B1)  
  - (C1B3, C2B2, C3B2)  
  - (C1B3, C2B3, C3B2)  
  - (C1B4, C4B1)  
  - (C1B4, C4B2)

#### 5. Derive test values
| Test | dirtyDocument setup | Expected Result |
|---|---|---|
| TC1 | `null` | โยน `IllegalArgumentException` |
| TC2 | baseUri = `""`, body empty | ได้เอกสาร shell ที่ `body` ว่าง และ baseUri ว่าง |
| TC3 | baseUri = `"https://ex"`, body = `<p>Hello</p>` | โหนดปลอดภัยถูกคัดลอก |
| TC4 | baseUri = `"https://ex"`, body = `<p>x</p><script>y</script>` | โหนดอันตรายถูกลบ เหลือเฉพาะปลอดภัย |
| TC5 | prettyPrint=false ในอินพุต | ค่า prettyPrint ถูกคงไว้ในผลลัพธ์ |
| TC6 | เปรียบเทียบอินสแตนซ์ `outputSettings` | อินสแตนซ์ **คนละตัว** แต่ค่าภายในเท่ากัน |

##  Test Suite 10 – Entity.escape
###  Interface based
#### 1. Identify testable function
- escape(String data, OutputSettings out)

#### 2. Identify parameters, return types, return values, and exceptional behavior
- **Parameters** :
  - data (String) – ข้อความอินพุตที่ต้องการ escape
  - out (OutputSettings) – ให้ค่า escapeMode() และ charset()
- **Return type** : String
- **Return value** : สตริงที่ถูก escape ตาม escapeMode และ charset
- **Exceptional behavior** :
  - NullPointerException เมื่อ data == null หรือ out == null

#### 3. Model the input domain
- **C1** : data เป็น null หรือไม่
- **C2** : out เป็น null หรือไม่
- **C3** : ความยาวของ data (เมื่อ data != null)
- **C4** : ประเภทเนื้อหาใน data (เมื่อ non-empty)
- **C5** : escapeMode จาก out
- **C6** : charset จาก out

**Partition characteristics**
| Characteristic | B1 | B2 | B3 | B4 |
|---|---|---|---|---|
| C1 : data nullness | data == null | data != null | - | - |
| C2 : out nullness | out == null | out != null | - | - |
| C3 : data length (if C1=B2) | empty ("") | non-empty | - | - |
| C4 : content type (if C1=B2 & C3=B2) | no-escapables | basic-HTML (& < > " ') | high-Unicode | already-escaped (&amp;) |
| C5 : escapeMode (if C2=B2) | BASE | XHTML | - | - |
| C6 : charset (if C2=B2) | UTF-8 | ISO-8859-1 | US-ASCII | - |

**Identify possible values**
| Characteristic | B1 | B2 | B3 | B4 |
|---|---|---|---|---|
| C1 : data nullness | null | "..." | - | - |
| C2 : out nullness | null | valid out | - | - |
| C3 : data length | "" | "hello" | - | - |
| C4 : content type | "hello" | "<a&b>", "He said 'ok'" | "สวัสดี" | "&amp;<" |
| C5 : escapeMode | BASE | XHTML | - | - |
| C6 : charset | UTF-8 | ISO-8859-1 | US-ASCII | - |


### 4. Combine partitions to define test requirements
- **Assumption** : PWC (Pair-Wise Coverage)
- **Selected tests:** (C1B1), (C2B1), (C1B2,C2B2,C3B1,C5B1,C6B1), (C1B2,C2B2,C3B2,C4B1,C5B1,C6B1), (C1B2,C2B2,C3B2,C4B2,C5B1,C6B1),
  (C1B2,C2B2,C3B2,C4B2,C5B2,C6B1), (C1B2,C2B2,C3B2,C4B3,C5B1,C6B1), (C1B2,C2B2,C3B2,C4B3,C5B1,C6B3), (C1B2,C2B2,C3B2,C4B3,C5B1,C6B2), (C1B2,C2B2,C3B2,C4B4,C5B1,C6B1)

### 5. Derive test values
| Test | data | out | escapeMode | charset | Expected Result |
|---|---|---|---|---|---|
| TC1 | null | valid | – | – | NPE |
| TC2 | "hi" | null | – | – | NPE |
| TC3 | "" | valid | BASE | UTF-8 | "" |
| TC4 | "hello" | valid | BASE | UTF-8 | "hello" |
| TC5 | "<a&b>" | valid | BASE | UTF-8 | "&lt;a&amp;b&gt;" |
| TC6 | "He said 'ok'" | valid | XHTML | UTF-8 | "He said &apos;ok&apos;" |
| TC7 | "สวัสดี" | valid | BASE | UTF-8 | "สวัสดี" |
| TC8 | "สวัสดี" | valid | BASE | US-ASCII | "&#3626;&#3623;&#3633;&#3626;&#3604;&#3637;" |
| TC9 | "©™" | valid | BASE | ISO-8859-1 | © encodable, ™ → entity |
| TC10 | "&amp;<" | valid | BASE | UTF-8 | "&amp;&lt;" |

---

## Functionality based
### 1. Identify testable function
- escape(String data, OutputSettings out)

### 2. Identify parameters, return types, return values, and exceptional behavior
- **Parameters** : data (String), out (OutputSettings)
- **Return type** : String
- **Return value** :
  - คืนค่าข้อความที่ escape แล้วตามโหมด/charset
  - อักขระที่ไม่รองรับใน charset → แปลงเป็น entity (named/numeric)
  - ข้อความที่ escape มาแล้วบางส่วนจะไม่ถูก double-escape
- **Exceptional behavior** : NullPointerException เมื่อ data == null หรือ out == null

### 3. Model the input domain
- **D1** : Null handling (data/out)
- **D2** : Content behavior (plain/basic-HTML/high-Unicode/already-escaped)
- **D3** : Charset encodability (full/partial/none)
- **D4** : Mode effect (apostrophe escaped vs not escaped)

**Partition characteristics**
| Characteristic | B1 | B2 | B3 |
|---|---|---|---|
| D1 : null handling | data==null | out==null | neither null |
| D2 : content | plain/no-escapables | basic-HTML | high-Unicode/already-escaped |
| D3 : charset encodability | full (UTF-8) | partial (ISO-8859-1) | none (US-ASCII) |
| D4 : mode effect | BASE | XHTML | - |

**Identify possible values**
| Characteristic | B1 | B2 | B3 |
|---|---|---|---|
| D1 | data=null | out=null | data="...", out=valid |
| D2 | "hello" | "<a&b>", "He said 'ok'" | "สวัสดี", "&amp;<" |
| D3 | UTF-8 | ISO-8859-1 | US-ASCII |
| D4 | BASE | XHTML | - |

### 4. Combine partitions to define test requirements
- **Assumption** : PWC (Pair-Wise Coverage)
- **Selected tests:** (D1B1), (D1B2), (D1B3+D2B1+D3B1+D4B1), (D1B3+D2B2+D3B1+D4B1), (D1B3+D2B2+D3B1+D4B2),
  (D1B3+D2B3+D3B1+D4B1), (D1B3+D2B3+D3B3+D4B1), (D1B3+D2B3+D3B2+D4B1), (D1B3+D2B3+D3B1+D4B1 with pre-escaped)

### 5. Derive test values
| Test | Input / Settings | Expected Output |
|---|---|---|
| FTC1 | data=null, out=valid | NPE |
| FTC2 | data="x", out=null | NPE |
| FTC3 | data="", mode=BASE, cs=UTF-8 | "" |
| FTC4 | "plain", BASE, UTF-8 | "plain" |
| FTC5 | "A&B<C>", BASE, UTF-8 | "A&amp;B&lt;C&gt;" |
| FTC6 | "Bob's", XHTML, UTF-8 | "Bob&apos;s" |
| FTC7 | "สวัสดี", BASE, UTF-8 | unchanged |
| FTC8 | "สวัสดี", BASE, US-ASCII | numeric entities |
| FTC9 | "©™", BASE, ISO-8859-1 | © encodable, ™ entity |
| FTC10 | "&amp; <tag>", BASE, UTF-8 | "&amp; &lt;tag&gt;" |
