package us.donut.skuniversal.shopkeepers.expressions;

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

import static us.donut.skuniversal.shopkeepers.ShopkeepersHook.*;

@Name("Shopkeepers - Shopkeeper Location")
@Description("Returns the location of a shopkeeper.")
@Examples({"send \"%the location of the shopkeeper with id 1\""})
public class ExprKeeperLoc extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ExprKeeperLoc.class, Location.class, ExpressionType.COMBINED, "[the] loc[ation] of [the] [shop]keeper [with ID] %integer%");
    }

    private Expression<Integer> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<Integer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "location of shopkeeper with ID " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        Shopkeeper shopkeeper;
        if (id.getSingle(e) == null || (shopkeeper = shopkeeperRegistry.getShopkeeperById(id.getSingle(e))) == null) return null;
        return new Location[]{shopkeeper.getLocation()};
    }
}