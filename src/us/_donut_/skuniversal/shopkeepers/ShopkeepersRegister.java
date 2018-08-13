package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.nisovin.shopkeepers.api.events.PlayerCreateShopkeeperEvent;
import com.nisovin.shopkeepers.api.events.PlayerDeleteShopkeeperEvent;
import com.nisovin.shopkeepers.api.events.ShopkeeperTradeEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us._donut_.skuniversal.SkUniversalEvent;

public class ShopkeepersRegister {

    public ShopkeepersRegister() {
        //Conditions
        Skript.registerCondition(CondIsKeeper.class, "%entity% is [a] [shop]keeper");
        Skript.registerCondition(CondKeeperExists.class, "[a] [shop]keeper [with id] %integer% exists");

        //Effects
        Skript.registerEffect(EffDeleteKeeper.class, "(delete|remove) [the] [shop]keeper [with id] %integer%");

        //Expressions
        Skript.registerExpression(ExprKeepers.class, Integer.class, ExpressionType.SIMPLE, "[[the ]IDs of] [all] [the] [existing] [shop]keepers");
        Skript.registerExpression(ExprKeepersOfPlayer.class, Integer.class, ExpressionType.COMBINED, "[[the ]IDs of] [all] [the] [shop]keepers of %offlineplayer%", "[[all] [the ]IDs of] %offlineplayer%'s [shop]keepers");
        Skript.registerExpression(ExprKeeperEntity.class, Integer.class, ExpressionType.COMBINED, "[the] [shop]keeper ID of %entity%", "%entity%'s [shop]keeper ID");
        Skript.registerExpression(ExprKeeperAtLoc.class, Integer.class, ExpressionType.COMBINED, "[the] ID of [the] [shop]keeper at %location%");
        Skript.registerExpression(ExprKeeperLoc.class, Location.class, ExpressionType.COMBINED, "[the] loc[ation] of [the] [shop]keeper [with id] %integer%");
        Skript.registerExpression(ExprKeeperName.class, String.class, ExpressionType.COMBINED, "[the] name of [the] [shop]keeper [with id] %integer%");

        //Events
        Skript.registerEvent("Shopkeepers - Create", SkUniversalEvent.class, PlayerCreateShopkeeperEvent.class, "[shop]keeper creat(e|ion)")
                .description("Called when a shopkeeper is created.")
                .examples("on shopkeeper creation:", "\tsend \"%player% created a shopkeeper at %event-location%!\"");
        EventValues.registerEventValue(PlayerCreateShopkeeperEvent.class, Player.class, new Getter<Player, PlayerCreateShopkeeperEvent>() {
            public Player get(PlayerCreateShopkeeperEvent e) {
                return e.getShopCreationData().getCreator();
            }
        }, 0);
        EventValues.registerEventValue(PlayerCreateShopkeeperEvent.class, Location.class, new Getter<Location, PlayerCreateShopkeeperEvent>() {
            public Location get(PlayerCreateShopkeeperEvent e) {
                return e.getShopCreationData().getSpawnLocation();
            }
        }, 0);

        Skript.registerEvent("Shopkeepers - Delete", SkUniversalEvent.class, PlayerDeleteShopkeeperEvent.class, "[shop]keeper (delet(e|ion)|remov(e|al))")
                .description("Called when a shopkeeper is deleted.")
                .examples("on shopkeeper deletion:", "\tsend \"%player% deleted the shopkeeper at %event-location%!\"");
        EventValues.registerEventValue(PlayerDeleteShopkeeperEvent.class, Player.class, new Getter<Player, PlayerDeleteShopkeeperEvent>() {
            public Player get(PlayerDeleteShopkeeperEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerDeleteShopkeeperEvent.class, Location.class, new Getter<Location, PlayerDeleteShopkeeperEvent>() {
            public Location get(PlayerDeleteShopkeeperEvent e) {
                return e.getShopkeeper().getLocation();
            }
        }, 0);
        EventValues.registerEventValue(PlayerDeleteShopkeeperEvent.class, String.class, new Getter<String, PlayerDeleteShopkeeperEvent>() {
            public String get(PlayerDeleteShopkeeperEvent e) {
                return e.getShopkeeper().getName();
            }
        }, 0);
        EventValues.registerEventValue(PlayerDeleteShopkeeperEvent.class, Integer.class, new Getter<Integer, PlayerDeleteShopkeeperEvent>() {
            public Integer get(PlayerDeleteShopkeeperEvent e) {
                return e.getShopkeeper().getId();
            }
        }, 0);

        Skript.registerEvent("Shopkeepers - Trade Complete", SkUniversalEvent.class, ShopkeeperTradeEvent.class, "[shop]keeper trad(e|ing) complet(e|ion)")
                .description("Called when a shopkeeper trade is completed.")
                .examples("on shopkeeper trade completion:", "\tsend \"%player% traded the shopkeeper named %event-string% for %event-item%!\"");
        EventValues.registerEventValue(ShopkeeperTradeEvent.class, Player.class, new Getter<Player, ShopkeeperTradeEvent>() {
            public Player get(ShopkeeperTradeEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ShopkeeperTradeEvent.class, String.class, new Getter<String, ShopkeeperTradeEvent>() {
            public String get(ShopkeeperTradeEvent e) {
                return e.getShopkeeper().getName();
            }
        }, 0);
        EventValues.registerEventValue(ShopkeeperTradeEvent.class, ItemStack.class, new Getter<ItemStack, ShopkeeperTradeEvent>() {
            public ItemStack get(ShopkeeperTradeEvent e) {
                return e.getClickEvent().getCurrentItem();
            }
        }, 0);
        EventValues.registerEventValue(ShopkeeperTradeEvent.class, Integer.class, new Getter<Integer, ShopkeeperTradeEvent>() {
            public Integer get(ShopkeeperTradeEvent e) {
                return e.getShopkeeper().getId();
            }
        }, 0);
    }
}
