package us.donut.skuniversal.skywars_daboross.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import us.donut.skuniversal.skywars_daboross.SkyWarsDaborossHook;

import javax.annotation.Nullable;

@Name("SkyWars (Daboross) - Players in Queue")
@Description("Returns the players in the game queue.")
@Examples({"send \"%the players in the skywars game queue%\""})
public class ExprQueuePlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprQueuePlayers.class, Player.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] players in [the] [SkyWars] [game] queue");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "players in queue";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return SkyWarsDaborossHook.skyWars.getGameQueue().getInQueue().stream().map(Bukkit::getPlayer).toArray(Player[]::new);
    }
}
