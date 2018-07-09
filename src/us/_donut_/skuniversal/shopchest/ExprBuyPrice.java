package us._donut_.skuniversal.shopchest;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import de.epiceric.shopchest.shop.Shop;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("ShopChest - Shop Buy Price")
@Description("Returns the buy price of a shop.")
@Examples({"send \"%the buy price of shop with id (shop at player)%\""})
public class ExprBuyPrice extends SimpleExpression<Number> {

    private Expression<Number> id;

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
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the buy price of the shop with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        Shop shop = ShopChestRegister.getShop(id.getSingle(e).intValue());
        return shop == null ? null : new Number[]{shop.getBuyPrice()};
    }
}