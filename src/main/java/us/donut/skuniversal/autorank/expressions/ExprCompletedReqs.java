package us.donut.skuniversal.autorank.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.armar.plugins.autorank.pathbuilder.holders.RequirementsHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.autorank.AutorankHook.*;

@Name("Autorank - Completed Requirements")
@Description("Returns list of completed requirements of player.")
@Examples({"send \"Requirements you have completed: %completed requirements of player%\""})
public class ExprCompletedReqs extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprCompletedReqs.class, String.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] (completed|finished) [Autorank] (requirements|reqs) of %player%",
                "[all of] %player%'s (completed|finished) [Autorank] (requirements|reqs)");
    }

    private Expression<Player> player;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "completed requirements of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return autorankAPI.getCompletedRequirements(player.getSingle(e)).stream().map(RequirementsHolder::getDescription).toArray(String[]::new);
    }
}
