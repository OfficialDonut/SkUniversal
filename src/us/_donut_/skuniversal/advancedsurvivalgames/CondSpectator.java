package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import e.Game;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Is Spectating")
@Description("Checks if a player is spectating a game.")
@Examples({"if the player spectating a survival games game:"})
public class CondSpectator extends Condition {

    private Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " is spectating survival games";
    }

    @Override
    public boolean check(Event e) {
        return Game.getPlayerManager().getSGPlayer(player.getSingle(e)).isSpectator();
    }
}