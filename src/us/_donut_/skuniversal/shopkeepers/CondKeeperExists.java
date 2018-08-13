package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.api.ShopkeepersAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - Shopkeeper Exists")
@Description("Checks if a shopkeeper with a certain ID exists.")
@Examples({"if shopkeeper with id 1 exists:"})
public class CondKeeperExists extends Condition {

    private Expression<Integer> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<Integer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "keeper wid ID " + id.toString(e, b) + " exists";
    }

    @Override
    public boolean check(Event e) {
        return ShopkeepersAPI.getShopkeeperRegistry().getShopkeeperById(id.getSingle(e)) != null;
    }
}