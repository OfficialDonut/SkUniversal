package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import e.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AdvancedSurvivalGamesRegister {
    public static void registerAdvancedSurvivalGames() {
        if (Bukkit.getServer().getPluginManager().getPlugin("AdvancedSurvivalGames") != null) {

            //Conditions
            Skript.registerCondition(CondSpectator.class, "%offlineplayer% is spectating [a[n]] [advanced] (survival games|sg) [game]");
            Skript.registerCondition(CondTeam.class, "%offlineplayer% has [a[n]] [advanced] (survival games|sg) team[mate]");

            //Expressions
            Skript.registerExpression(ExprSGVictim.class, Player.class, ExpressionType.SIMPLE, "[the] [advanced] (survival games|sg) victim");
            Skript.registerExpression(ExprSGAttacker.class, Player.class, ExpressionType.SIMPLE, "[the] [advanced] (survival games|sg) (attacker|killer)");
            Skript.registerExpression(ExprCurrentArena.class, String.class, ExpressionType.SIMPLE, "[the] [advanced] (survival games|sg) current arena");
            Skript.registerExpression(ExprAlivePlayers.class, Player.class, ExpressionType.SIMPLE, "[the] [advanced] (survival games|sg) alive players");
            Skript.registerExpression(ExprSpectators.class, Player.class, ExpressionType.SIMPLE, "[the] [advanced] (survival games|sg) spectators");
            Skript.registerExpression(ExprTopPlayers.class, String.class, ExpressionType.SIMPLE, "[the] [advanced] (survival games|sg) top players");
            Skript.registerExpression(ExprTeammate.class, Player.class, ExpressionType.COMBINED, "[the] [advanced] (survival games|sg) teammate of %offlineplayer%", "%offlineplayer%'s [advanced] (survival games|sg) teammate");
            Skript.registerExpression(ExprBounty.class, Number.class, ExpressionType.COMBINED, "[the] [advanced] (survival games|sg) bounty of %offlineplayer%", "%offlineplayer%'s [advanced] (survival games|sg) bounty");
            Skript.registerExpression(ExprStage.class, String.class, ExpressionType.SIMPLE, "[the] [advanced] (survival games|sg) [current] stage");

            //Events
            Skript.registerEvent("Advanced Survival Games Death", SimpleEvent.class, SGPlayerKillEvent.class, "[on] [advanced] (survival games|sg) [player] death");
            Skript.registerEvent("Advanced Survival Games Game Start", SimpleEvent.class, SGGameStartEvent.class, "[on] [advanced] (survival games|sg) game (start|begin)");
            Skript.registerEvent("Advanced Survival Games Game End", SimpleEvent.class, SGGameEndEvent.class, "[on] [advanced] (survival games|sg) game (end|finish)");
            EventValues.registerEventValue(SGGameEndEvent.class, Player.class, new Getter<Player, SGGameEndEvent>() {
                public Player get(SGGameEndEvent e) {
                    return e.getWinner().getPlayer().getPlayer();
                }
            }, 0);
            Skript.registerEvent("Advanced Survival Games Item Purchase", SimpleEvent.class, SGItemPurchaseEvent.class, "[on] [advanced] (survival games|sg) item purchase");
            EventValues.registerEventValue(SGItemPurchaseEvent.class, Player.class, new Getter<Player, SGItemPurchaseEvent>() {
                public Player get(SGItemPurchaseEvent e) {
                    return e.getBuyer().getPlayer().getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(SGItemPurchaseEvent.class, ItemStack.class, new Getter<ItemStack, SGItemPurchaseEvent>() {
                public ItemStack get(SGItemPurchaseEvent e) {
                    return e.getItem();
                }
            }, 0);
            EventValues.registerEventValue(SGItemPurchaseEvent.class, Number.class, new Getter<Number, SGItemPurchaseEvent>() {
                public Number get(SGItemPurchaseEvent e) {
                    return e.getItemPrice();
                }
            }, 0);
            Skript.registerEvent("Advanced Survival Games Kit Purchase", SimpleEvent.class, SGKitPurchaseEvent.class, "[on] [advanced] (survival games|sg) kit purchase");
            EventValues.registerEventValue(SGKitPurchaseEvent.class, Player.class, new Getter<Player, SGKitPurchaseEvent>() {
                public Player get(SGKitPurchaseEvent e) {
                    return e.getBuyer().getPlayer().getPlayer();
                }
            }, 0);
        }
    }
}