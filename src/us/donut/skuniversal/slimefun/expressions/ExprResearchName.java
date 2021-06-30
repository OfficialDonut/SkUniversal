package us.donut.skuniversal.slimefun.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import us.donut.skuniversal.slimefun.SlimefunHook;

import javax.annotation.Nullable;

@Name("Slimefun - Research Name")
@Description("Returns the name of a Slimefun research")
@Examples({"send \"%name of the slimefun research with ID \"cool_research\"%\""})
public class ExprResearchName extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprResearchName.class, String.class, ExpressionType.COMBINED, "[the] name of [the] [Slimefun] research [with ID] %string% for %player%");
    }

    private Expression<String> id;
    private Expression<Player> player;

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
        id = (Expression<String>) e[0];
        player = (Expression<Player>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the name of the Slimefun research with ID " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (id.getSingle(e) == null || player.getSingle(e) == null) return null;
        return new String[]{SlimefunHook.getResearch(id.getSingle(e)).getName(player.getSingle(e))};
    }
}
