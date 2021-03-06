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
package mage.sets.dragonsoftarkir;

import java.util.UUID;
import mage.abilities.effects.common.LookLibraryAndPickControllerEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.filter.Filter.ComparisonType;
import mage.filter.FilterCard;
import mage.filter.predicate.mageobject.CardTypePredicate;
import mage.filter.predicate.mageobject.ConvertedManaCostPredicate;

/**
 *
 * @author jeffwadsworth
 */
public class CollectedCompany extends CardImpl {

    private static final FilterCard filter = new FilterCard("up to two creature cards with converted mana cost 3 or less");

    static {
        filter.add(new CardTypePredicate(CardType.CREATURE));
        filter.add(new ConvertedManaCostPredicate(ComparisonType.LessThan, 4));
    }

    public CollectedCompany(UUID ownerId) {
        super(ownerId, 177, "Collected Company", Rarity.RARE, new CardType[]{CardType.INSTANT}, "{3}{G}");
        this.expansionSetCode = "DTK";

        // Look at the top six cards of your library. Put up to two creature cards with converted mana cost 3 or less from among them onto the battlefield. Put the rest on the bottom of your library in any order.
        this.getSpellAbility().addEffect(new LookLibraryAndPickControllerEffect(6, 2, filter, true));

    }

    public CollectedCompany(final CollectedCompany card) {
        super(card);
    }

    @Override
    public CollectedCompany copy() {
        return new CollectedCompany(this);
    }
}
