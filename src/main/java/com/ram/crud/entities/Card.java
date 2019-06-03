package com.ram.crud.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringTokenizer;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiry_date")
    private String expiryDate;

    public Card() {
        //hibernate use
    }

    public Card(String bankName, String cardNumber, String expiryDate) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        if (cardNumber != null){
            return maskCardumber(cardNumber);
        }
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Card))
            return false;
        Card that = (Card) o;
        return Objects.equals(getBankName(), that.getBankName()) &&
               Objects.equals(getCardNumber(), that.getCardNumber()) &&
               Objects.equals(getExpiryDate(), that.getExpiryDate());
    }

    @Override public int hashCode() {
        return Objects.hash(getBankName(), getCardNumber(), getExpiryDate());
    }

    private String maskCardumber(String cardNumber) {
        StringTokenizer tokenizer = new StringTokenizer(cardNumber, "-");
        String firstToken = tokenizer.nextToken();
        String maskedTokens = "xxxx-xxxx-xxxx";
        return String.format("%s-%s", firstToken, maskedTokens);
    }

    @Override public String toString() {
        return "Card{" +
               ", bankName='" + bankName + '\'' +
               ", cardNumber='" + maskCardumber(cardNumber) + '\'' +
               '}';
    }
}
