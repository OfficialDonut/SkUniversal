package us.donut.skuniversal.shopkeepers.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.shopkeepers.ShopkeepersHook.*;

@Name("Shopkeepers - Is Shopkeeper")
@Description("Checks if an entity is a shopkeeper.")
@Examples({"if event-entity is a shopkeeper:"})
public class CondIsKeeper extends Condition {

    static {
        Skript.registerCondition(CondIsKeeper.class,
                "%entity% is [a] [shop]keeper",
                "%entity% is(n't| not) [a] [shop]keeper");
    }

    private Expression<Entity> entity;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        entity = (Expression<Entity>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "entity " + entity.toString(e, b) + " is a shopkeeper";
    }

    @Override
    public boolean check(Event e) {
        if (entity.getSingle(e) == null) return isNegated();
        return shopkeeperRegistry.isShopkeeper(entity.getSingle(e)) != isNegated();
    }
}