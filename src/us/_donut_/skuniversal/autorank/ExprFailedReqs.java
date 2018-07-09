package us._donut_.skuniversal.autorank;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.armar.plugins.autorank.Autorank;
import me.armar.plugins.autorank.pathbuilder.holders.RequirementsHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Autorank - Failed Requirements")
@Description("Returns list of the failed requirements of a player.")
@Examples({"send \"Your failed requirements: %failed requirements of player%\""})
public class ExprFailedReqs extends SimpleExpression<String> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
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
        return Autorank.getInstance().getAPI().getFailedRequirements(player.getSingle(e)).stream().map(RequirementsHolder::getDescription).toArray(String[]::new);
    }
}
