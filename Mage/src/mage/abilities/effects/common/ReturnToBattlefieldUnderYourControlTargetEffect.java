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

package mage.abilities.effects.common;

import java.util.UUID;
import mage.MageObject;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Card;
import mage.game.Game;
import mage.players.Player;
import mage.util.CardUtil;

/**
 *
 * @author noxx
 */
public class ReturnToBattlefieldUnderYourControlTargetEffect extends OneShotEffect {

    private boolean fromExileZone;
    
    public ReturnToBattlefieldUnderYourControlTargetEffect() {
        this(false);
    }
    
    /**
     * 
     * @param fromExileZone - the card will only be retunred if it's still in the sour obect specific exile zone
     */
    public ReturnToBattlefieldUnderYourControlTargetEffect(boolean fromExileZone) {
        super(Outcome.Benefit);
        staticText = "return that card to the battlefield under your control";
        this.fromExileZone = fromExileZone;
    }

    public ReturnToBattlefieldUnderYourControlTargetEffect(final ReturnToBattlefieldUnderYourControlTargetEffect effect) {
        super(effect);
        this.fromExileZone = effect.fromExileZone;
    }

    @Override
    public ReturnToBattlefieldUnderYourControlTargetEffect copy() {
        return new ReturnToBattlefieldUnderYourControlTargetEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        MageObject sourceObject = source.getSourceObject(game);
        if (controller != null && sourceObject != null) {            
            Card card = null;        
            if (fromExileZone) {
                UUID exilZoneId = CardUtil.getObjectExileZoneId(game, sourceObject);
                if (exilZoneId != null) {
                card = game.getExile().getExileZone(exilZoneId).get(getTargetPointer().getFirst(game, source), game);
                }
            } else {
                card = game.getCard(targetPointer.getFirst(game, source));
            }
            if (card != null) {
                Zone currentZone = game.getState().getZone(card.getId());
                controller.putOntoBattlefieldWithInfo(card, game, currentZone, source.getSourceId());
            }
            return true;
        }
        return false;
    }
}
