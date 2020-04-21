package us.donut.skuniversal.advancedsurvivalgames.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
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

    static {
        Skript.registerExpression(ExprCurrentWorld.class, World.class, ExpressionType.SIMPLE, "[the] current [[advanced] (survival games|sg)] world");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the current survival games world";
    }

    @Override
    @Nullable
    protected World[] get(Event e) {
        return new World[]{Game.getCurrentArenaWorld()};
    }
}
