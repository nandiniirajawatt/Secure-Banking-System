package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.*;
import java.util.List;

@SpringBootApplication
public class SecureBankingSystemApplication {
    public static void main(String[] args) {
        System.setProperty("server.port", "9090");
        SpringApplication.run(SecureBankingSystemApplication.class, args);
        System.out.println("🚀 Core Banking System running on http://localhost:9090");
    }
}

@Entity
@Table(name = "accounts")
class Account {
    @Id
    private int id;
    private String holderName;
    private double balance;

    public Account() {}

    public Account(int id, String holderName, double balance) {
        this.id = id;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getId() { return id; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}

interface AccountRepository extends JpaRepository<Account, Integer> {}

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
class BankingController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/sorted")
    public List<Account> getAllAccountsSorted() {
        List<Account> accountList = accountRepository.findAll();
        Account[] arr = accountList.toArray(new Account[0]);
        int n = arr.length;
        
        // Custom DSA: Insertion Sort
        for (int i = 1; i < n; ++i) {
            Account key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getBalance() < key.getBalance()) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return java.util.Arrays.asList(arr);
    }

    @PostMapping
    public String createAccount(@RequestBody Account newAccount) {
        if(newAccount.getHolderName() == null || newAccount.getHolderName().trim().isEmpty()) {
            return "❌ Error: Account holder name cannot be blank!";
        }
        if(newAccount.getBalance() < 500) {
            return "❌ Error: Minimum initial deposit must be at least ₹500!";
        }
        accountRepository.save(newAccount);
        return "✅ Account successfully initialized for " + newAccount.getHolderName();
    }

    @GetMapping("/{id}")
    public Account searchAccountById(@PathVariable int id) {
        List<Account> list = accountRepository.findAll(); 
        Account[] arr = list.toArray(new Account[0]);
        
        // Custom DSA: Binary Search
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].getId() == id) return arr[mid];
            if (arr[mid].getId() < id) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable int id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return "✅ Account registry completely purged from database rows.";
        }
        return "❌ Error: Account ID does not exist!";
    }
}
