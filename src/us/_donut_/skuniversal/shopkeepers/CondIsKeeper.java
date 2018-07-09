package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.ShopkeepersPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - Is Shopkeeper")
@Description("Checks if an entity is a shopkeeper.")
@Examples({"if event-entity is a shopkeeper:"})
public class CondIsKeeper extends Condition {

    private Expression<Entity> entity;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        entity = (Expression<Entity>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "entity " + entity.toString(e, b) + " is a shopkeeper";
    }

    @Override
    public boolean check(Event e) {
        return ShopkeepersPlugin.getInstance().isShopkeeper(entity.getSingle(e));
    }
}