package us._donut_.skuniversal.shopkeepers.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.shopkeepers.ShopkeepersHook.*;

@Name("Shopkeepers - Shopkeeper at Location")
@Description("Returns the ID of the shopkeeper at a location.")
@Examples({"send \"%the ID of the shopkeeper at player's location\""})
public class ExprKeeperAtLoc extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprKeeperAtLoc.class, Integer.class, ExpressionType.COMBINED, "[the] [ID of [the]] [shop]keeper at %location%");
    }

    private Expression<Location> location;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        location = (Expression<Location>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "ID of shopkeeper at " + location.toString(e, b);
    }

    @Override
    @Nullable
    protected Integer[] get(Event e) {
        Shopkeeper shopkeeper;
        if (location.getSingle(e) == null || (shopkeeper = shopkeeperRegistry.getShopkeepersAtLocation(location.getSingle(e)).get(0)) == null) return null;
        return new Integer[]{shopkeeper.getId()};
    }
}
