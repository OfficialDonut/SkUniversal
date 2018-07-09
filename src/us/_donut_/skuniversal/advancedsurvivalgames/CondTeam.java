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

@Name("AdvancedSurvivalGames - Has Teammate")
@Description("Checks if a player has a teammate.")
@Examples({"if the player has a survival games team:"})
public class CondTeam extends Condition {

    private Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " has survival games team";
    }

    @Override
    public boolean check(Event e) {
        return Game.getPlayerManager().getSGPlayer(player.getSingle(e)).hasTeam();
    }
}
