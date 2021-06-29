package us.donut.skuniversal.skywars_daboross.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.daboross.bukkitdev.skywars.api.events.GameStartEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("SkyWars (Daboross) - Starting Players")
@Description("Returns the stating players on SkyWars Game Start event.")
public class ExprStartPlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprStartPlayers.class, Player.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] start[ing] players [in [the] [SkyWars] game]");
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
        if (!ScriptLoader.isCurrentEvent(GameStartEvent.class)) {
            Skript.error("You can not use starting players expression in any event but on SkyWars game start.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the starting players";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return ((GameStartEvent)e).getPlayers().toArray(new Player[0]);
    }
}
