package us._donut_.skuniversal.clearlagg;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.minebuilders.clearlag.events.EntityRemoveEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.List;

public class ExprClearedEntities extends SimpleExpression<Entity> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        if (!ScriptLoader.isCurrentEvent(EntityRemoveEvent.class)) {
            Skript.error("You can not use cleared entities expression in any event but on ClearLag remove entities.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the entities cleared";
    }

    @Override
    @Nullable
    protected Entity[] get(Event e) {
        if (((EntityRemoveEvent)e).getEntityList().isEmpty()) {
            return null;
        }
        List<Entity> entities = ((EntityRemoveEvent)e).getEntityList();
        return entities.toArray(new Entity[entities.size()]);
    }
}
