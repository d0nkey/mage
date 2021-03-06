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
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DamageMultiEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.constants.TimingRule;
import mage.target.common.TargetCreatureOrPlayerAmount;

/**
 *
 * @author cbt33
 */
public class VolleyOfBoulders extends CardImpl {

    public VolleyOfBoulders(UUID ownerId) {
        super(ownerId, 227, "Volley of Boulders", Rarity.RARE, new CardType[]{CardType.SORCERY}, "{8}{R}");
        this.expansionSetCode = "ODY";

        this.color.setRed(true);

        // Volley of Boulders deals 6 damage divided as you choose among any number of target creatures and/or players.
        this.getSpellAbility().addEffect(new DamageMultiEffect(6));
        this.getSpellAbility().addTarget(new TargetCreatureOrPlayerAmount(6));
        // Flashback {R}{R}{R}{R}{R}{R}
        this.addAbility(new FlashbackAbility(new ManaCostsImpl("{R}{R}{R}{R}{R}{R}"),TimingRule.SORCERY));
    }

    public VolleyOfBoulders(final VolleyOfBoulders card) {
        super(card);
    }

    @Override
    public VolleyOfBoulders copy() {
        return new VolleyOfBoulders(this);
    }
}
