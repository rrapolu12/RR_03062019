package com.ram.crud.repositories;

import com.ram.crud.entities.Card;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CardRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    CardRepository repository;

    //In-Memory Data
    Map<String, Card> bankDetailsMap = new HashMap<>();

    private String HSBC_BANK_CARD = "5601-2345-3446-5678";
    private String HSBC_BANK = "HSBC Canada";
    private String HSBC_EXPIRY_DATE = "Nov-2017";

    private String RBC_BANK_CARD = "4519-4532-4524-2456";
    private String RBC_BANK = "Royal Bank of  Canada";

    private String AMEX_BANK_CARD = "3786-7334-8965-345";
    private String AMEX_BANK = "American Express";

    @Before
    public void setup() {
        setupData();
    }

    @Test
    @DirtiesContext
    public void test_save_dummyRecord() {
        String bankName = "DummyBank";
        String cardNumber = "1-1-1-1";
        String expiryDate = "Nov-2019";
        Card card = buildBankDetails(bankName, cardNumber, expiryDate);
        //save record
        em.persist(card);
        //retrieve record
        Card retrievedRecord = repository.findByCardNumber(cardNumber);
        //assert now
        assertEquals(bankName, retrievedRecord.getBankName());
    }

    @Test
    @DirtiesContext
    public void test_findByCardNumber() {

        Card card = buildBankDetails(HSBC_BANK, HSBC_BANK_CARD, HSBC_EXPIRY_DATE);
        //save record
        em.persist(card);
        //retrieve record
        Card returned_HSBC = repository.findByCardNumber(HSBC_BANK_CARD);
        //assert now
        assertEquals(card.getBankName(), returned_HSBC.getBankName());
        assertEquals(card.getCardNumber(), returned_HSBC.getCardNumber());
        assertEquals(card.getExpiryDate(), returned_HSBC.getExpiryDate());
    }

    @Test
    @DirtiesContext
    public void test_delete_dummyRecord() {
        String bankName = "DummyBank";
        String cardNumber = "1-1-1-1";
        String expiryDate = "Nov-2019";
        Card card = buildBankDetails(bankName, cardNumber, expiryDate);
        //save record
        Card dummyRecord = em.persist(card);
        //delete record
        repository.delete(card);
        //retrieve record
        Card retrievedRecord = repository.findByCardNumber(cardNumber);
        //assert now
        assertNull(retrievedRecord);
    }

    @Test
    @DirtiesContext
    public void test_save_multiple_records() {
        Collection<Card> cardCollection = bankDetailsMap.values();
        //save/persist into DB
        List<Card> bankDetails = repository.saveAll(cardCollection);
        //retrieved after saved.
        for (Card bankDetail : bankDetails) {
            //assert now
            assertEquals(bankDetail, bankDetailsMap.get(bankDetail.getBankName()));
        }
    }

    private Map<String, Card> setupData() {
        Card card1 = buildBankDetails("HSBC Canada", "5601-2345-3446-5678", "Nov-2017");
        bankDetailsMap.put(HSBC_BANK, card1);
        Card card2 = buildBankDetails("Royal Bank of  Canada", "4519-4532-4524-2456", "Oct-2017");
        bankDetailsMap.put(RBC_BANK, card2);
        Card card3 = buildBankDetails("American Express", "3786-7334-8965-345", "Dec-2018");
        bankDetailsMap.put(AMEX_BANK, card3);

        return bankDetailsMap;
    }

    private Card buildBankDetails(String bankName, String cardNumber, String expiryDate) {
        Card card = new Card();
        card.setBankName(bankName);
        card.setCardNumber(cardNumber);
        card.setExpiryDate(expiryDate);
        return card;
    }

}
