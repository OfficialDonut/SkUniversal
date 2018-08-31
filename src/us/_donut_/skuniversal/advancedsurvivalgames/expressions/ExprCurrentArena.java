package us._donut_.skuniversal.advancedsurvivalgames.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import e.Game;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Current Arena")
@Description("Returns the current playing arena name.")
public class ExprCurrentArena extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprCurrentArena.class, String.class, ExpressionType.SIMPLE, "[the] current [[advanced] (survival games|sg)] arena");
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the current survival games arena";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{Game.getCurrentArena()};
    }
}
