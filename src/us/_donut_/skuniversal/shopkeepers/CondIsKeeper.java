package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.ShopkeepersPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

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
        return "entity is a shopkeeper";
    }

    @Override
    public boolean check(Event e) {
        ShopkeepersPlugin skp = ShopkeepersPlugin.getInstance();
        return skp.isShopkeeper(entity.getSingle(e));
    }
}