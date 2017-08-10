package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.Shopkeeper;
import com.nisovin.shopkeepers.ShopkeepersPlugin;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondKeeperExists extends Condition {

    private Expression<String> keeperName;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        keeperName = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "keeper name exists";
    }

    @Override
    public boolean check(Event e) {
        ShopkeepersPlugin skp = ShopkeepersPlugin.getInstance();
        for (Shopkeeper sk :skp.getAllShopkeepers()) {
            if (sk.getName().equals(keeperName.getSingle(e))) {
                return true;
            }
        }
        return false;
    }
}