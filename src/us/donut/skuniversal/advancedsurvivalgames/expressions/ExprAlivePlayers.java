package us.donut.skuniversal.advancedsurvivalgames.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import e.Game;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Alive Players")
@Description("Returns list of the alive players.")
public class ExprAlivePlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprAlivePlayers.class, Player.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [[advanced] (survival games|sg)] (alive|living) players");
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
        return "the survival games alive players";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return Game.getAlivePlayers().stream().map(OfflinePlayer::getPlayer).toArray(Player[]::new);
    }
}
