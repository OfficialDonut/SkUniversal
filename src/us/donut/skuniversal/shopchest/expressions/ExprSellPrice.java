package us.donut.skuniversal.shopchest.expressions;

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
import org.bukkit.event.Event;
import us.donut.skuniversal.shopchest.ShopChestHook;

import javax.annotation.Nullable;

@Name("ShopChest - Shop Sell Price")
@Description("Returns the sell price of a shop.")
@Examples({"send \"%the sell price of shop with id (shop at player)%\""})
public class ExprSellPrice extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprSellPrice.class, Number.class, ExpressionType.COMBINED, "[the] sell price of [the] [ShopChest] shop [with ID] %number%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the sell price of the shop with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        Shop shop;
        if (id.getSingle(e) == null || (shop = ShopChestHook.getShop(id.getSingle(e).intValue())) == null) return null;
        return new Number[]{shop.getSellPrice()};
    }
}
