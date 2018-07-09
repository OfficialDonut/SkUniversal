package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import e.Game;
import org.bukkit.World;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Current World")
@Description("Returns the current playing arena world name.")
public class ExprCurrentWorld extends SimpleExpression<World> {

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends World> getReturnType() {
        return World.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the survival games current world";
    }

    @Override
    @Nullable
    protected World[] get(Event e) {
        return new World[]{Game.getCurrentArenaWorld()};
    }
}
