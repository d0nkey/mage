/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.odyssey;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.repository.CardRepository;
import mage.choices.Choice;
import mage.choices.ChoiceImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetPlayer;

/**
 *
 * @author Quercitron
 */
public class Predict extends CardImpl {

    public Predict(UUID ownerId) {
        super(ownerId, 94, "Predict", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{1}{U}");
        this.expansionSetCode = "ODY";

        this.color.setBlue(true);

        // Name a card, then target player puts the top card of his or her library into his or her graveyard. If that card is the named card, you draw two cards. Otherwise, you draw a card.
        this.getSpellAbility().addEffect(new PredictEffect());
        this.getSpellAbility().addTarget(new TargetPlayer());
    }

    public Predict(final Predict card) {
        super(card);
    }

    @Override
    public Predict copy() {
        return new Predict(this);
    }
}

class PredictEffect extends OneShotEffect {

    public PredictEffect() {
        super(Outcome.DrawCard);
        this.staticText = "Name a card, then target player puts the top card of his or her library into his or her graveyard. "
                + "If that card is the named card, you draw two cards. Otherwise, you draw a card.";
    }
    
    public PredictEffect(final PredictEffect effect) {
        super(effect);
    }

    @Override
    public PredictEffect copy() {
        return new PredictEffect(this);
    }
    
    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Player target = game.getPlayer(source.getFirstTarget());
        if (controller != null && target != null) {
            Choice cardChoice = new ChoiceImpl();
            cardChoice.setChoices(CardRepository.instance.getNames());
            cardChoice.clearChoice();
            while (!controller.choose(Outcome.Detriment, cardChoice, game)) {
                if (!controller.isInGame()) {
                    return false;
                }
            }
            String cardName = cardChoice.getChoice();
            game.informPlayers("Predict, named card: [" + cardName + "]");
            
            int amount = 1;
            
            Card card = target.getLibrary().removeFromTop(game);
            if (card != null) {
                if (target.moveCardToGraveyardWithInfo(card, source.getSourceId(), game, Zone.LIBRARY)) {
                    if (card.getName().equals(cardName)) {
                        amount = 2;
                    }
                }
            }
            
            controller.drawCards(amount, game);
            
            return true;
        }
        return false;
    }
    
}