package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.ShopkeepersPlugin;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - Delete Shopkeeper")
@Description("Delete a shopkeeper.")
@Examples({"delete the shopkeeper named {shopkeepers::%player%}"})
public class EffDeleteKeeper extends Effect {

    private Expression<String> keeperName;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        keeperName = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "delete shopkeeper named " + keeperName.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        ShopkeepersPlugin.getInstance().deleteShopkeeper(ShopkeepersPlugin.getInstance().getShopkeeperByName(keeperName.getSingle(e)));
    }
}