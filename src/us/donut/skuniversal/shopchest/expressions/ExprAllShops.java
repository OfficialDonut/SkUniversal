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
import java.util.Arrays;

@Name("ShopChest - All Shops")
@Description("Returns the IDs of all shops.")
@Examples({"send \"%all shops%\""})
public class ExprAllShops extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprAllShops.class, Number.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [IDs of all [the]] [ShopChest] shops");
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "ids of all shops";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return Arrays.stream(ShopChestHook.shopUtils.getShops()).map(Shop::getID).toArray(Number[]::new);
    }
}