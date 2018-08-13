package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.api.ShopkeepersAPI;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Shopkeepers - Shopkeeper at Location")
@Description("Returns the ID of the shopkeeper at a location.")
@Examples({"send \"%the ID of the shopkeeper at player's location\""})
public class ExprKeeperAtLoc extends SimpleExpression<Integer> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
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
        Shopkeeper shopkeeper = ShopkeepersAPI.getShopkeeperRegistry().getShopkeepersAtLocation(location.getSingle(e)).get(0);
        return new Integer[]{shopkeeper == null ? null : shopkeeper.getId()};
    }
}
