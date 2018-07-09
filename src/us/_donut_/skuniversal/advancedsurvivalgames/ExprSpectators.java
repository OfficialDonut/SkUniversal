package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import e.Game;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Spectators")
@Description("Returns list of all game spectators.")
public class ExprSpectators extends SimpleExpression<Player> {

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
        return "the survival games spectators";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return Game.getSpectators().stream().map(OfflinePlayer::getPlayer).toArray(Player[]::new);
    }
}
