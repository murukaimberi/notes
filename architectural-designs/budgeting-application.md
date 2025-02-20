### **Updated Budgeting Application Design with Expense Management**  

---

## **Problem Statement (Revised)**  
Managing daily finances is often complex for small households. Many struggle to track expenses, create budgets, and reconcile bank statements. Existing solutions are either too rigid or require manual data entry, leading to inefficiencies.  

This budgeting application aims to simplify personal finance management by enabling users to:  
- Enter and manage bank details.  
- Manually upload and parse bank statements.  
- Automatically categorize transactions.  
- Set up budgets and track expenses.  
- Manage daily expenses with categorized tracking.  

By integrating expense management, users can directly record spending and compare it against their budgets.  

---

## **Updated Domain Design**  

### **Core Entities (with Expense Management)**  

1. **User**  
   - Attributes: `id`, `name`, `email`, `password`, `createdAt`  
   - Relationships: Owns multiple **Budgets**, **Bank Accounts**, **Transactions**, and **Expenses**.  

2. **Bank**  
   - Attributes: `id`, `name`, `swiftCode`, `createdAt`  
   - Relationships: Has multiple **Bank Accounts**.  

3. **Bank Account**  
   - Attributes: `id`, `accountNumber`, `bankId`, `userId`, `balance`, `createdAt`  
   - Relationships: Belongs to a **User** and a **Bank**.  

4. **Bank Statement**  
   - Attributes: `id`, `bankAccountId`, `uploadedByUserId`, `filePath`, `uploadedAt`, `status`  
   - Relationships: Belongs to a **Bank Account**. Can be uploaded manually or retrieved via API.  

5. **Transaction**  
   - Attributes: `id`, `bankAccountId`, `amount`, `category`, `transactionDate`, `description`, `createdAt`  
   - Relationships: Linked to a **Bank Account**. May be imported from a **Bank Statement**.  

6. **Expense**  
   - Attributes: `id`, `userId`, `amount`, `category`, `paymentMethod`, `date`, `description`, `createdAt`  
   - Relationships: Belongs to a **User** and may be linked to a **Budget Item**.  

7. **Budget**  
   - Attributes: `id`, `userId`, `name`, `amountAllocated`, `startDate`, `endDate`, `createdAt`  
   - Relationships: Belongs to a **User** and contains multiple **Budget Items**.  

8. **Budget Item**  
   - Attributes: `id`, `budgetId`, `category`, `amountAllocated`, `amountSpent`, `createdAt`  
   - Relationships: Belongs to a **Budget** and is associated with multiple **Expenses**.  

---

## **C4 Model Code (Using Structurizr DSL - Similar to Robert Martin’s Approach)**  

```dsl
workspace {
  
  model {
    
    user = person "User" "Manages personal finances"
    
    webApp = softwareSystem "Budgeting Application" "Tracks budgets, transactions, and expenses"
    
    user -> webApp "Uses"
    
    backend = container "Spring Boot API" "Handles business logic" "Java & Spring Boot"
    frontend = container "Angular Frontend" "User Interface" "Angular"
    db = container "Database" "Stores users, transactions, and budgets" "PostgreSQL"
    fileUpload = container "File Upload Service" "Handles statement uploads" "Java"
    bankAPI = external_system "Bank API" "Fetches bank transactions"  
    
    webApp -> frontend "Provides UI"
    frontend -> backend "Sends requests"
    backend -> db "Reads/Writes data"
    backend -> fileUpload "Processes file uploads"
    backend -> bankAPI "Fetches transactions"
    
    component ExpenseService {
        description "Handles user expenses and categorization"
        backend -> ExpenseService "Manages expenses"
        ExpenseService -> db "Stores expenses"
    }

  }
  
  views {
    systemContext SystemContext {
      include *
      autolayout lr
    }
    container ContainerDiagram {
      include *
      autolayout lr
    }
  }
}
```

---

## **Updated Use Case Diagrams (With Expense Management)**  
### **Use Case 1: Bank Account & Statement Management**  
**Actors:** User  
**Use Cases:**  
- Add a bank  
- Add a bank account  
- View bank accounts  
- Upload bank statement (Manual - CSV/PDF)  
- Retrieve bank transactions (Automated API)  
- Parse and categorize transactions  
- View uploaded bank statements  

### **Use Case 2: Budget & Expense Management**  
**Actors:** User  
**Use Cases:**  
- Create a budget  
- Add budget items  
- Track budget progress  
- Adjust budget  
- Record a new expense  
- Categorize an expense  
- Compare expenses with the budget  

---

## **Updated User Stories (With Expense Management)**  

### **Bank Account Management**  
1. *As a user, I want to add my bank and account details so that I can track my transactions.*  
2. *As a user, I want to view my linked bank accounts so that I can manage them easily.*  

