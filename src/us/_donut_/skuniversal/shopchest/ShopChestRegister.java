package us._donut_.skuniversal.shopchest;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import de.epiceric.shopchest.ShopChest;
import de.epiceric.shopchest.event.ShopBuySellEvent;
import de.epiceric.shopchest.event.ShopCreateEvent;
import de.epiceric.shopchest.event.ShopRemoveEvent;
import de.epiceric.shopchest.shop.Shop;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us._donut_.skuniversal.SkUniversalEvent;

public class ShopChestRegister {

    public ShopChestRegister() {
        //Expressions
        Skript.registerExpression(ExprShopLimit.class, Number.class, ExpressionType.COMBINED, "[the] [ShopChest] shop limit of %player%", "%player%'s [ShopChest] shop limit");
        Skript.registerExpression(ExprAllShops.class, Number.class, ExpressionType.SIMPLE, "[[the] ids of] [all [of]] [the] [ShopChest] shops");
        Skript.registerExpression(ExprPlayerShops.class, Number.class, ExpressionType.COMBINED, "[[the] ids of] [all [of]] [the] [ShopChest] shops of %offlineplayer%", "[[the] ids of] [all [of]] %offlineplayer%'s [ShopChest] shops");
        Skript.registerExpression(ExprShopAtLocation.class, Number.class, ExpressionType.COMBINED, "[the] [id of [the]] [ShopChest] shop at %location%");
        Skript.registerExpression(ExprShopVendor.class, OfflinePlayer.class, ExpressionType.COMBINED, "[the] (vendor|owner) of [the] [ShopChest] shop [with id] %number%");
        Skript.registerExpression(ExprShopProduct.class, ItemStack.class, ExpressionType.COMBINED, "[the] (product|item) of [the] [ShopChest] shop [with id] %number%");
        Skript.registerExpression(ExprBuyPrice.class, Number.class, ExpressionType.COMBINED, "[the] buy price of [the] [ShopChest] shop [with id] %number%");
        Skript.registerExpression(ExprSellPrice.class, Number.class, ExpressionType.COMBINED, "[the] sell price of [the] [ShopChest] shop [with id] %number%");
        Skript.registerExpression(ExprShopLocation.class, Location.class, ExpressionType.COMBINED, "[the] loc[ation] of [the] [ShopChest] shop [with id] %number%");
        Skript.registerExpression(ExprShopType.class, String.class, ExpressionType.COMBINED, "[the] type of [the] [ShopChest] shop [with id] %number%");

        //Events
        Skript.registerEvent("ShopChest - Shop Creation", SkUniversalEvent.class, ShopCreateEvent.class, "[ShopChest] shop creat(e|ion)")
                .description("Called when a shop is created.")
                .examples("on shop creation:", "\tbroadcast \"%player% created a shop!\"");
        EventValues.registerEventValue(ShopCreateEvent.class, Integer.class, new Getter<Integer, ShopCreateEvent>() {
            public Integer get(ShopCreateEvent e) {
                return e.getShop().getID();
            }
        }, 0);
        EventValues.registerEventValue(ShopCreateEvent.class, Player.class, new Getter<Player, ShopCreateEvent>() {
            public Player get(ShopCreateEvent e) {
                return e.getPlayer();
            }
        }, 0);
        Skript.registerEvent("ShopChest - Shop Removal", SkUniversalEvent.class, ShopRemoveEvent.class, "[ShopChest] shop remov(e|al)")
                .description("Called when a shop is removed.")
                .examples("on shop removal:", "\tbroadcast \"%player% removed a shop!\"");
        EventValues.registerEventValue(ShopRemoveEvent.class, Integer.class, new Getter<Integer, ShopRemoveEvent>() {
            public Integer get(ShopRemoveEvent e) {
                return e.getShop().getID();
            }
        }, 0);
        EventValues.registerEventValue(ShopCreateEvent.class, Player.class, new Getter<Player, ShopCreateEvent>() {
            public Player get(ShopCreateEvent e) {
                return e.getPlayer();
            }
        }, 0);
        Skript.registerEvent("ShopChest - Transaction", SkUniversalEvent.class, ShopBuySellEvent.class, "[ShopChest] shop transaction")
                .description("Called when a player buys/sells something from a shop.")
                .examples("on shop transaction:", "\tbroadcast \"%player% used a shop!\"");
        EventValues.registerEventValue(ShopBuySellEvent.class, Integer.class, new Getter<Integer, ShopBuySellEvent>() {
            public Integer get(ShopBuySellEvent e) {
                return e.getShop().getID();
            }
        }, 0);
        EventValues.registerEventValue(ShopBuySellEvent.class, Player.class, new Getter<Player, ShopBuySellEvent>() {
            public Player get(ShopBuySellEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ShopBuySellEvent.class, String.class, new Getter<String, ShopBuySellEvent>() {
            public String get(ShopBuySellEvent e) {
                return e.getType().name();
            }
        }, 0);
    }

    static Shop getShop(int id) {
        for (Shop shop : ShopChest.getInstance().getShopUtils().getShops()) {
            if (shop.hasId() && shop.getID() == id) {
                return shop;
            }
        }
        return null;
    }
}
