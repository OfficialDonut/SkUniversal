package us._donut_.skuniversal.shopchest.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import de.epiceric.shopchest.shop.Shop;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.shopchest.ShopChestHook.*;

@Name("ShopChest - Shop Vendor")
@Description("Returns the vendor of a shop.")
@Examples({"send \"%the vendor of shop with id (shop at player)%\""})
public class ExprShopVendor extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprShopVendor.class, OfflinePlayer.class, ExpressionType.COMBINED, "[the] (vendor|owner) of [the] [ShopChest] shop [with ID] %number%");
    }

    private Expression<Number> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the vendor of the shop with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        Shop shop;
        if (id.getSingle(e) == null || (shop = getShop(id.getSingle(e).intValue())) == null) return null;
        return new OfflinePlayer[]{shop.getVendor()};
    }
}