### **Bank Statement Upload**  
3. *As a user, I want to manually upload my bank statement in CSV or PDF format so that I can track my transactions.*  
4. *As a user, I want the system to parse uploaded bank statements and extract transaction details.*  
5. *As a user, I want the system to categorize transactions from my bank statement automatically to reduce manual effort.*  
6. *As a user, I want to review and edit parsed transactions before they are stored in my records.*  

### **Budget Management**  
7. *As a user, I want to create a monthly budget so that I can track my spending against my income.*  
8. *As a user, I want to allocate specific amounts to different spending categories so that I can control my finances.*  
9. *As a user, I want to compare my actual spending against my budget to see if I am overspending.*  

### **Expense Management**  
10. *As a user, I want to manually add an expense so that I can track cash payments.*  
11. *As a user, I want to categorize an expense so that I can analyze my spending habits.*  
12. *As a user, I want to see a report comparing my expenses with my budget so that I can adjust my spending.*  
13. *As a user, I want to filter expenses by category and date so that I can review past spending.*  
14. *As a user, I want to delete or edit an expense so that I can correct mistakes in my records.*  

---

This version includes **Structurizr DSL** for diagrams, making it more in line with Robert Martin’s style. Let me know if you need modifications or more details! 🚀
```
workspace {
  
  model {
    
    user = person "User" "Manages personal finances"
    
    webApp = softwareSystem "Budgeting Application" "Tracks budgets, transactions, and expenses"
    
    user -> webApp "Uses"
    
    backend = container "Spring Boot API" "Handles business logic" "Java & Spring Boot"
    frontend = container "Angular Frontend" "User Interface" "Angular"
    db = container "Database" "Stores users, transactions, and budgets" "PostgreSQL"
    fileUpload = container "File Upload Service" "Handles statement uploads" "Java"
    bankAPI = external_system "Bank API" "Fetches bank transactions"  
    
    webApp -> frontend "Provides UI"
    frontend -> backend "Sends requests"
    backend -> db "Reads/Writes data"
    backend -> fileUpload "Processes file uploads"
    backend -> bankAPI "Fetches transactions"
    
    component ExpenseService {
        description "Handles user expenses and categorization"
        backend -> ExpenseService "Manages expenses"
        ExpenseService -> db "Stores expenses"
    }

  }
  
  views {
    systemContext SystemContext {
      include *
      autolayout lr
    }
    container ContainerDiagram {
      include *
      autolayout lr
    }
  }
}
```

Here are some name ideas using only **Shona** and **Swahili**, while keeping the themes of robustness, reliability, and financial management:

### **Shona-Inspired Names:**
1. **MariSure** – *"Mari"* means *money* in Shona, and *Sure* represents reliability.
2. **Chengetedza** – Means *to safeguard/preserve* in Shona, perfect for financial security.
3. **RugareBudget** – *"Rugare"* means *peace/prosperity* in Shona, aligning with financial stability.
4. **TengesaPlan** – *"Tengesa"* means *to manage well* in Shona, suitable for budgeting.
5. **HupfumiTrack** – *"Hupfumi"* means *wealth* in Shona, emphasizing financial growth.

### **Swahili-Inspired Names:**
6. **ImaraBudget** – *"Imara"* means *strong/steady* in Swahili, highlighting reliability.
7. **TunaPesa** – *"Tuna"* means *we have*, and *Pesa* means *money* in Swahili.
8. **BajetiSalama** – *"Bajeti"* means *budget*, and *Salama* means *safe/secure*.
9. **AkibaPlan** – *"Akiba"* means *savings/reserve* in Swahili, ideal for a budgeting app.
10. **MaliThabiti** – *"Mali"* means *wealth/assets*, and *Thabiti* means *firm/strong*.

Would you like a combination of both languages in a single name? Or do any of these stand out to you? 😊

Here are a few name ideas for your budgeting application with African roots, reflecting robustness and reliability:

1. **ImaliGuard** – *"Imali"* means *money* in Zulu/Xhosa, and *Guard* signifies security and reliability.
2. **Thamani** – *"Thamani"* means *value/worth* in Swahili, emphasizing financial responsibility.
3. **PesaFlow** – *"Pesa"* is *money* in Swahili, and *Flow* suggests smooth financial management.
4. **Zamani Budget** – *"Zamani"* means *timeless* in Swahili, reflecting longevity and dependability.
5. **MaliTrack** – *"Mali"* means *wealth/assets* in Swahili, and *Track* conveys control and organization.
6. **Isibalo** – *"Isibalo"* means *calculation/accounting* in Zulu, reinforcing precision.
7. **AmaniFin** – *"Amani"* means *peace* in Swahili, symbolizing financial stability.
8. **TengaPlan** – *"Tenga"* means *to allocate/save* in Swahili, fitting for budgeting.
9. **HelaSync** – *"Hela"* means *money* in Swahili, and *Sync* represents integration with banking.
10. **Ndoto Finance** – *"Ndoto"* means *dream* in Swahili, ideal for financial aspirations.

Would you like something more abstract or a combination of different African languages?