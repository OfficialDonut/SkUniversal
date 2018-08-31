package us._donut_.skuniversal.clearlagg.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.minebuilders.clearlag.events.EntityRemoveEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("ClearLagg - Cleared Entities")
@Description("Returns list of the cleared entities on Removed Entities event.")
public class ExprClearedEntities extends SimpleExpression<Entity> {

    static {
        Skript.registerExpression(ExprClearedEntities.class, Entity.class, ExpressionType.SIMPLE, "[the] entities (cleared|removed|deleted) [by ClearLag[g]]");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean kl, SkriptParser.ParseResult pr) {
        if (!ScriptLoader.isCurrentEvent(EntityRemoveEvent.class)) {
            Skript.error("You can not use cleared entities expression in any event but on ClearLag remove entities.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the entities cleared";
    }

    @Override
    @Nullable
    protected Entity[] get(Event e) {
        return ((EntityRemoveEvent)e).getEntityList().toArray(new Entity[0]);
    }
}
