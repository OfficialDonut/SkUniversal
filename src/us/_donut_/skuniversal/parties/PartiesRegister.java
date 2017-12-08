package us._donut_.skuniversal.parties;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PartiesRegister {

    public static void registerParties() {
        //Conditions
        Skript.registerCondition(CondPartyExists.class, "party [(named|with name)] %string% exists");
        Skript.registerCondition(CondInParty.class, "%offlineplayer% is in [a] party");

        //Effects
        Skript.registerEffect(EffCreateParty.class, "(create|make) [a] party [(named|with name)] %string% with leader %player%");
        Skript.registerEffect(EffRemoveParty.class, "(delete|remove) [the] party [(named|with name)] %string%");
        Skript.registerEffect(EffAddToParty.class, "add %player% to [the] party [(named|with name)] %string%");

        //Expressions
        Skript.registerExpression(ExprLeader.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] leader of [the] party [(named|with name)] %string%");
        Skript.registerExpression(ExprPartyOfPlayer.class,String.class, ExpressionType.COMBINED, "[the] party [name] of %offlineplayer%", "%offlineplayer%'s party [name]");
        Skript.registerExpression(ExprMembers.class, OfflinePlayer.class, ExpressionType.COMBINED, "[the] members of [the] party [(named|with name)] %string%", "[the] party [(named|with name)] %string%'s members");
        Skript.registerExpression(ExprOnlineMembers.class, Player.class, ExpressionType.COMBINED, "[the] online members of [the] party [(named|with name)] %string%", "[the] party [(named|with name)] %string%'s online members");
        Skript.registerExpression(ExprKills.class, Number.class, ExpressionType.COMBINED, "[the] [(number|amount of)] kills of [the] party [(named|with name)] %string%", "[the] party [(named|with name)] %string%'s [(number|amount of)] kills");
        Skript.registerExpression(ExprHome.class, Location.class, ExpressionType.COMBINED, "[the] home [loc[ation]] of [the] party [(named|with name)] %string%", "[the] party [(named|with name)] %string%'s home [loc[ation]]");
    }
}
