package us._donut_.skuniversal.autorank.expressions;

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

import static us._donut_.skuniversal.autorank.AutorankHook.*;

@Name("Autorank - Failed Requirements")
@Description("Returns list of the failed requirements of a player.")
@Examples({"send \"Your failed requirements: %failed requirements of player%\""})
public class ExprFailedReqs extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprFailedReqs.class, String.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] failed [Autorank] (requirements|reqs) of %player%",
                "[all of] %player%'s failed [Autorank] (requirements|reqs)");
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
        return "failed requirements of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return autorankAPI.getFailedRequirements(player.getSingle(e)).stream().map(RequirementsHolder::getDescription).toArray(String[]::new);
    }
}
