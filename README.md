# Loan Eligibility Advisor

This is a Java console application that checks a user's loan eligibility based on several factors including age, income, CIBIL score, loan amount, existing loans, and tenure. It categorizes users as High, Medium, or Low credit risk and estimates the chance of loan rejection. The application provides personalized suggestions and calculates EMI. A detailed report is saved as a `.txt` file.

---

## Java Concepts Covered

- **Object-Oriented Programming (OOP)**, Class `LoanApplicant` combines logic and data.
- **Classes and Objects**, Data is modeled through real-world entities.
- **Constructors**, Initializes user data using a parameterized constructor.
- **Methods**, Modular methods like `evaluateRiskWithReason()`, `getRejectionProbability()`, and others.
- **Conditionals & String Handling**, Used to apply logic and format responses.
- **Exception Handling**, Manages file actions with `try-catch`.
- **User Input**, Handled with the `Scanner` class.
- **Random Number Simulation**, For estimating loan rejection chances.
- **File I/O**, Saves report in a `.txt` file to the `D:/` drive.

---

## Java Packages Used

| Package              | Purpose                                        |
|----------------------|------------------------------------------------|
| `java.util.Scanner`  | Read user input                                |
| `java.io.FileWriter` | Export text-based loan report                  |
| `java.io.IOException`| Handle errors during file writing              |
| `java.util.Random`   | Simulate rejection chance (can be changed)    |

---

## Sample Features

- CIBIL Score analysis
- Loan rejection probability based on logic and range
- EMI calculator for eligible applicants
- Advice on improving loan approval chances
- Report generation saved as `.txt` on the D drive

---

## How to Run

1. Open the code in VS Code or any Java IDE.
2. Compile and run the program.
3. Follow the console prompts.
4. Check the saved report in `D:/loan_report_<name>.txt`.

---

## Note
The loan rejection probability is simulated for demonstration using `Random`. For real-world use, think about connecting to financial APIs or machine learning models for better accuracy.

---

## Developed By

Nandhini A – Pre-final year ECE student who loves using technology for real-world solutions.
