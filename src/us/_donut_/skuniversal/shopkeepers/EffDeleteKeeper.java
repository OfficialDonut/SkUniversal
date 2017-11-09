package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.Shopkeeper;
import com.nisovin.shopkeepers.ShopkeepersPlugin;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class EffDeleteKeeper extends Effect {

    private Expression<String> keeperName;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        keeperName = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "delete shopkeeper named " + keeperName.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (keeperName.getSingle(e) != null) {
            ShopkeepersPlugin skp = ShopkeepersPlugin.getInstance();
            Shopkeeper keeper = skp.getShopkeeperByName(keeperName.getSingle(e));
            skp.deleteShopkeeper(keeper);
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
        }
    }
}