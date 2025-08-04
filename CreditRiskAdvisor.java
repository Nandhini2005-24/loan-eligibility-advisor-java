import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class LoanApplicant {
    String name;
    int age;
    double income;
    double loanAmount;
    int cibilScore;
    int loanTenure;
    boolean hasExistingLoans;
    int existingLoanCount;
    double existingLoanAmount;
    double existingLoanInterest;
    int monthsPaid;

    public LoanApplicant(String name, int age, double income, double loanAmount, int cibilScore, int loanTenure,
                         boolean hasExistingLoans, int existingLoanCount, double existingLoanAmount,
                         double existingLoanInterest, int monthsPaid) {
        this.name = name;
        this.age = age;
        this.income = income;
        this.loanAmount = loanAmount;
        this.cibilScore = cibilScore;
        this.loanTenure = loanTenure;
        this.hasExistingLoans = hasExistingLoans;
        this.existingLoanCount = existingLoanCount;
        this.existingLoanAmount = existingLoanAmount;
        this.existingLoanInterest = existingLoanInterest;
        this.monthsPaid = monthsPaid;
    }

    public String evaluateRiskWithReason() {
        String reason;
        String suggestion;
        String riskLevel;

        if (income < 30000 && cibilScore < 600 && loanAmount > 50000 && hasExistingLoans) {
            riskLevel = "High Credit Risk - Likely to Default";
            reason = "Low income, low CIBIL score, high loan amount, and existing loans increase your financial risk.";
            suggestion = "Try clearing your existing loans and work on improving your CIBIL score before applying again.";
        } else if (cibilScore >= 750 || (cibilScore >= 700 && income >= 45000 && !hasExistingLoans)) {
            riskLevel = "Low Credit Risk - Likely to Repay";
            reason = "Strong CIBIL score and manageable income make you a reliable borrower.";
            suggestion = "You are eligible for most loan offers. Keep up your good credit behavior.";
        } else {
            riskLevel = "Medium Credit Risk - Monitor Closely";
            reason = "CIBIL score is fair and income is modest.";
            if (!hasExistingLoans) {
                reason += " No existing loans helped reduce your risk.";
            }
            suggestion = "Start building your CIBIL score by paying bills and EMIs on time. Also aim to increase your income.";
        }

        return riskLevel + "\nReason: " + reason + "\nSuggestion: " + suggestion;
    }

    public String getRejectionProbability(String riskLevel) {
        Random rand = new Random();
        int probability;
        String comment;
        if (riskLevel.startsWith("High")) {
            probability = 70 + rand.nextInt(21);
            comment = "High chance of rejection. Please improve your financial profile.";
        } else if (riskLevel.startsWith("Medium")) {
            probability = 30 + rand.nextInt(6);  // 30-35
            comment = "Moderate chance of rejection. You're on the edge, improve CIBIL or income.";
        } else {
            probability = rand.nextInt(21);  // 0-20
            comment = "Low chance of rejection. You're likely to get approved if other conditions are met.";
        }
        return "Loan Rejection Probability: " + probability + "%\n" + comment;
    }

    public String ageBasedTip() {
        if (age < 25) {
            return "Tip: You're young. Start with small EMIs and build a good credit history by paying your bills on time.";
        }
        return "";
    }
}

