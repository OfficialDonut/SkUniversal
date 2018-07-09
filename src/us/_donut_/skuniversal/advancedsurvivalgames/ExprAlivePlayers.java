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

@Name("AdvancedSurvivalGames - Alive Players")
@Description("Returns list of the alive players.")
public class ExprAlivePlayers extends SimpleExpression<Player> {

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
        return "the survival games alive players";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return Game.getAlivePlayers().stream().map(OfflinePlayer::getPlayer).toArray(Player[]::new);
    }
}
