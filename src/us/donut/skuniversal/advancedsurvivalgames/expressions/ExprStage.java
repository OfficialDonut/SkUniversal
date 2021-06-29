package us.donut.skuniversal.advancedsurvivalgames.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import e.Game;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Current Stage")
@Description("Returns the current stage name.")
public class ExprStage extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprStage.class, String.class, ExpressionType.SIMPLE, "[the] [current] [[advanced] (survival games|sg)] stage");
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
        return "the current survival games stage";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{Game.getStageName()};
    }
}
