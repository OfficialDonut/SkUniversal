package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.nisovin.shopkeepers.ShopkeepersPlugin;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - Shopkeeper Location")
@Description("Returns the location of a shopkeeper")
@Examples({"send \"%the location of the shopkeeper named {shopkeepers::%player%}\""})
public class ExprKeeperLoc extends SimpleExpression<Location> {

    private Expression<String> keeperName;

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        keeperName = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "location of keeper named " + keeperName.getSingle(e);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        if (keeperName.getSingle(e) != null) {
            ShopkeepersPlugin skp = ShopkeepersPlugin.getInstance();
            return new Location[]{skp.getShopkeeperByName(keeperName.getSingle(e)).getLocation()};
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            ShopkeepersPlugin skp = ShopkeepersPlugin.getInstance();
            skp.getShopkeeperByName(keeperName.getSingle(e)).setLocation((Location) delta[0]);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Location.class);
        }
        return null;
    }
}