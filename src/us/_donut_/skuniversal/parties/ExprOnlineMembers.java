package us._donut_.skuniversal.parties;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.Parties;
import com.alessiodp.parties.utils.api.PartiesAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.List;

@Name("Parties - Party Online Members")
@Description("Returns the online members of a party.")
@Examples({"send \"%the online members of the party named \"cool\"%\""})
public class ExprOnlineMembers extends SimpleExpression<Player> {

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "online members of party named " + name.getSingle(e);
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        if (name.getSingle(e) != null) {
            PartiesAPI parties = new PartiesAPI();
            List<Player> players = parties.getPartyOnlinePlayers(name.getSingle(e));
            return players.toArray(new Player[players.size()]);
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }
}