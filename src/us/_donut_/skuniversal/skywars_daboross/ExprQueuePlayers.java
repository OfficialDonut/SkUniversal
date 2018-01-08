package us._donut_.skuniversal.skywars_daboross;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.daboross.bukkitdev.skywars.api.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Name("SkyWars (Daboross) - Players in Queue")
@Description("Returns the players in the game queue.")
@Examples({"send \"%the players in the skywars game queue%\""})
public class ExprQueuePlayers extends SimpleExpression<Player> {

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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "players in queue";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        SkyWars sw = (SkyWars) Bukkit.getPluginManager().getPlugin("SkyWars");
        List<Player> players = new ArrayList<>();
        for (UUID id : sw.getGameQueue().getInQueue()) {
            Player p = Bukkit.getPlayer(id);
            players.add(p);
        }
        return players.toArray(new Player[players.size()]);
    }
}
