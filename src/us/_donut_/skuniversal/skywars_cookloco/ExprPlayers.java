package us._donut_.skuniversal.skywars_cookloco;

import ak.CookLoco.SkyWars.arena.ArenaManager;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ExprPlayers extends SimpleExpression<Player> {

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
        return "the players in SkyWars arena named " + name.getSingle(e);
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        if (name.getSingle(e) != null) {
            List<Player> players = new ArrayList<>();
            for (SkyPlayer p : ArenaManager.getGame(name.getSingle(e)).getAlivePlayer()) {
                players.add(p.getPlayer());
            }
            return players.toArray(new Player[players.size()]);
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }
}
