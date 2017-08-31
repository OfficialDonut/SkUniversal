package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import e.SGPlayerKillEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprSGVictim extends SimpleExpression<Player> {

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        if (!ScriptLoader.isCurrentEvent(SGPlayerKillEvent.class)) {
            Skript.error("You can not use survival games victim expression in any event but on SG death event.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the survival games victim";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return new Player[]{((SGPlayerKillEvent)e).getVictim().getPlayer().getPlayer()};
    }
}
