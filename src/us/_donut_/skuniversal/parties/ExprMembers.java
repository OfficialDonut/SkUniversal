package us._donut_.skuniversal.parties;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.utils.api.PartiesAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExprMembers extends SimpleExpression<OfflinePlayer> {

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "members of party named " + name.getSingle(e);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        if (name.getSingle(e) != null) {
            PartiesAPI parties = new PartiesAPI();
            List<UUID> playerUUIDs = parties.getPartyMembers(name.getSingle(e));
            List<OfflinePlayer> players = new ArrayList<>();
            for (UUID p : playerUUIDs) {
                players.add(Bukkit.getOfflinePlayer(p));
            }
            return players.toArray(new OfflinePlayer[players.size()]);
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }
}