public class CreditRiskAdvisor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Monthly Income: ");
        double income = sc.nextDouble();

        System.out.print("Enter Loan Amount: ");
        double loanAmount = sc.nextDouble();

        System.out.print("Enter CIBIL Score (300-900): ");
        int cibilScore = sc.nextInt();

        System.out.print("Enter Loan Tenure (in years): ");
        int loanTenure = sc.nextInt();

        System.out.print("Has Existing Loans? (true/false): ");
        boolean hasExistingLoans = sc.nextBoolean();

        int existingLoanCount = 0;
        double existingLoanAmount = 0;
        double existingLoanInterest = 0;
        int monthsPaid = 0;

        if (hasExistingLoans) {
            System.out.print("How many existing loans do you have? ");
            existingLoanCount = sc.nextInt();
            if (existingLoanCount == 1) {
                System.out.print("Enter existing loan amount remaining: ");
                existingLoanAmount = sc.nextDouble();
                System.out.print("Enter interest rate of existing loan (%): ");
                existingLoanInterest = sc.nextDouble();
                System.out.print("How many months have you paid already? ");
                monthsPaid = sc.nextInt();
            }
        }

        sc.nextLine(); // consume leftover newline

        LoanApplicant applicant = new LoanApplicant(name, age, income, loanAmount, cibilScore, loanTenure,
                hasExistingLoans, existingLoanCount, existingLoanAmount, existingLoanInterest, monthsPaid);

        String result = applicant.evaluateRiskWithReason();
        String[] riskLines = result.split("\n");
        String riskLevel = riskLines[0];

        System.out.println("\n==== Credit Risk Evaluation ====");
        System.out.println(result);

        String tip = applicant.ageBasedTip();
        if (!tip.isEmpty()) {
            System.out.println(tip);
        }

        String rejection = applicant.getRejectionProbability(riskLevel);
        System.out.println("\n" + rejection);

        StringBuilder report = new StringBuilder();
        report.append("Loan Credit Risk Report\n");
        report.append("=======================\n");
        report.append("Name: ").append(name).append("\n");
        report.append("Age: ").append(age).append("\n");
        report.append("Income: Rs.").append(income).append("\n");
        report.append("Loan Amount: Rs.").append(loanAmount).append("\n");
        report.append("CIBIL Score: ").append(cibilScore).append("\n");
        report.append("Loan Tenure: ").append(loanTenure).append(" years\n");
        report.append("Existing Loans: ").append(hasExistingLoans).append("\n");
        report.append(result).append("\n");
        if (!tip.isEmpty()) {
            report.append("Tip: ").append(tip).append("\n");
        }
        report.append(rejection).append("\n");

        if (riskLevel.contains("Medium")) {
            System.out.print("\nDo you want to know how to convert Medium risk to Low risk? (yes/no): ");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                String improvement = "You can reduce your risk by increasing your CIBIL score (above 700) and income (above Rs.50,000). Avoid taking multiple loans.";
                System.out.println(improvement);
                report.append("Conversion Tip: ").append(improvement).append("\n");
            }
        } else if (riskLevel.contains("High")) {
            System.out.print("\nDo you want to know how to reduce your High risk? (yes/no): ");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                String highAdvice = "To reduce High risk: Clear existing loans, improve your CIBIL score, and avoid high loan amounts until income increases.";
                System.out.println(highAdvice);
                report.append("High Risk Advice: ").append(highAdvice).append("\n");
            }
        } else if (riskLevel.contains("Low") && !hasExistingLoans) {
            System.out.print("\nDo you want to know your monthly EMI amount? (yes/no): ");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                double interestRate = 0.10;
                double totalInterest = loanAmount * interestRate * loanTenure;
                double totalRepayment = loanAmount + totalInterest;
                double monthlyEMI = totalRepayment / (loanTenure * 12);
                System.out.println("If the interest is fixed, then the following is applicable:");
                System.out.printf("Monthly EMI: Rs.%.2f%n", monthlyEMI);
                System.out.printf("Total repayment over %d years: Rs.%.2f%n", loanTenure, totalRepayment);
                report.append(String.format("Monthly EMI: Rs.%.2f%n", monthlyEMI));
                report.append(String.format("Total Repayment: Rs.%.2f over %d years%n", totalRepayment, loanTenure));
            }
        }

        if (hasExistingLoans && existingLoanCount == 1) {
            double remainingAmount = existingLoanAmount + (existingLoanAmount * existingLoanInterest / 100);
            if (remainingAmount < 10000) {
                System.out.println("\nYou are close to finishing your existing loan. You may be eligible for a new loan.");
                report.append("Existing Loan Status: Near completion. Eligible for new loan.\n");
            } else {
                System.out.printf("\nYou still need to pay Rs.%.2f before taking another loan.\n", remainingAmount);
                report.append(String.format("Existing Loan Balance: Rs.%.2f still to be paid before next loan.\n", remainingAmount));
            }
        }

        try {
            String fileName = "D:/loan_report_" + name.replaceAll(" ", "_") + ".txt";
            FileWriter writer = new FileWriter(fileName);
            writer.write(report.toString());
            writer.close();
            System.out.println("\nReport saved as: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving the report.");
            e.printStackTrace();
        }

        sc.close();
    }
}
