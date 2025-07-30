# Selenium Java Automation Framework
This is a structured Selenium WebDriver automation framework built using Java and TestNG. 
It is designed for cross-browser testing, data-driven testing, and clean reporting using Allure.
The framework also integrates environment-based configuration and proper logging via Log4j.
---

## 📌 Project Description
This automation framework simulates a real-world end-to-end scenario on the [Balsam Hill](https://www.balsamhill.com/) e-commerce site. 
It includes:
- Searching for a product
- Selecting and customizing it
- Adding it to the cart
- Verifying pricing
- Removing it from the cart
- Validating confirmation messages
---

## How to clone and run
git clone https://github.com/brixumacam/balsam-selenium-java.git
cd selenium-java-framework

## ⚙️ Framework Setup and Dependencies
### 📦 Built With
- **Java 24**
- **Selenium WebDriver 4.x**
- **Maven**
- **TestNG**
- **Allure Report**
- **Log4j 2**

### 🧩 Maven Dependencies
All dependencies are managed via `pom.xml`.

To install all dependencies:
```bash
mvn clean install
```

Run the following command to execute tests:
```bash
mvn clean test
```

### 🛠️ IDE Setup
1. **IntelliJ IDEA** or **Eclipse** is recommended.
2. Import the project as a Maven project.
3. Ensure all dependencies are resolved.
4. Set up the JDK to Java 24.
5. Configure the Allure plugin for your IDE if you want to view reports directly.
6. (Optional) Install the Allure command-line tool for generating reports:
   ```bash
   brew install allure
   ```

## 🙋‍♂️ Author
#### Brix Umacam
#### Principal Quality Engineering Candidate
#### GitHub: https://github.com/brixumacam
#### LinkedIn: https://www.linkedin.com/in/brixumacam/
