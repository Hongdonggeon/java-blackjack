package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import utils.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.compare;

public abstract class Participant implements Comparable<Participant> {

    protected static final int MAX_SCORE = 21;

    protected final Cards cards;
    protected final Name name;

    public Participant(String name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = new Name(name);
    }

    public abstract boolean canDrawCard();

    public void hit(Card card) {
        if (!canDrawCard()) {
            throw new IllegalStateException(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
        }
        cards.addCard(card);
    }

    public void hitInitialTurn(List<Card> cards) {
        this.cards.addCards(cards);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    private boolean isOverMaxScore() {
        return calculateScore() > MAX_SCORE;
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    @Override
    public int compareTo(Participant participant) {
        if (participant.isOverMaxScore()) {
            return 1;
        }
        if (isOverMaxScore()) {
            return -1;
        }

        return compare(calculateScore(), participant.calculateScore());
    }
}
