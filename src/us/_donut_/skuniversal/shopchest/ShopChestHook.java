package us._donut_.skuniversal.shopchest;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import de.epiceric.shopchest.ShopChest;
import de.epiceric.shopchest.event.ShopBuySellEvent;
import de.epiceric.shopchest.event.ShopCreateEvent;
import de.epiceric.shopchest.event.ShopRemoveEvent;
import de.epiceric.shopchest.shop.Shop;
import de.epiceric.shopchest.utils.ShopUtils;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;

public class ShopChestHook {

    public static ShopUtils shopUtils = ShopChest.getInstance().getShopUtils();

    public static Shop getShop(int id) {
        for (Shop shop : shopUtils.getShops()) {
            if (shop.hasId() && shop.getID() == id) return shop;
        }
        return null;
    }

    static {
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

}
