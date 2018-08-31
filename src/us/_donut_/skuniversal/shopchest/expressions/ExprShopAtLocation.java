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
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.shopchest.ShopChestHook.*;

@Name("ShopChest - Shop At Location")
@Description("Returns the id of the shop at a location.")
@Examples({"send \"%the id of the shop at player%\""})
public class ExprShopAtLocation extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprShopAtLocation.class, Number.class, ExpressionType.COMBINED, "[the] [ID of [the]] [ShopChest] shop at %location%");
    }

    private Expression<Location> location;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        location = (Expression<Location>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the shop at location " + location.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        Shop shop;
        if (location.getSingle(e) == null || (shop = shopUtils.getShop(location.getSingle(e))) == null) return null;
        return new Number[]{shop.getID()};
    }
}
