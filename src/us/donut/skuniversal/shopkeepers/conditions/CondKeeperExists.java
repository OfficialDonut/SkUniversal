package us.donut.skuniversal.shopkeepers.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.shopkeepers.ShopkeepersHook.shopkeeperRegistry;

@Name("Shopkeepers - Shopkeeper Exists")
@Description("Checks if a shopkeeper with a certain ID exists.")
@Examples({"if shopkeeper with id 1 exists:"})
public class CondKeeperExists extends Condition {

    static {
        Skript.registerCondition(CondKeeperExists.class,
                "[a] [shop]keeper [with ID] %integer% exists",
                "[a] [shop]keeper [with ID] %integer% does(n't| not) exist");
    }

    private Expression<Integer> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<Integer>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "keeper wid ID " + id.toString(e, b) + " exists";
    }

    @Override
    public boolean check(Event e) {
        if (id.getSingle(e) == null) return isNegated();
        return (shopkeeperRegistry.getShopkeeperById(id.getSingle(e)) == null) == isNegated();
    }
}