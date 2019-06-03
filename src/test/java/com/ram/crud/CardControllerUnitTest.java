package com.ram.crud;

import com.ram.crud.controllers.CardController;
import com.ram.crud.entities.Card;
import com.ram.crud.repositories.CardRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardControllerUnitTest {

    private static CardController cardController;
    private static CardRepository mockedCardRepository;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeClass
    public static void setUpUserControllerInstance() {
        mockedCardRepository = mock(CardRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        cardController = new CardController(mockedCardRepository);
    }

    @Test
    public void whenCalledshowSignUpForm_thenCorrect() {
        Card card = new Card("DummyBank", "1-1-1-1","Nov-2019");

        assertThat(cardController.showSignUpForm(card)).isEqualTo("add-card");
    }
    
    @Test
    public void whenCalledaddUserAndValidUser_thenCorrect() {
        Card card = new Card("DummyBank", "1-1-1-1","Nov-2019");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(cardController.addUser(card, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledaddUserAndInValidUser_thenCorrect() {
        Card card = new Card("DummyBank", "1-1-1-1","Nov-2019");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(cardController.addUser(card, mockedBindingResult, mockedModel)).isEqualTo("add-card");
    }
}
