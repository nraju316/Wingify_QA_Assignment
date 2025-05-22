# ✅ Selenium Test Automation Framework (TestNG + Java)

This project is a robust Selenium-based test automation framework built using **Java** and **TestNG**. It supports scalable web UI testing with report generation and automated copying of test reports to the user's **Downloads** folder.


## 📌 Table of Contents

* [📦 Project Structure](#-project-structure)
* [🧰 Technologies Used](#-technologies-used)
* [⚙️ Setup & Installation](#️-setup--installation)
* [🧪 How to Run the Framework](#-how-to-run-the-framework)
* [📊 Test Reports](#-test-reports)
* [🛠️ Customization](#️-customization)
* [🧪 Test Report/Output Screenshot]
* [📌 Best Practices Followed](#-best-practices-followed)
* [👤 Author](#-author)


## 📦 Project Structure

```
📦 Automation
├── 📂 src
│   ├── 📂 main
│   │   └── 📂 java
│           └── 📂 tests/Tests_initialization    # Contains DriverBase.java with base URL and API key
│               └── DriverBase.java
│   │       └── 📂 WebPages                      # Page Object Model classes
│   │           ├── AI_BASED_VALIDATION.java
│   │           ├── DreamsDairyPage.java
│   │           ├── DreamsTotalPage.java
│   │           └── HomePage.java
│   └── 📂 test
│       └── 📂 java
│           ├── 📂 tests                         # Test classes for each page
│           │   ├── AI_BASED_VALIDATION_Test.java
│           │   ├── DreamsDairyPage_Test.java
│           │   ├── DreamsTotalPage_Test.java
│           │   └── HomePage_Test.java
├── 📂 test-output                                # Default TestNG report directory
│   └── emailable-report.html                     # Generated test report
├── 📄 pom.xml                                    # Maven build descriptor

```

## 🧰 Technologies Used

| Technology           | Version                                                                        | Purpose                                    |
| -------------------- | ------------------------------------------------------------------------------ | ------------------------------------------ |
| **Java**             | 21.0.7                                                                         | Programming language                       |
| **Selenium**         | 4.32.0                                                                         | Web automation framework                   |
| **TestNG**           | 7.11.0                                                                         | Testing framework                          |
| **WebDriverManager** | 5.8.0                                                                          | Manages browser drivers automatically      |
| **Eclipse IDE**      | 2025-06 M2 (4.36.0)                                                            | Development environment                    |
| **Google Chrome**    | 136.0.7103.114                                                                 | Browser for test execution                 |
| **Maven**            | 3.3.9.900                                                                      | Build and dependency management            |
| **Google AI Studio** | Gemini API (via [aistudio.google.com](https://aistudio.google.com/app/apikey)) | AI validation using Gemini language model  |


## ⚙️  Setup & Installation

## Prerequisites

Ensure the following tools and environments are installed on your system:

Eclipse IDE for Enterprise Java and Web Developers – Version 2025-06 M2 (4.36.0)

Google Chrome Browser – Version 136.0.7103.114

Stable Internet Connection –
Required for:
        TestNG installation

        WebDriverManager (auto-downloads browser drivers)
        
        API Key via Gemini (Gemini Pro/Pro Vision)


##🧪  How to Run the Framework

##🔧  Step-by-Step Setup

Install Eclipse IDE
Download and install Eclipse IDE for Java Developers:
👉 https://www.eclipse.org/downloads

Clone or Download the Repository

download the Github repository ZIP and extract it locally.

Import the Project into Eclipse

        Open Eclipse
        
        Go to File → Import → Maven → Existing Maven Projects
        
        Select the extracted/cloned project folder
        
        Click Finish to import

Install TestNG Plugin (if not already installed)
        
        Go to Eclipse -> Help → Eclipse Marketplace
        
        Search for TestNG for Eclipse
        
        Click Install and follow the installation steps
        
        Restart Eclipse when prompted
        Need visual guidance? You can follow this YouTube tutorial or any other general youtube videos:
        [https://www.youtube.com/watch?v=J9VM_JyiJFo]

        After installation Right click any test class run as using TestNg


Set Your Gemini API Key (Optional )

        Visit Google AI Studio
        
        Generate your API Key
        
        Open the DriverBase.java file
        
        Replace the default placeholder with your personal key:
        
        public static final String API_KEY = "YOUR API KEY GOES HERE"

Run the Tests

        Open any test class from the tests package
        
        Right-click inside the class
        
        Select Run As → TestNG Test
        
        ✅ Tests are detected using TestNG annotations (e.g., @Test) — no XML suite file is needed.

> **Note:** WebDriverManager automatically downloads and manages the required browser drivers.


## 📊  Test Reports

After test execution, reports are generated and managed as follows:

- **Default Location:**  
  TestNG automatically generates the test report (`emailable-report.html`) inside the `test-output/` directory of the project.

- **Custom Feature:**  
  After each test run, the report is also **automatically copied** to the user's **Downloads** folder with a unique timestamp, for example:  
  `emailable-report_20250522_153045.html`

This allows for easier access and archiving of past reports outside the project structure.


## 🛠️  Customization

* To change BaseUrl or the endpoint and API key update DriverBase file in initilization


## Report/Output Screenshot

![Screenshot 2025-05-22 130451](https://github.com/user-attachments/assets/d3f8283e-7fba-4fdf-9968-af0c82f8d5b8)
![Screenshot 2025-05-22 130253](https://github.com/user-attachments/assets/60183f46-91a1-42a5-a9f0-202b91646e92)

## 📌 Best Practices Followed

* Page Object Model for maintainability
* Centralized WebDriver configuration
* Clear console logs & exception handling
* Time-stamped report naming to avoid overwriting

## 👤 Author

**Nagaraju K**  
📧 Email: [nraju316@gmail.com]  
📞 Phone: +91-6363909369  
💼 LinkedIn: [ https://www.linkedin.com/in/nagaraju-k-50888325a ]
