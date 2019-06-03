package com.ram.crud;

import static org.assertj.core.api.Assertions.assertThat;

import com.ram.crud.entities.Card;
import org.junit.Test;

public class CardUnitTest {
    
    @Test
    public void whenCalledGetName_thenCorrect() {
        Card card = new Card("DummyBank", "1-1-1-1","Nov-2019");
        
        assertThat(card.getBankName()).isEqualTo("DummyBank");
    }
    
    @Test
    public void whenCalledWithCardNumber() {
        Card card = new Card("DummyBank", "1-1-1-1","Nov-2019");
        assertThat(card.getCardNumber()).isEqualTo("1-1-1-1");
    }

}
