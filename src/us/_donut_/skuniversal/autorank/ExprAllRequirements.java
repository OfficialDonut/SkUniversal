package us._donut_.skuniversal.autorank;

import ch.njol.skript.Skript;
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
import java.util.ArrayList;
import java.util.List;

@Name("Autorank - All Requirements")
@Description("Returns list of all requirements of player.")
@Examples({"send \"All of your requirements: %requirements of player%\""})
public class ExprAllRequirements extends SimpleExpression<String> {

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
    public String toString(@Nullable Event e, boolean arg1) {
        return "all requirements of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) != null) {
            List<RequirementsHolder> requirementsList = Autorank.getInstance().getAPI().getAllRequirements(player.getSingle(e));
            List<String> requirements = new ArrayList<>();
            for (RequirementsHolder req : requirementsList) {
                requirements.add(req.getDescription());
            }
            return requirements.toArray(new String[requirements.size()]);
        } else{
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }
}
