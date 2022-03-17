package domain.participant;

import domain.card.Card;
import java.util.List;

public final class Dealer extends Participant {

    public static final int CONVERT_POSITIVE = -1;
    private static final int MAX_CARD_SUM = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public int calculateIncome(List<Integer> playerIncomes) {
        int playerIncomeSum = playerIncomes.stream()
            .mapToInt(playerIncome -> playerIncome)
            .sum();
        return playerIncomeSum * CONVERT_POSITIVE;
    }

    public Card getFirstCard() {
        return cards.getCardByIndex(0);
    }

    @Override
    public boolean canHit() {
        return cards.calculateSum() <= MAX_CARD_SUM;
    }
}
