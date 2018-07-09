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

@Name("ShopChest - Shop Type")
@Description("Returns the type of a shop.")
@Examples({"send \"%the type of shop with id (shop at player)%\""})
public class ExprShopType extends SimpleExpression<String> {

    private Expression<Number> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the type of the shop with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Shop shop = ShopChestRegister.getShop(id.getSingle(e).intValue());
        return shop == null ? null : new String[]{shop.getShopType().name()};
    }
}
