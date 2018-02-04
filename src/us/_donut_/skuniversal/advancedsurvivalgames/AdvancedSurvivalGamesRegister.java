package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import e.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us._donut_.skuniversal.SkUniversalEvent;

public class AdvancedSurvivalGamesRegister {

    public AdvancedSurvivalGamesRegister() {
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
        Skript.registerEvent("AdvancedSurvivalGames - Player Death", SkUniversalEvent.class, SGPlayerKillEvent.class, "[advanced] (survival games|sg) [player] death")
				.description("Called when a player dies.\n\n" +
						"**Event Expressions:**\n" +
						"`[the] [advanced] (survival games|sg) victim`\n" +
						"`[the] [advanced] (survival games|sg) (attacker|killer)`")
				.examples("on survival games player death:", "\tbroadcast \"%survival games victim% has killed by %survival games attacker%!\"");
        Skript.registerEvent("AdvancedSurvivalGames - Game Start", SkUniversalEvent.class, SGGameStartEvent.class, "[advanced] (survival games|sg) game (start|begin)")
				.description("Called when a game starts.");
        Skript.registerEvent("AdvancedSurvivalGames - Game End", SkUniversalEvent.class, SGGameEndEvent.class, "[advanced] (survival games|sg) game (end|finish)")
				.description("Called when a game ends.")
				.examples("on survival games game end:", "\tsend \"Congratulations!\" to player #The winner");
        EventValues.registerEventValue(SGGameEndEvent.class, Player.class, new Getter<Player, SGGameEndEvent>() {
            public Player get(SGGameEndEvent e) {
                return e.getWinner().getPlayer().getPlayer();
            }
        }, 0);
        Skript.registerEvent("AdvancedSurvivalGames - Item Purchase", SkUniversalEvent.class, SGItemPurchaseEvent.class, "[advanced] (survival games|sg) item purchase")
				.description("Called when a player buys an item.")
				.examples("on survival games item purchase:", "\tbroadcast \"%player% bought %event-item% for %event-number%!\"");
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
        Skript.registerEvent("AdvancedSurvivalGames - Kit Purchase", SkUniversalEvent.class, SGKitPurchaseEvent.class, "[advanced] (survival games|sg) kit purchase")
				.description("Called when a player buys a kit.")
                .examples("on survival games kit purchase:", "\tbroadcast \"%player% bought a kit!\"");
        EventValues.registerEventValue(SGKitPurchaseEvent.class, Player.class, new Getter<Player, SGKitPurchaseEvent>() {
            public Player get(SGKitPurchaseEvent e) {
                return e.getBuyer().getPlayer().getPlayer();
            }
        }, 0);
    }
}