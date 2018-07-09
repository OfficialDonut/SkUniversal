package us._donut_.skuniversal.shopchest;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import de.epiceric.shopchest.ShopChest;
import de.epiceric.shopchest.shop.Shop;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("ShopChest - Shop At Location")
@Description("Returns the id of the shop at a location.")
@Examples({"send \"%the id of the shop at player%\""})
public class ExprShopAtLocation extends SimpleExpression<Number> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
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
        Shop shop = ShopChest.getInstance().getShopUtils().getShop(location.getSingle(e));
        return shop == null || !shop.hasId() ? null : new Number[]{shop.getID()};
    }
}
