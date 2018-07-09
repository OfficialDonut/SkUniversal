package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.ShopkeepersPlugin;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - Does Shopkeeper Exist")
@Description("Checks if a shopkeeper with a certain name exists.")
@Examples({"if a shopkeeper named \"cool\" exists:"})
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
        return "keeper name " + keeperName.toString(e, b) + " exists";
    }

    @Override
    public boolean check(Event e) {
        return ShopkeepersPlugin.getInstance().getAllShopkeepers().stream().anyMatch(shopkeeper -> shopkeeper.getName().equals(keeperName.getSingle(e)));
    }
}