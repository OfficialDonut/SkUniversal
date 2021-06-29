package us.donut.skuniversal.skywars_daboross.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import us.donut.skuniversal.skywars_daboross.SkyWarsDaborossHook;

import javax.annotation.Nullable;

@Name("SkyWars (Daboross) - Next Arena")
@Description("Returns the name of the next arena.")
@Examples({"send \"%next skywars arena%\""})
public class ExprNextArena extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprNextArena.class, String.class, ExpressionType.SIMPLE, "[the] next [planned] SkyWars [game] arena");
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
        return "next arena";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{SkyWarsDaborossHook.skyWars.getGameQueue().getPlannedArena().getArenaName()};
    }
}
